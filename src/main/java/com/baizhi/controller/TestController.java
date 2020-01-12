package com.baizhi.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("test")
@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("test")
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String test(String name){

        String forObject = restTemplate.getForObject("http://eureka-client2/producer/hello?name=" + name, String.class);
        return  forObject;

    }


    public String fallbackMethod(String name){

        return "hello方法没有用";
    }
}
