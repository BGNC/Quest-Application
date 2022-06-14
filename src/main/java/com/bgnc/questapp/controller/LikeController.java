package com.bgnc.questapp.controller;

import com.bgnc.questapp.model.Like;
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
    public List<Like> getAllLikes(@RequestParam Optional<Long> userId, Optional<Long> postId){
        return likeService.getAllLikes(userId,postId);

    }

    @GetMapping("/{likeId}")
    public Like getLikeById(@PathVariable Long likeId){
        return likeService.getLikeById(likeId);
    }

    @PostMapping
    public Like saveLike(@RequestBody Like like){
        return likeService.addLike(like);

    }

    @PutMapping("/{likeId}")
    public Like updateLike(@PathVariable Long likeId ,@RequestBody Like like){
        return likeService.updateById(likeId,like);

    }


    @DeleteMapping("/{likeId}")
    public void deleteById(@PathVariable Long likeId){
        likeService.deleteById(likeId);
    }
}
