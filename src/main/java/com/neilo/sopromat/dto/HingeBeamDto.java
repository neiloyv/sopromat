package com.neilo.sopromat.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class HingeBeamDto {

    private List<ForceDto> forces;
    private List<DistributedForceDto> distributedForces;
    private List<MomentDto> moments;

}
