package com.example.blockchain.CA.Controller;

import com.example.blockchain.CA.Entity.CAEntity;
import com.example.blockchain.CA.Entity.PublicCAEntity;
import com.example.blockchain.CA.Service.CAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<PublicCAEntity> getAllEntity() {
        return caService.getAllClients();
    }

    @RequestMapping(value = "getByName", method = RequestMethod.GET)
    public PublicCAEntity getAllEntity(@RequestParam String name) {
        return caService.getClientByName(name);
    }

    @RequestMapping(value = "save", method = RequestMethod.GET)
    public HttpStatus saveClients() {
        if(caService.saveClients()) {
            return HttpStatus.OK;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
