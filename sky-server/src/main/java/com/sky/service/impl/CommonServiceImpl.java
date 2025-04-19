package com.sky.service.impl;

import com.sky.result.Result;
import com.sky.service.CommonService;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class CommonServiceImpl implements CommonService {

    @Autowired
    private AliOssUtil aliOssUtil;

    @Autowired
    private CommonService commonService;

    public String uploadFile(MultipartFile file) {

        try {
            String originalFilename = file.getOriginalFilename();
            String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
            String s = UUID.randomUUID().toString() + substring;

            return aliOssUtil.upload(file.getBytes(), s);
        } catch (IOException e) {
            log.error("文件上传失败：{}", e);
            throw new RuntimeException(e);
        }
    }
}
