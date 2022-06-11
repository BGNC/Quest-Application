package com.bgnc.questapp.response;

import com.bgnc.questapp.model.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    private Long id;
    private Long userId;
    private String userName;
    private String title;
    private String text;

    List<LikeResponse> likeResponseList;

    public PostResponse(Post post){
        this.id= post.getId();
        this.userId= Long.valueOf(post.getUser().getId());
        this.userName=post.getUser().getUserName();
        this.title=post.getTitle();
        this.text=post.getText();


    }
}
