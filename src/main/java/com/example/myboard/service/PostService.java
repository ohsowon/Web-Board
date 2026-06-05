package com.example.myboard.service;

import com.example.myboard.domain.Post;
import com.example.myboard.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

// 비즈니스 로직 (Controller와 Repository 사이 중간다리 역할)
@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 게시글 저장
    public Long save(Post post) {
        postRepository.save(post);
        return post.getId();
    }

    // 전체 조회 (최신순)
    public List<Post> findAll() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    // 단건 조회
    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
    }

    // 조회수 증가 후 게시글 반환
    public Post increaseViewCount(Long id) {
        Post post = findById(id);
        post.setViewCount(post.getViewCount() + 1);
        return post;
    }

    // 게시글 수정 (JPA 더티 체킹 활용하여 save() 불필요)
    public void update(Long id, String title, String content, String author) {
        Post post = findById(id);
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author);
    }

    // 게시글 삭제
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
