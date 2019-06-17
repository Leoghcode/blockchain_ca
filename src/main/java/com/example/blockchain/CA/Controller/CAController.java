package com.example.blockchain.CA.Controller;

import com.example.blockchain.CA.Entity.CAEntity;
import com.example.blockchain.CA.Service.CAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("CA")
public class CAController {
    @Autowired
    private CAService caService;


    @RequestMapping(value = "register", method = RequestMethod.POST)
    public CAEntity register(@RequestBody Map<String, String> request) {
        String addr = request.get("address").toString();
        System.out.println("in register");
        System.out.println(addr);
        return caService.register(addr);
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public CAEntity getKeys(@RequestBody Map<String, String> request) {
        String addr = request.get("address").toString();
        return caService.getKeys(addr);
    }
}
