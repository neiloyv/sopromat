package com.neilo.sopromat.service;

import com.neilo.sopromat.dto.HingeBeamDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Slf4j
public class HingeBeamServiceImpl implements HingeBeamService {

    @Override
    public void calculate(HingeBeamDto hingeBeamDto) {

        BigDecimal f = hingeBeamDto.getForce().getForce();
        BigDecimal xf = hingeBeamDto.getForce().getPosition();
        BigDecimal q = hingeBeamDto.getDistributedForce().getDistributedForce();
        BigDecimal xq1 = hingeBeamDto.getDistributedForce().getPositionStart();
        BigDecimal xq2 = hingeBeamDto.getDistributedForce().getPositionEnd();
        BigDecimal m = hingeBeamDto.getMoment().getMoment();
        BigDecimal xa = hingeBeamDto.getPositionLeftBeamReaction();
        BigDecimal xb = hingeBeamDto.getPositionRightBeamReaction();

        BigDecimal ra = (
                f.multiply(xb.subtract(xf))
                        .add(q.multiply((xq2.subtract(xq1)))
                                .divide(xb.subtract((xq2.subtract(xq1)).divide(BigDecimal.TWO, 2, RoundingMode.HALF_UP)), 2, RoundingMode.HALF_UP))
                        .add(m))
                .divide(xb.subtract(xa), 2, RoundingMode.HALF_UP);

        BigDecimal rb = (
                f.multiply(xf.subtract(xa))
                        .add(q.multiply((xq2.subtract(xq1)))
                                .divide(xq1.add((xq2.subtract(xq1)).divide(BigDecimal.TWO, 2, RoundingMode.HALF_UP)), 2, RoundingMode.HALF_UP))
                        .add(m)
        )
                .divide(xb.subtract(xa), 2, RoundingMode.HALF_UP);


    }

}
