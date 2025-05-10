package com.neilo.sopromat.service;

import com.neilo.sopromat.dto.HingeBeamDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.TreeMap;

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
        BigDecimal xm = hingeBeamDto.getMoment().getPosition();
        BigDecimal xa = hingeBeamDto.getPositionLeftBeamReaction();
        BigDecimal xb = hingeBeamDto.getPositionRightBeamReaction();

        BigDecimal q1 = q.multiply((xq2.subtract(xq1)));

        BigDecimal mfa = f.multiply(xb.subtract(xf));
        BigDecimal mqa = q1.multiply(xb.subtract(xq1.add((xq2.subtract(xq1)).divide(BigDecimal.TWO, 2, RoundingMode.HALF_UP))));

        BigDecimal ra = (mfa.add(mqa).subtract(m)).divide(xb.subtract(xa), 2, RoundingMode.HALF_UP);

        BigDecimal mfb = f.multiply(xf.subtract(xa));
        BigDecimal mqb = q1.multiply(xq1.add((xq2.subtract(xq1)).divide(BigDecimal.TWO, 2, RoundingMode.HALF_UP)));

        BigDecimal rb = (mfb.add(mqb).add(m)).divide(xb.subtract(xa), 2, RoundingMode.HALF_UP);

        System.out.println("xf = " + xf);
        System.out.println("q = " + q);
        System.out.println("xq1 = " + xq1);
        System.out.println("xq2 = " + xq2);
        System.out.println("m = " + m);
        System.out.println("xa = " + xa);
        System.out.println("xb = " + xb);
        System.out.println("====================");
        System.out.println("mfa = " + mfa);
        System.out.println("mqa = " + mqa);
        System.out.println("====================");
        System.out.println("mfb = " + mfb);
        System.out.println("mqb = " + mqb);
        System.out.println("====================");
        System.out.println("ra = " + ra);
        System.out.println("rb = " + rb);

        Map<BigDecimal, BigDecimal> diagramForces = new TreeMap<>();
        Map<BigDecimal, BigDecimal> diagramQ = new TreeMap<>();
        Map<BigDecimal, BigDecimal> diagramM = new TreeMap<>();

        diagramForces.put(xa, ra);
        diagramForces.put(xf, f.negate());
        diagramForces.put(xq1, q.negate());
        diagramForces.put(xq1.add((xq2.subtract(xq1)).divide(BigDecimal.TWO, 2, RoundingMode.HALF_UP)), q.negate());
        diagramForces.put(xq2, q.negate());
        diagramForces.put(xm, m);
        diagramForces.put(xb, rb);


        diagramForces.forEach((position, force) -> {
            double sumForces = diagramQ.values().stream().mapToDouble(BigDecimal::doubleValue).sum();
            diagramQ.put(position, BigDecimal.valueOf(sumForces).add(force));
        });

        System.out.println("diagramForces: " + diagramForces);
        System.out.println("diagramQ: " + diagramQ);

//        diagramQ.put(xa, ra);

    }

}
