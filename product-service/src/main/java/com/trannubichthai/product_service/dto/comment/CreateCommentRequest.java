package com.trannubichthai.product_service.dto.comment;

import lombok.Getter;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class CreateCommentRequest {
    @NotNull
    private UUID productId;
    @NotNull
    private String text;
}
