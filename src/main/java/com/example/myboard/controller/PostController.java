package com.example.myboard.controller;

import com.example.myboard.domain.Post;
import com.example.myboard.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// 브라우저의 요청 URL을 받아서 처리
@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {this.postService = postService;}

    // 목록
    @GetMapping
    public String list(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "list";
    }

    // 글쓰기 폼
    @GetMapping("/new")
    public String createForm() {
        return "createForm";
    }

    // 글쓰기 처리
    @PostMapping("/new")
    public String create(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/posts";
    }

    // 상세보기
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Post post = postService.increaseViewCount(id);
        model.addAttribute("post", post);
        return "detail";
    }

    // 수정 폼
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "editForm";
    }

    // 수정 처리
    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id,
                       @RequestParam String title,
                       @RequestParam String content,
                       @RequestParam String author) {
        postService.update(id, title, content, author);
        return "redirect:/posts/" + id;
    }

    // 삭제 처리
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        postService.delete(id);
        return "redirect:/posts";
    }
}
