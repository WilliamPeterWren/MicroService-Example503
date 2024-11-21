package com.trannubichthai.orderservice.dto.orderaddress;

import lombok.*;
import jakarta.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateOrderAddressRequest {
    @NotNull
    private String city;
    @NotNull
    private String district;
    @NotNull
    private String addressDetail;
}