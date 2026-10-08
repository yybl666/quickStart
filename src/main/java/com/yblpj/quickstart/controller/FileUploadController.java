package com.yblpj.quickstart.controller;

import com.yblpj.quickstart.pojo.Result;
import com.yblpj.quickstart.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        //获取文件原名
        String originalFilename = file.getOriginalFilename();
        String newFilename = UUID.randomUUID().toString() + "." + originalFilename.substring(originalFilename.lastIndexOf("."));
        String s = AliOssUtil.upload(newFilename, file.getInputStream());
        return Result.success(s);
    }

}
