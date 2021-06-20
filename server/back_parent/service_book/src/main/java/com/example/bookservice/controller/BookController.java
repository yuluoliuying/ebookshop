package com.example.bookservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bookservice.entity.*;
import com.example.bookservice.recommend.BaseItemRecommender;
import com.example.bookservice.recommend.BaseUserRecommender;
import com.example.bookservice.service.BookCategoryService;
import com.example.bookservice.service.BookInfoService;
import com.example.bookservice.service.BxBookRatingsService;
import com.example.bookservice.service.CommentService;
import com.example.commonutils.R;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bookservice/book")
public class BookController {

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private BookCategoryService bookCategoryService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private BxBookRatingsService bxBookRatingsService;

    //根据图书ID显示图书的详情
    @GetMapping("bookinfo/{book_id}")
    @ApiOperation(value = "根据图书ID显示图书的详情", notes = "根据图书ID显示图书的详情")
    public R bookinfo(@PathVariable Long book_id){
        BookInfo info = bookInfoService.getbookinfo(book_id);
        return R.ok().data("bookinfo", info);
    }

    // 获取top20图书和新书推荐图书
    @GetMapping("bookindex")
    @ApiOperation(value = "获取top20图书和新书推荐图书", notes = "获取top20图书和新书推荐图书")
    public R bookindex(){
        List<IndexBookVO> top_book = bookInfoService.getTop();
        List<IndexBookVO> new_book = bookInfoService.getNew();
        return R.ok().data("top", top_book).data("new", new_book);
    }

    // 获取图书分类
    @GetMapping("bookcategory")
    @ApiOperation(value = "获取图书分类", notes = "获取图书分类")
    public R bookcategory(){
        List<BookCategory> bookCategoryList = bookCategoryService.list(null);
        List<String> categoryNameList = new ArrayList<>();
        for(BookCategory bookCategory:bookCategoryList){
            categoryNameList.add(bookCategory.getName());
        }
        return R.ok().data("category", categoryNameList);
    }

    // 根据图书的类别id获取所有该类别的图书
    @GetMapping("getCategoryBooks/{category_id}/{page}/{limit}")
    @ApiOperation(value = "根据图书的类别id获取所有该类别的图书", notes = "根据图书的类别id获取所有该类别的图书")
    public R getCategoryBooks(@PathVariable Integer category_id, @PathVariable Integer page,
                              @PathVariable Integer limit){
        Page<BookInfo> p = new Page<>(page, limit);
        Map<String, Object> bookMap =  bookInfoService.getCategoryBooks(category_id, p);
        return R.ok().data("category_map", bookMap);
    }

    // 根据图书的id 获取对应的评论内容(仅限10条)
    @GetMapping("getComment/{book_id}")
    @ApiOperation(value = "根据图书的id 获取对应的评论内容(仅限10条)", notes = "根据图书的id 获取对应的评论内容(仅限10条)")
    public R getComment(@PathVariable String book_id){
        List<Comment> commentList = commentService.getComment(book_id);
        return R.ok().data("commentList", commentList);
    }

    // 用户发布评论
    @PostMapping("comment")
    @ApiOperation(value = "用户发布评论", notes = "用户发布评论")
    public R comment(@RequestBody Comment comment){

        commentService.setComment(comment);
        return R.ok();
    }

    // 获取基于item的推荐图书
    @GetMapping("getRecommendItemList/{user_id}/{book_id}")
    public R getRecommendItemList(@PathVariable Integer user_id, @PathVariable Long book_id){
        boolean flag = bxBookRatingsService.judge(user_id);
        if(!flag){
            List<BookInfo> list2 = bookInfoService.getRecommend();
            return R.ok().data("recommendItemList", list2);
        }
        List<BookInfo> list = new ArrayList<>();
        Integer num = 10;
        BaseItemRecommender recommender = new BaseItemRecommender();
        List<RecommendedItem> recommendItemList = recommender.getRecommendItemList(user_id, book_id, num);
        if(recommendItemList.isEmpty()){
            List<BookInfo> list1 = bookInfoService.getRecommend();
            return R.ok().data("recommendItemList", list1);
        }
        for (RecommendedItem recommendedItem : recommendItemList) {
                long book_rec_id = recommendedItem.getItemID();
            BookInfo bookInfo = bookInfoService.getById(book_rec_id);
            list.add(bookInfo);
        }
        return R.ok().data("recommendItemList", list);
    }

    // 根据店铺id 查询该店铺的图书
    @GetMapping("getStoreBook/{store_id}/{page}/{limit}")
    public R getStoreBook(@PathVariable Integer store_id, @PathVariable Integer page,
                          @PathVariable Integer limit){
        Page<BookInfo> p = new Page<>(page, limit);
        Map<String, Object> bookMap= bookInfoService.getStoreBook(store_id, p);
        return R.ok().data("storeBookMap",bookMap);
    }

//    // 根据店铺id 和bookid 删除图书
//    @DeleteMapping("deleteStoreBook/{store_id}/{book_id}")
//    public R deleteStoreBook(@PathVariable Integer store_id, @PathVariable Long book_id){
//        bo
//        return R.ok();
//    }

    // 添加店铺图书
    @PostMapping("addStoreBook")
    public R addStoreBook(@RequestBody BookInfo bookInfo){

        bookInfoService.save(bookInfo);
        return R.ok();
    }

    // 当用户点击购买时修改图书信息表的deal_mount和store_mount
    @GetMapping("updateBookStore/{book_id}/{user_id}")
    public R updateBookStore(@PathVariable Long book_id,@PathVariable Long user_id){

        R response = bookInfoService.updateBookStore(book_id);
        if(response.getCode().equals(20000)){
            BxBookRatings ratings = new BxBookRatings();
            ratings.setBookid(book_id);
            ratings.setUserid(user_id);
            ratings.setScore(8);
            bxBookRatingsService.save(ratings);
        }
        return response;
    }

    // 获取基于item的推荐图书
    @GetMapping("getRecommendUserList/{user_id}")
    public R getRecommendUserList(@PathVariable Integer user_id){
        boolean flag = bxBookRatingsService.judge(user_id);
        if(!flag){
            List<BookInfo> list2 = bookInfoService.getRecommend();
            return R.ok().data("recommendUserList", list2);
        }
        List<BookInfo> list = new ArrayList<>();
        BaseUserRecommender recommender = new BaseUserRecommender();
        List<RecommendedItem> recommendItemList = recommender.getRecommendItemList(user_id);
        if(recommendItemList.isEmpty()){
            List<BookInfo> list1 = bookInfoService.getRecommend();
            return R.ok().data("recommendUserList", list1);
        }
        for (RecommendedItem recommendedItem : recommendItemList) {
            long book_rec_id = recommendedItem.getItemID();
            BookInfo bookInfo = bookInfoService.getById(book_rec_id);
            list.add(bookInfo);
        }
        return R.ok().data("recommendUserList", list);
    }


}
