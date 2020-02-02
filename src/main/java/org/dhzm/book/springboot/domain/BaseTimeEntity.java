package org.dhzm.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

}

/*
    LocalDate 사용
    - Java8부터 LocalDate와 LocalDateTime이 실용적으로 사용가능
    - 데이터베이스에 데이터를 삽입하거나 수정할때 서비스계층에서 지속적인 코드 사용이 불편함.
    - JPA Auditing을 사용

    @MappedSuperclass
    - JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들(createdDate, modifiedDate)도
    칼럼으로 인식하도록한다.

    @EntityListeners(AuditingEntityListener.class)
    - BaseTimeEntity 클래스에 Auditing 기능을 포함시킨다.

    @CreatedDate
    - Entity가 생성되어 저장될 때 시간이 자동으로 저장된다.

    @LastModifiedDate
    - 조회한 Entity의 값을 변경할 때 시간이 자동 저장된다.
 */