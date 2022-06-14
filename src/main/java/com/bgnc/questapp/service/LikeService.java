package com.bgnc.questapp.service;

import com.bgnc.questapp.model.Like;
import com.bgnc.questapp.model.Post;
import com.bgnc.questapp.model.User;
import com.bgnc.questapp.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserService userService;
    private final PostService postService;


    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }




    public List<Like> getAllLikes(Optional<Long> userId, Optional<Long> postId) {

        if(userId.isPresent() && postId.isPresent()){
            return likeRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }
        else if(userId.isPresent()){
            return likeRepository.findByUserId(userId.get());
        }
        else if(postId.isPresent()){
            return likeRepository.findByPostId(postId.get());
        }
        else
            return likeRepository.findAll();
    }


    public void deleteById(Long likeId) {
        likeRepository.deleteById(likeId);
    }

    public Like addLike(Like like) {
        User user = userService.getUserById(like.getUser().getId());
        Post post = postService.getPostById(like.getPost().getId());

        if(user!=null && post!=null){

            Like saveToLike = new Like();
            like.setId(like.getId());
            like.setPost(post);
            like.setUser(user);
            likeRepository.save(saveToLike);
            return saveToLike;
        }
        else
            return null;

    }

    public Like getLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public Like updateById(Long likeId, Like like) {

    }
}
