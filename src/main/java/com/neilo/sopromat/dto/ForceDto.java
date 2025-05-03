package com.neilo.sopromat.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class ForceDto {

    private BigDecimal force;
    private BigDecimal position;

}
