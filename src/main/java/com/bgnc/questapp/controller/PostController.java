package com.bgnc.questapp.controller;

import com.bgnc.questapp.model.Post;
import com.bgnc.questapp.request.PostCreateRequest;
import com.bgnc.questapp.request.PostUpdateRequest;
import com.bgnc.questapp.response.PostResponse;
import com.bgnc.questapp.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;

    PostController(PostService postService){
        this.postService=postService;
    }

    @GetMapping
    public List<PostResponse> getAllPost(@RequestParam Optional<Long>userId){

        return postService.getAllPost(userId);
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }


    @PostMapping
    public Post addPost(@RequestBody PostCreateRequest newPostRequest){
        return postService.addPost(newPostRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        postService.deleteById(id);

    }

    @PutMapping("/{postId}")

    public Post updatePostById(@PathVariable Long postId,@RequestParam PostUpdateRequest newPost){
        return postService.updatePostById(postId,newPost);
    }


}
