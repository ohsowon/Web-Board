package com.example.myboard.repository;

import com.example.myboard.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

// DB에 직접 접근하는 역할
public interface PostRepository extends JpaRepository<Post, Long> {
}
