package com.jobservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobservice.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
