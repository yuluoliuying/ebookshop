package com.example.bookservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bookservice.entity.BookInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bookservice.entity.IndexBookVO;
import com.example.commonutils.R;

import java.util.List;
import java.util.Map;

public interface BookInfoService extends IService<BookInfo> {

    BookInfo getbookinfo(Long id);

    List<IndexBookVO> getTop();

    List<IndexBookVO> getNew();

    Map<String, Object> getCategoryBooks(Integer category_id, Page<BookInfo> p);

    Map<String, Object> getStoreBook(Integer store_id, Page<BookInfo> p);

    R updateBookStore(Long book_id);

    List<BookInfo> getRecommend();
}
