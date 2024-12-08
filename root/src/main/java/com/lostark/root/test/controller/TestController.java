package com.lostark.root.test.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${test.one}")
    private String password;

    @GetMapping("/one")
    public String testOne() {
        return "NewTestOne";
    }

    @GetMapping("/two")
    public String testTwo() {
        return "testTwo";
    }

    @GetMapping("/three")
    public String testThree() {
        return "testThree";
    }

    @GetMapping("/four")
    public String testFour() {
        return "testFour";
    }

    @GetMapping("/five")
    public String testFive() {
        return "testFive";
    }

    @GetMapping("/six")
    public String testSix() {
        return password;
    }

}
