package com.guli.service.edu.controller;

import com.guli.common.base.result.R;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("user")
public class LoginController {

    @PostMapping("login")
    public R login () {
        return R.ok().data("token","admin");
    }
    @GetMapping("info")
    public R info () {
        return R.ok().data("name","admin")
                .data("roles","[admin]")
                .data("avator","https://space.bilibili.com/79952491");
    }
    @PostMapping("logout")
    public R logout () {
        return R.ok();
    }

}

