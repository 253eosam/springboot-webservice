package org.dhzm.book.springboot.web;

import lombok.RequiredArgsConstructor;
import org.dhzm.book.springboot.service.PostsService;
import org.dhzm.book.springboot.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class indexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}

/*
    gradle dependency 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정

    Model
    - 서버 템플릿 엔진에서 사용할 수 있는 객체를 지정할 수 있다.
    - postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달달

*/