package org.dhzm.book.springboot.web;

import lombok.RequiredArgsConstructor;
import org.dhzm.book.springboot.service.PostsService;
import org.dhzm.book.springboot.web.dto.PostsResponseDto;
import org.dhzm.book.springboot.web.dto.PostsSaveRequestDto;
import org.dhzm.book.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}

/*
    스프링에서 Bean을 주입받는 방식
    @Autowired , setter, 생성자
    - 가장 권장하는 방식 ** 생성자 주입 **
    - @Autowired는 권장하지 않음 -> 생성자로 Bean 객체를 받도록 설정
    - @RequiredArgsConstructor에서 final이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복이 대신 생성
 */
