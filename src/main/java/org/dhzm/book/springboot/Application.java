package org.dhzm.book.springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // 이 위치부터 설정을 읽어옴 -> 프로젝트 최상단에 위치
public class Application {
    public static void main(String[] args){
        // SpringApplication -> 내장 WAS 실행
        SpringApplication.run(Application.class,args);
    }
}

/*
    내장 WAS 권장
    - 언제 어디서나 같은 환경에서 스프링 부트를 배포.
    - 톰캣을 설치할 필요 없음.
 */
