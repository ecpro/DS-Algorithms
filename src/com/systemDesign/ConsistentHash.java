package com.systemDesign;

import java.util.*;
import java.util.zip.CRC32;

public class ConsistentHash {

    private Integer numVirtualNodes;
    private SortedMap<Long, Server> serverRing;

    public ConsistentHash(Integer numVirtualNodes, Collection<Server> servers) {
        this.numVirtualNodes = numVirtualNodes;
        serverRing = new TreeMap<>();
        servers.forEach(server -> {
            Long key = hash(server.ipAddress);
            serverRing.put(key, server);
            for(int i = 1; i < numVirtualNodes; i++) {
                key = hash(server.ipAddress + i);
                serverRing.put(key, Server.getVirtualServer(server));
            }
        });
    }

    public Server get(String key) {
        if (serverRing.isEmpty()) return null;
        Long hash = hash(key);
        SortedMap<Long, Server> tailMap = serverRing.tailMap(hash);
        Long serverAddressKey = tailMap.isEmpty() ? serverRing.firstKey() : tailMap.firstKey();
        return serverRing.get(serverAddressKey);
    }

    public void addServer(Server server) {
        Long hash = hash(server.ipAddress);
        serverRing.put(hash, server);
        for (int i = 1; i < numVirtualNodes; i++) {
            Server virtualServer = Server.getVirtualServer(server);
            Long key = hash(server.ipAddress + i);
            serverRing.put(key, virtualServer);
        }
    }

    public void removeServer(Server server) {
        for(int i = 1; i < numVirtualNodes; i++) {
            Long hash = hash(server.ipAddress + i);
            serverRing.remove(hash);
        }
    }

    private Long hash(String key) {
        CRC32 crc32 = new CRC32();
        crc32.update(key.getBytes());
        return crc32.getValue();
    }

    public static void main(String[] args) {
        List<Server> servers = new ArrayList<>();
        servers.add(new Server("10.0.11.15"));
        servers.add(new Server("10.0.11.17"));
        servers.add(new Server("10.0.11.19"));
        servers.add(new Server("10.0.11.21"));
        servers.add(new Server("10.0.11.20"));

        ConsistentHash consistentHashObj = new ConsistentHash(10, servers);
        System.out.println(consistentHashObj.get("626"));
        System.out.println(consistentHashObj.get("ahfafga"));
        System.out.println(consistentHashObj.get("ahfa2838fga"));
        System.out.println(consistentHashObj.get("ahf33626afga"));
        System.out.println(consistentHashObj.get("ahfafga"));
        System.out.println(consistentHashObj.get("13672"));
        System.out.println(consistentHashObj.get("24727"));
        System.out.println(consistentHashObj.get("622asfaa26"));
        System.out.println(consistentHashObj.get("qweq627h"));
    }
}
