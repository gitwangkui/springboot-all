package com.redmaple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 若成风
 * @description
 * @date 2021/4/25 21:34
 * @copyright (c) 2021, all rights reserved
 **/
@Controller
public class ErrorController {

    // 403权限不足页面
    @RequestMapping("/error/403")
    public String error() {
        return "/error/403";
    }

}
