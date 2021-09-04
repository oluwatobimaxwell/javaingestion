package com.elastic.javaingestion.elastic;

import com.elastic.javaingestion.elastic.services.device.DVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/device")
public class DeviceController {
    private final DVService dvService;

    @Autowired
    public DeviceController(DVService dvService) {
        this.dvService = dvService;
    }

    @GetMapping("/generate")
    public void genData(){
        this.dvService.genData();
    }
}
