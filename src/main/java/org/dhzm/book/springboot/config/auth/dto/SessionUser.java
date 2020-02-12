package org.dhzm.book.springboot.config.auth.dto;

import lombok.Getter;
import org.dhzm.book.springboot.domain.user.User;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}

/*
    SessionUser에는 인증된 사용자 정보만 필요

    직렬화를 구현하지 않으면 에러 발생 - Entity 클래스를 직렬화하는 것은 자식 엔티티를 갖고 있다면 직렬화 대상에 자식들까지 포함
                                        성능 이슈, 부수 효과가 발생 확률 높음
 */