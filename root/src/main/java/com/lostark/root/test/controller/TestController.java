package com.lostark.root.test.controller;

import com.lostark.root.common.staticMethod.ApiRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {


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
        return "six";
    }

}
