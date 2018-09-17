package com.self.concurrent.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucifer
 * @do 用来学习使用测试工具的类
 * @date 2018/09/13 10:42
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }

}
