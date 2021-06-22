package com.example.bookservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bookservice.entity.*;

import com.example.bookservice.service.BookCategoryService;
import com.example.bookservice.service.BookInfoService;

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






}
