package com.example.bookservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bookservice.entity.BookInfo;
import com.example.bookservice.entity.IndexBookVO;
import com.example.bookservice.mapper.BookInfoMapper;
import com.example.bookservice.service.BookInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.commonutils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class BookInfoServiceImpl extends ServiceImpl<BookInfoMapper, BookInfo> implements BookInfoService {

    //根据图书id获取图书详情
    @Override
    public BookInfo getbookinfo(Long id) {
        BookInfo info = baseMapper.selectById(id);
        BookInfo new_info = new BookInfo();
        BeanUtils.copyProperties(info,new_info);
        new_info.setLookMount(info.getLookMount()+1);
        baseMapper.updateById(new_info);
        return info;
    }

    // 获取首页显示的top20图书
    @Override
    public List<IndexBookVO> getTop() {
        QueryWrapper<BookInfo> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("deal_mount").last("limit 20");
        List<IndexBookVO> top = new ArrayList<>();
        List<BookInfo> bookInfos = baseMapper.selectList(wrapper);
        for(BookInfo bookInfo:bookInfos){
            IndexBookVO bookVO = new IndexBookVO();
            BeanUtils.copyProperties(bookInfo,bookVO);
            top.add(bookVO);
        }
        return top;
    }

    // 获取首页显示的新书推荐
    @Override
    public List<IndexBookVO> getNew() {
        QueryWrapper<BookInfo> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("store_time");
        wrapper.last("limit 20");
        List<IndexBookVO> new_book = new ArrayList<>();
        List<BookInfo> bookInfos = baseMapper.selectList(wrapper);
        for(BookInfo bookInfo:bookInfos){
            IndexBookVO bookVO = new IndexBookVO();
            BeanUtils.copyProperties(bookInfo,bookVO);
            new_book.add(bookVO);
        }
        return new_book;
    }

    @Override
    public Map<String, Object> getCategoryBooks(Integer category_id, Page<BookInfo> p) {
        QueryWrapper<BookInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("book_category_id", category_id);
        wrapper.orderByDesc("deal_mount");
        baseMapper.selectPage(p, wrapper);
        List<BookInfo> records = p.getRecords();
        long current = p.getCurrent();
        long pages = p.getPages();
        long size = p.getSize();
        long total = p.getTotal();
        //是否有下一页
        boolean hasNext = p.hasNext();
        //是否有上一页
        boolean hasPrevious = p.hasPrevious();
        //将分页数据放到map中
        HashMap<String, Object> map =new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }

    @Override
    public Map<String, Object> getStoreBook(Integer store_id, Page<BookInfo> p) {

        QueryWrapper<BookInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("store_id", store_id);
        wrapper.orderByDesc("deal_mount");
        baseMapper.selectPage(p, wrapper);
        List<BookInfo> records = p.getRecords();
        long current = p.getCurrent();
        long pages = p.getPages();
        long size = p.getSize();
        long total = p.getTotal();
        //是否有下一页
        boolean hasNext = p.hasNext();
        //是否有上一页
        boolean hasPrevious = p.hasPrevious();
        //将分页数据放到map中
        HashMap<String, Object> map =new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }

    // 当用户点击购买时修改图书信息表的deal_mount和store_mount
    @Override
    public R updateBookStore(Long book_id) {
        BookInfo info = baseMapper.selectById(book_id);
        Integer deal_mount = info.getDealMount();
        Integer storeMount = info.getStoreMount();
        if(storeMount-1>=0){
            info.setDealMount(deal_mount+1);
            info.setStoreMount(storeMount-1);
            return R.ok();
        }else {
            return R.error().message("该商品无库存");
        }
    }

    @Override
    public List<BookInfo> getRecommend() {
        QueryWrapper<BookInfo> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("deal_mount").last("limit 10");
        List<BookInfo> bookInfos = baseMapper.selectList(wrapper);
        return bookInfos;
    }


}
