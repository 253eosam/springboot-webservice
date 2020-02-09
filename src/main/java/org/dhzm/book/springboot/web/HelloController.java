package org.dhzm.book.springboot.web;

import org.dhzm.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }

}

/*
    @RestController
    - 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줌.
    - ResponseBody를 각 메소드마다 선언하지 않고 한번에 사용 가능.

    @GetMapping
    - Http Method인 Get의 요청을 받을 수 있는 API를 만듬.
    - @RequestMapping(method = RequestMethod.GET)의 대체재

    @RequestParam
    - 외부에서 api로 넘긴 파라미터를 가져오는 어노테이션
 */