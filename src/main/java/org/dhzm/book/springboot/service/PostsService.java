package org.dhzm.book.springboot.service;

import lombok.RequiredArgsConstructor;
import org.dhzm.book.springboot.domain.posts.Posts;
import org.dhzm.book.springboot.domain.posts.PostsRepository;
import org.dhzm.book.springboot.web.dto.PostsListResponseDto;
import org.dhzm.book.springboot.web.dto.PostsResponseDto;
import org.dhzm.book.springboot.web.dto.PostsSaveRequestDto;
import org.dhzm.book.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);
    }
}

/*
    Service layer
    - 트랜잭션과 도메인 간의 순서만 보장

    !! 기존 트랜잭션 스크립트 방식을 서비스에서 Domain으로 변경
    - 비지니스 처리를 Domain에서 처리

    Update 기능에 쿼리를 날리는 부분이 없는 이유
    - JPA의 영속성 컨텍스트 때문
    - 엔티티를 영구 저장하는 환경
    - 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지 -> 트랜잭션이 끝나는 시점에
        해당 테이블에 변경분을 반영 (Update 쿼리를 날릴 필요가 없음 | ** 더티 체킹 **)

    readOnly = true
    - 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도를 개선
    - 등록, 수정, 삭제 기능이 전혀 없는 서비스 메소드에서 사용

    .map(PostsListResponseDto::new)
    .map(posts-> new PostsListResponseDto(posts))
    postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto 변환-> List로 변환하는 메소드

    postsRepository.delete(posts)
    - jpaRepository에서 이미 delete메소드를 지원하고 있으니 이를 활용.
    - 엔티티를 파라미터로 삭제할 수도 있고, deleteById 메소드를 이용하면 id로 삭제할 수도 있다.
    - 존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제
 */