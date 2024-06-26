package com.jobservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobservice.constant.PostConstants;
import com.jobservice.exception.PostNotFoundException;
import com.jobservice.model.Post;
import com.jobservice.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(PostConstants.POST_BASE_URL)
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(PostConstants.ADD_POST)
    public ResponseEntity<Post> addPost(@RequestBody @Valid Post post) {
        Post addedPost = postService.addPost(post);
        return new ResponseEntity<>(addedPost,PostConstants.HTTP_STATUS_CREATED);
    }

    @GetMapping(PostConstants.GET_ALL_POSTS)
    public ResponseEntity<List<Post>> getAllPosts() throws PostNotFoundException {
        List<Post> allPosts = postService.getAllPosts();
        return new ResponseEntity<>(allPosts,PostConstants.HTTP_STATUS_OK);
    }

    @GetMapping(PostConstants.GET_POST_BY_ID)
    public ResponseEntity<Optional<Post>> getPostById(@PathVariable int id) throws PostNotFoundException {
        Optional<Post> post = postService.getPostById(id);
        return new ResponseEntity<>(post,PostConstants.HTTP_STATUS_OK);
    }

    @PutMapping(PostConstants.UPDATE_POST)
    public ResponseEntity<Post> updatePost(@RequestBody @Valid Post post) throws PostNotFoundException {
        Post updatedPost = postService.updatePost(post);
        return new ResponseEntity<>(updatedPost,PostConstants.HTTP_STATUS_OK);
    }

    @DeleteMapping(PostConstants.DELETE_POST)
    public ResponseEntity<String> deletePost(@PathVariable int id) throws PostNotFoundException {
        String result = postService.deletePost(id);
        return new ResponseEntity<>(result,PostConstants.HTTP_STATUS_OK);
    }
}
