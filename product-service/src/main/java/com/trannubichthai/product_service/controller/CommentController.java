package com.trannubichthai.product_service.controller;

import com.trannubichthai.product_service.dto.comment.CommentDTO;
import com.trannubichthai.product_service.dto.comment.CreateCommentRequest;
import com.trannubichthai.product_service.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CreateCommentRequest createCommentRequest) {

        CommentDTO commentDTO = commentService.createComment(createCommentRequest);
        return new ResponseEntity<>(commentDTO, HttpStatus.CREATED);
    }
}
