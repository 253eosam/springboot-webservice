package org.dhzm.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}

/*
    @Entity
    - 테이블과 링크될 클래스
    - 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭

    @Id
    - 해당 테이블의 pk 필드

    @GeneratedValue
    - pk의 생성 규칙
    - 스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment 됨

    @Column
    - 테이블 칼럼을 나타내며 굳이 선언하지 않아도 클래스의 필드는 모두 칼럼이 됨
    - 기본값 외에 추가 변경이 필요한 옵션이 있으면 사용
    - 문자열 varchar(255) 기본 사이즈, -> 500으로 늘리거나 text 타입으로 변경하고 싶은경우

    @NoArgsConstructor
    - 기본 생성자 자동 추가
    - public Posts(){} 동등

    @Builder
    - 해당 클래스의 빌더 패턴 클래스를 생성
    - 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함

    ###################################################################################
    PK는 Long의 auto_increment를 추천
    여러 키를 조합한 복합기나 유니크 키는 난감한 상황을 발생시킴.
    -> FK를 맺을 때 다른 테이블에서 복합키 전부를 갖고 있거나, 중간 테이블을 하나 더 둬야 하는 상황
    -> 인덱스에 좋은 영향을 끼치지 못함
    -> 유니크한 조건이 변경될 경우 PK전체를 수정정
    ###################################################################################
    Setter 메소드가 없음!!!
 */