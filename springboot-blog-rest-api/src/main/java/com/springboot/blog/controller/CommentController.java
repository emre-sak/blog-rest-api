package com.springboot.blog.controller;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.dto.requests.CreateCommentRequest;
import com.springboot.blog.dto.requests.UpdateCommentRequest;
import com.springboot.blog.service.CommnetService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class CommentController {

    private final CommnetService commnetService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") Long postId,
                                                    @RequestBody @Valid CreateCommentRequest commentRequest) {

        return new ResponseEntity<>(commnetService.createComment(postId, commentRequest), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(commnetService.getCommentsByPostId(postId));
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("postId") Long postId,
                                                     @PathVariable("commentId") Long commentId) {

        CommentDto commentDto = commnetService.getCommentById(postId,commentId);

        return ResponseEntity.ok(commentDto);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId") Long postId,
                                                    @PathVariable("commentId") Long commentId,
                                                    @RequestBody @Valid UpdateCommentRequest commentRequest) {

        CommentDto updatedComment = commnetService.updateComment(postId, commentId, commentRequest);

        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("postId") Long postId,
                                                @PathVariable("commentId") Long commentId) {

        commnetService.deleteComment(postId, commentId);

        return ResponseEntity.ok().body("Comment deleted successfully");
    }

}
