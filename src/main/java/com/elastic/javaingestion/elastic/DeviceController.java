package com.elastic.javaingestion.elastic;

import com.elastic.javaingestion.elastic.services.device.DVService;
import com.elastic.javaingestion.elastic.utils.DataGenerator;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/gen-phones")
    public void genPhone(){
        DataGenerator d = new DataGenerator();
        List<JSONObject> dx = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
//            JSONObject obj = d.randomePhoneName();
//            boolean check = dx.toString().contains(obj.getString("phone"));
//            while (check){
//                obj = d.randomePhoneName();
//                check = dx.toString().contains(obj.getString("phone"));
//            }
//          dx.add(d.randomePhoneName());
            System.out.println("SMS: "+d.getEnglishSMS());
        }
        System.out.println(dx.toString());
    }
}
