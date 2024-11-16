package com.lostark.root.test.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/one")
    public String testOne() {
        return "testOne";
    }

    @GetMapping("/two")
    public String testTwo() {
        return "testTwo";
    }
}
