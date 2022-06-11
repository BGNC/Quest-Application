package com.bgnc.questapp.response;

import com.bgnc.questapp.model.Like;
import lombok.Data;

@Data
public class LikeResponse {

    private Long id;
    private Long userId;
    private Long postId;


    LikeResponse(Like like){
        this.id= like.getId();
        this.userId= (like.getUser().getId());
        this.postId=like.getPost().getId();
    }
}
