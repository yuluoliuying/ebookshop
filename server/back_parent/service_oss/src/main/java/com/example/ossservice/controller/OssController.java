package com.example.ossservice.controller;

import com.example.commonutils.R;
import com.example.ossservice.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ossservice/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("getPictureUrl")
    public R uploadOssFile(MultipartFile file){
        //获取上传的文件   MultipartFile
        String avatarUrl = ossService.uploadFileAvatar(file);   //获取上传阿里云后的图片的路径
        return R.ok().data("url",avatarUrl);
    }
}
