package com.example.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.userservice.entity.Store;
import com.example.userservice.mapper.StoreMapper;
import com.example.userservice.service.StoreService;
import org.springframework.stereotype.Service;


@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {
    @Override
    public Integer getStoreIdByUserId(Integer user_id) {
        QueryWrapper<Store> wrapper = new QueryWrapper<>();
        wrapper.eq("store_manager_id", user_id);
        Store store = baseMapper.selectOne(wrapper);
        return store.getStoreId();
    }

    @Override
    public Store getStoreInfo(Integer store_id) {
        Store store = baseMapper.selectById(store_id);
        return store;
    }
}
