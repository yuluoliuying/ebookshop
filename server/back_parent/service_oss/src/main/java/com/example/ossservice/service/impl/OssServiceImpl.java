package com.example.ossservice.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.ossservice.service.OssService;
import com.example.ossservice.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 上传文件流。
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String filename= file.getOriginalFilename();
            //1. 在文件名称里添加唯一的随机的值 ---》 避免不同用户上传头像名称相同时会覆盖掉
            String uuid = UUID.randomUUID().toString().replace("-","");
            //dsahsl12301.jpg
            filename = uuid + filename;

            //2 把文件按照日期进行分类 2020/4/12/01.jpg
            //获取当前日期
            String date = new DateTime().toString("yyyy/MM/dd");
            filename = date + "/" + filename;

            //调用oss实现文件上传
            ossClient.putObject(bucketName, filename , inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();
            //拼接阿里云图片的路径
            String url = "https://" + bucketName + "." + endpoint + "/" + filename;
            return url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
