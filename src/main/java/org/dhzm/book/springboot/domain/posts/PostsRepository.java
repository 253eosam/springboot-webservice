package org.dhzm.book.springboot.domain.posts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}

/*
    Entity 클래스는 기본 repository와 떨어져있으면 제대로 역할을 수행 할 수 없으므로
    같이 나두어야함.
 */