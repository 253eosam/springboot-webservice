package org.dhzm.book.springboot;

import org.dhzm.book.springboot.config.auth.SecurityConfig;
import org.dhzm.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is; // is import 'org.hamcrest.Matchers.is'
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class, excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}

/*
    @RunWith(SpringRunner.class)
    - 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴.
    - 여기서는 SpringRunner라는 스프링 실행자를 사용
    - 스프링 부트 테스트와 JUnit 사이에 연결자 역할

    @WebMvcTest
    - 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
    - 선언할 경우 @Controller, @ControllerAdvice등을 사용할 수 있음.
    - !! @Service , @Component, @Repository 등은 사용할 수 없음.
    - 여기서는 컨트롤러만 사용하기 때문에 사용

    @Autowired
    - 스프링이 관리하는 빈(Bean)을 주입

    @MockMvc
    - 웹 api를 테스트할 때 사용
    - 스프링 mvc 테스트용.
    - get, post 등에 대한 api 테스트 가능

    mvc.perform(get("/hello"))
    - MockMvc를 통해 /hello 주소로 http get 요청
    - 체이닝이 지원되어 아래와 같은 여러 기능을 이어서 선언할 수 있음

    andExpect(status().isOk())
    - mvc.perform의 결과를 검증
    - header의 status를 검증
    - 200,404,500 등 상태를 검증

    andExpect(content().string(hello))
    - mvc.perform의 결과를 검증
    - 응답 본문 내용을 검증

    param
    - api 테스트할 때 사용될 요청 파라미터
    - !! 값은 string만 허용
    - 숫자/날짜 등의 데이터도 등록할 때는 문자열로 변경

    jsonPath
    - Json 응답값을 필드별로 검증할 수 있는 메소드
    - $를 기준으로 필드명을 명시
 */
