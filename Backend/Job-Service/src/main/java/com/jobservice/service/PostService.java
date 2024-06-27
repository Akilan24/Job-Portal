package com.jobservice.service;

import java.util.List;

import com.jobservice.exception.PostNotFoundException;
import com.jobservice.model.Post;

public interface PostService {
    Post addPost(Post post);
    List<Post> getAllPosts() throws PostNotFoundException;
    Post getPostById(int postId) throws PostNotFoundException;
    Post updatePost(Post post) throws PostNotFoundException;
    String deletePost(int postId) throws PostNotFoundException;
}
