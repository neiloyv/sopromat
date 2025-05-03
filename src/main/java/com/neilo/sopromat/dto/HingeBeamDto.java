package com.neilo.sopromat.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class HingeBeamDto {

    private BigDecimal lengthBeam;
    private BigDecimal positionLeftBeamReaction;
    private BigDecimal positionRightBeamReaction;
    private ForceDto force;
    private DistributedForceDto distributedForce;
    private MomentDto moment;

//    private List<ForceDto> forces;
//    private List<DistributedForceDto> distributedForces;
//    private List<MomentDto> moments;

}
