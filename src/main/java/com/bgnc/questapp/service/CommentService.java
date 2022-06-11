package com.bgnc.questapp.service;

import com.bgnc.questapp.model.Comment;

import com.bgnc.questapp.model.Post;
import com.bgnc.questapp.model.User;
import com.bgnc.questapp.repository.CommentRepository;
import com.bgnc.questapp.request.CommentCreateRequest;
import com.bgnc.questapp.request.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    CommentService(CommentRepository commentRepository,UserService userService,PostService postService){
        this.postService=postService;
        this.userService=userService;
        this.commentRepository=commentRepository;
    }

    public List<Comment> getAllComments(Optional<Long> userId, Optional<Long> postId) {

        if(userId.isPresent()&& postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }
        else if(userId.isPresent()){
            return commentRepository.findByUserId(userId.get());
        }
        else if(postId.isPresent()){
            return commentRepository.findByPostId(postId.get());
        }
        else
            return commentRepository.findAll();

    }

    public Comment getCommentById(Long commentId) {

        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment saveComment(CommentCreateRequest commentCreateRequest) {

        User user = userService.getUserById(commentCreateRequest.getUserId());
        Post post = postService.getPostById(commentCreateRequest.getPostId());
        if(user!=null && post!=null){
            Comment comment = new Comment();
            comment.setId(commentCreateRequest.getId());
            comment.setPost(post);
            comment.setUser(user);
            comment.setText(commentCreateRequest.getText());
            commentRepository.save(comment);
            return comment;
        }
        else
            return null;


    }

    public void deleteById(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public Comment updateCommentById(Long commentId, CommentUpdateRequest commentUpdateRequest) {

        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()){

            Comment toUpdate = comment.get();
            toUpdate.setText(commentUpdateRequest.getText());

            commentRepository.save(toUpdate);
            return toUpdate;
        }
        else
            return null;
    }
}
