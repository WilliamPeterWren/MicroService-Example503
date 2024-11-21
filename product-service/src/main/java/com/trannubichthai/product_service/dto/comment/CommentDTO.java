package com.trannubichthai.product_service.dto.comment;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private UUID id;
    private String createdBy;
    private LocalDateTime createdDate;
    private String text;
    private String creator;
}
