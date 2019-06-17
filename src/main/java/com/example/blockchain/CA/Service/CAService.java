package com.example.blockchain.CA.Service;

import com.example.blockchain.CA.Entity.CAEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CAService {
    private Map<String, CAEntity> clients = new HashMap<>();

    public CAEntity register(String address) {
        if(clients.get(address) != null) {
            return clients.get(address);
        } else {
            Map<String, String> keys = KeyUtil.getSHAKeys();
            CAEntity node = new CAEntity(keys.get("public_key"), keys.get("private_key"));
            clients.put(address, node);
            return node;
        }
    }

    public CAEntity getKeys(String address) {
        CAEntity node = clients.get(address);
        if(node == null) {
            node = new CAEntity("", "");
        }
        return node;
    }
}
