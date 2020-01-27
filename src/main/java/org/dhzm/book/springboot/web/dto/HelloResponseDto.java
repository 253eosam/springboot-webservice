package org.dhzm.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HelloResponseDto {

    private final String name;
    private final int amount;

}

/*
    @Getter
    - 선언된 모든 필드의 get메소드를 생성

    @RequiredArgsConstructor
    - 선언된 모든 final 필드가 포홤된 생성자를 생성
    - final이 없는 필드는 생성자에 포함되지 않음
 */