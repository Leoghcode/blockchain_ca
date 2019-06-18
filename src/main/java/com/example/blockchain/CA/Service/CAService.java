package com.example.blockchain.CA.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.blockchain.CA.Entity.CAEntity;
import com.example.blockchain.CA.Entity.PublicCAEntity;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;


import java.io.*;
import java.util.*;

@Service
public class CAService {
    private Map<String, CAEntity> clients = new HashMap<>();
    private List<PublicCAEntity> pubClients = new ArrayList<>();
    private static String FILE_PATH = ResourceUtils.FILE_URL_PREFIX + "clients.json";

    public CAService() {
        loadClients();
    }

    public void loadClients() {
        try {
            File file = ResourceUtils.getFile(FILE_PATH);
            InputStream is = new FileInputStream(file);
            String clients = IOUtils.toString(is, "utf8");
            is.close();
            JSONObject jsonObject = JSONObject.parseObject(clients);
            System.out.println(jsonObject);
            for(String addr : jsonObject.keySet()) {
                CAEntity caEntity = JSONObject.parseObject(jsonObject.get(addr).toString(), CAEntity.class);
                if(!this.clients.containsKey(addr)) {
                    this.clients.put(addr, caEntity);
                    this.pubClients.add(new PublicCAEntity(caEntity));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean saveClients() {
        try {
            File file = ResourceUtils.getFile(FILE_PATH);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(JSON.toJSONString(clients, true));
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
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
