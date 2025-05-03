package com.neilo.sopromat.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class MomentDto {

    private BigDecimal moment;
    private BigDecimal position;

}
