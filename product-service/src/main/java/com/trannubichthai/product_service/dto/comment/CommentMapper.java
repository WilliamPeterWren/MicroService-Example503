package com.trannubichthai.product_service.dto.comment;

import com.trannubichthai.product_service.model.Comment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentMapper {
    public CommentDTO commentToCommentDTO(Comment comment) {

        return CommentDTO.builder()
                .id(comment.getId())
                .createdDate(comment.getCreatedDate())
                .createdBy(comment.getCreatedBy())
                .text(comment.getText())
                .creator(comment.getCreator())
                .build();
    }
}
