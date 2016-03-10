package com.github.zjiajun.java.core.algorithm.consistent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by zhujiajun
 * 15/12/29 21:11
 */
public class TestConsistentHash {

    public static void main(String[] args) {
        String ip = "192.168.1.";
        HashMap<String,Integer> map = new HashMap<>();
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            map.put(ip + i,0);
            Node node = new Node("node" + 0,ip + i);
            nodes.add(node);
        }

        ConsistentHash<Node> consistentHash = new ConsistentHash<>(nodes);
        for (int i = 0; i < 5000; i++) {
            String data = UUID.randomUUID().toString() + i;
            Node node = consistentHash.get(data);
            String nodeIp = node.getIp();
            map.put(nodeIp,map.get(nodeIp) + 1);
        }

        for (int i = 1; i <= 10; i++) {
            Integer number = map.get(ip + i);
            System.out.println(ip + i + "节点记录条数:" + number);
        }
    }
}
