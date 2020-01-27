package org.dhzm.book.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}

/*
    @RestController
    - 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줌.
    - ResponseBody를 각 메소드마다 선언하지 않고 한번에 사용 가능.

    @GetMapping
    - Http Method인 Get의 요청을 받을 수 있는 API를 만듬.
    - @RequestMapping(method = RequestMethod.GET)의 대체재
 */