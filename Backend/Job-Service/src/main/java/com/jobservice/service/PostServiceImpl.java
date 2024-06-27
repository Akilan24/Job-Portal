package com.jobservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobservice.exception.PostNotFoundException;
import com.jobservice.model.Post;
import com.jobservice.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() throws PostNotFoundException {
        List<Post> posts = postRepository.findAll();
        if (posts.isEmpty()) {
            throw new PostNotFoundException("No posts found");
        }
        return posts;
    }

    @Override
    public Post getPostById(int postId) throws PostNotFoundException {
        Optional<Post> post = postRepository.findById(postId);
        if (!post.isPresent()) {
            throw new PostNotFoundException("Post not found with id: " + postId);
        }
        return post.get();
    }

    @Override
    public Post updatePost(Post post) throws PostNotFoundException {
        Optional<Post> existingPost = postRepository.findById(post.getPostId());
        if (existingPost.isPresent()) {
            Post updatedPost = existingPost.get();
            updatedPost.setEmailId(post.getEmailId());
            updatedPost.setName(post.getName());
            updatedPost.setDescription(post.getDescription());
            return postRepository.save(updatedPost);
        } else {
            throw new PostNotFoundException("Post not found with id: " + post.getPostId());
        }
    }

    @Override
    public String deletePost(int postId) throws PostNotFoundException {
        if (postRepository.existsById(postId)) {
            postRepository.deleteById(postId);
            return "Post deleted with id: " + postId;
        } else {
            throw new PostNotFoundException("Post not found with id: " + postId);
        }
    }
}
