package com.redmaple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 若成风
 * @description
 * @date 2021/4/25 21:33
 * @copyright (c) 2021, all rights reserved
 **/
@Controller
public class SecurityController {
    // 首页
    @RequestMapping("/")
    public String index() {
        System.out.println("index");
        return "index";
    }

    // 查询订单
    @RequestMapping("/showOrder")
    public String showOrder() {
        return "showOrder";
    }

    // 添加订单
    @RequestMapping("/addOrder")
    public String addOrder() {
        return "addOrder";
    }

    // 修改订单
    @RequestMapping("/updateOrder")
    public String updateOrder() {
        return "updateOrder";
    }

    // 删除订单
    @RequestMapping("/deleteOrder")
    public String deleteOrder() {
        return "deleteOrder";
    }

    // 自定义登陆页面
    @GetMapping("/login")
    public String login() {
        System.out.println("login");
        return "login";
    }

//    @Autowired
////    private UserMapper userMapper;
////
////    @RequestMapping("/findUser")
////    @ResponseBody
////    public User findUser(String userName) {
////        return userMapper.findByUsername(userName);
////    }
}
