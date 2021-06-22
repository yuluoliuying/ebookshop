package com.example.servicebase.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //这里的fieldName不是mysql表中的字段名，而是属性名
        this.setFieldValByName("created", new Date(), metaObject);
        this.setFieldValByName("updated", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updated", new Date(), metaObject);

    }
}
