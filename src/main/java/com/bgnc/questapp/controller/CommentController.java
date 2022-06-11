package com.bgnc.questapp.controller;

import com.bgnc.questapp.model.Comment;
import com.bgnc.questapp.model.Post;
import com.bgnc.questapp.model.User;
import com.bgnc.questapp.request.CommentCreateRequest;
import com.bgnc.questapp.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    CommentController(CommentService commentService){
        this.commentService=commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return commentService.getAllComments(userId,postId);
    }

    @PostMapping
    public Comment saveComment(@RequestBody CommentCreateRequest commentCreateRequest){
        return commentService.saveComment(commentCreateRequest);
    }

    @GetMapping("/{commentId}")
    public Comment getCommentById(@PathVariable Long commentId){
        return commentService.getCommentById(commentId);

    }




}
