package org.dhzm.book.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dhzm.book.springboot.domain.posts.Posts;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}

/*
    Dtos
    - 계층 간에 데이터 교환을 위한 객체

    Entity class
    - Entity클래스를 Request/Response클래스로 사용해서는 안됨!!!
    - 데이터베이스와 맞닿는 핵심 클래스
    - 서비스 클래스나 비즈니스 로직들이 Entity클래스를 기준으로 동작 -> Entity클래스가 변경되면 여러 클래스에 영향
    - Controller에서 결과값으로 여러 테이블을 조인해서 줘야 할 경우 발생 -> Entity 클래스로 표현하기 어려운 경우 발생

 */