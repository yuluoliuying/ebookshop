package com.example.ossservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {

    // 上传图片
    String uploadFileAvatar(MultipartFile file);
}
