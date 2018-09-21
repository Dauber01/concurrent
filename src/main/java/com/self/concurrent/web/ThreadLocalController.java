package com.self.concurrent.web;

import com.self.concurrent.threadlocal.RequestHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucifer
 * @do threadLocal的测试controller
 * @date 2018/09/18 17:48
 */
@RestController
@RequestMapping("/threadlocal")
public class ThreadLocalController {

    @GetMapping("/test")
    public Long test(){
        return RequestHolder.getId();
    }

}
