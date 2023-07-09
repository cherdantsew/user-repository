package com.deliverengine.user.model.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserAccountRequestDto {
    private Long id;
    private BigDecimal accountValue;
}
