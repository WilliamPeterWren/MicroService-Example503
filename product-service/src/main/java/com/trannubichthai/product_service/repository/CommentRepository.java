package com.trannubichthai.product_service.repository;

import com.trannubichthai.product_service.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
