package com.bgnc.questapp.controller;

import com.bgnc.questapp.model.Like;
import com.bgnc.questapp.request.LikeCreateRequest;
import com.bgnc.questapp.response.LikeResponse;
import com.bgnc.questapp.service.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }


    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return likeService.getAllLikes(userId,postId);

    }

    @GetMapping("/{likeId}")
    public Like getLikeById(@PathVariable Long likeId){
        return likeService.getLikeById(likeId);
    }

    @PostMapping
    public Like saveLike(@RequestBody LikeCreateRequest likeRequest){
        return likeService.addLike(likeRequest);

    }

    @DeleteMapping("/{likeId}")
    public void deleteById(@PathVariable Long likeId){
        likeService.deleteById(likeId);
    }
}
