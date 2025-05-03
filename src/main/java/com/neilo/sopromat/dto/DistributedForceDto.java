package com.neilo.sopromat.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class DistributedForceDto {

    private BigDecimal distributedForce;
    private BigDecimal positionStart;
    private BigDecimal positionEnd;

}
