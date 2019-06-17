package com.example.blockchain.CA.Service;

import com.example.blockchain.CA.Entity.CAEntity;
import com.example.blockchain.CA.Entity.PublicCAEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CAService {
    private Map<String, CAEntity> clients = new HashMap<>();
    private List<PublicCAEntity> pubClients = new ArrayList<>();

    public CAEntity register(String address) {
        if(clients.get(address) != null) {
            return clients.get(address);
        } else {
            Map<String, String> keys = KeyUtil.getSHAKeys();
            CAEntity node = new CAEntity(address, keys.get("public_key"), keys.get("private_key"));
            clients.put(address, node);
            pubClients.add(new PublicCAEntity(node));
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

    public List<PublicCAEntity> getAllClients() {
        return pubClients;
    }

    public PublicCAEntity getClientByName(String name) {
        for(PublicCAEntity publicCAEntity: pubClients) {
            if(publicCAEntity.getName().equals(name)) {
                return publicCAEntity;
            }
        }
        return new PublicCAEntity("", "", "");
    }
}
