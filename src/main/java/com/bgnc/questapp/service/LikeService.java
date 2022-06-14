package com.bgnc.questapp.service;

import com.bgnc.questapp.model.Like;
import com.bgnc.questapp.model.Post;
import com.bgnc.questapp.model.User;
import com.bgnc.questapp.repository.LikeRepository;
import com.bgnc.questapp.request.LikeCreateRequest;
import com.bgnc.questapp.response.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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




    public List<LikeResponse> getAllLikes(Optional<Long> userId, Optional<Long> postId) {

        List<Like> list;

        if(userId.isPresent() && postId.isPresent()){
            list = likeRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }
        else if(userId.isPresent()){
            list =likeRepository.findByUserId(userId.get());
        }
        else if(postId.isPresent()){
            list = likeRepository.findByPostId(postId.get());
        }
        else {
             list = likeRepository.findAll();
        }

        return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
    }


    public void deleteById(Long likeId) {
        likeRepository.deleteById(likeId);
    }

    public Like addLike(LikeCreateRequest request) {
        User user = userService.getUserById(request.getUserId());
        Post post = postService.getPostById(request.getPostId());

        if(user!=null && post!=null){

            Like saveToLike = new Like();
            saveToLike.setId(request.getId());
            saveToLike.setPost(post);
            saveToLike.setUser(user);
            likeRepository.save(saveToLike);
            return saveToLike;
        }
        else
            return null;

    }

    public Like getLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

}
