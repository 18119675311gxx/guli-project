package com.guli.service.edu.feign;

import com.guli.common.base.result.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.TimeUnit;
@Service
@FeignClient("service-oss")
public interface OssFileService {
    @GetMapping("/admin/oss/file/test")
    R test();

    //删除阿里云文件
    @DeleteMapping("/admin/oss/file/remove")
    R removeFile(String avatar);
}
