package com.trannubichthai.product_service.service;

import com.trannubichthai.common.model.UserCredential;
import com.trannubichthai.product_service.dto.comment.CommentDTO;
import com.trannubichthai.product_service.dto.comment.CommentMapper;
import com.trannubichthai.product_service.dto.comment.CreateCommentRequest;
import com.trannubichthai.product_service.model.Comment;
import com.trannubichthai.product_service.model.Product;
import com.trannubichthai.product_service.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProductService productService;
    private final CommentMapper commentMapper;

    public CommentDTO createComment(CreateCommentRequest createCommentRequest) {

        Product product = productService.getProductById(createCommentRequest.getProductId());

        // UserCredential userCredential = (UserCredential)
        // SecurityContextHolder.getContext()
        // .getAuthentication().getCredentials();

        String creator = "william";

        Comment comment = Comment.builder()
                .product(product)
                .text(createCommentRequest.getText())
                // .creator(userCredential.getUsername())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .creator(creator)
                .build();

        return commentMapper.commentToCommentDTO(commentRepository.save(comment));
    }
}
