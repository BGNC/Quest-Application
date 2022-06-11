package com.bgnc.questapp.service;

import com.bgnc.questapp.model.Post;
import com.bgnc.questapp.model.User;
import com.bgnc.questapp.repository.PostRepository;
import com.bgnc.questapp.request.PostCreateRequest;
import com.bgnc.questapp.request.PostUpdateRequest;
import com.bgnc.questapp.response.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;
    private LikeService likeService;
    private UserService userService;

    PostService(PostRepository postRepository,UserService userService ){
        this.userService=userService;

        this.postRepository=postRepository;
    }
    public void setLikeService(LikeService likeService){
        this.likeService=likeService;
    }

    public List<PostResponse> getAllPost(Optional<Long> userId){

        List<Post> list;

        if(userId.isPresent()){
            list= postRepository.findByUserId(userId.get());
        }
        else
            list= postRepository.findAll();

        return list.stream().map(p->{


                return new PostResponse(p);
        }).
                collect(Collectors.toList());

    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post addPost(PostCreateRequest newPost) {

        User user = userService.getUserById(newPost.getUserId());

        if(user==null) {
            return null;
        }

        Post postToSave = new Post();
        postToSave.setId(newPost.getId());
        postToSave.setText(newPost.getText());
        postToSave.setTitle(newPost.getTitle());
        postToSave.setUser(user);
        return postRepository.save(postToSave);
    }

    public Post updatePostById(Long postId, PostUpdateRequest newPost) {

        Optional<Post> post = postRepository.findById(postId);

        if(post.isPresent()){
            Post toUpdate = new Post();
            toUpdate.setTitle(newPost.getTitle());
            toUpdate.setText(newPost.getText());

            postRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }
}
