package com.neilo.sopromat.controller;

import com.neilo.sopromat.dto.HingeBeamDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculate")
@Slf4j
public class MainController {

    @PostMapping(value = "/hingeBeam")
    public void calculateHingeBeam(@RequestBody HingeBeamDto request) {
        System.out.println(request.toString());
    }

}
