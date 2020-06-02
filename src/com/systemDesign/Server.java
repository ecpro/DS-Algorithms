package com.systemDesign;

public class Server {

    final String ipAddress;
    private Boolean isVirtual = Boolean.FALSE;

    public Server(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public static Server getVirtualServer(Server server) {
        Server serverV = new Server(server.ipAddress);
        serverV.isVirtual = Boolean.TRUE;
        return serverV;
    }

    @Override
    public String toString() {
        return "Server{" +
                "ipAddress='" + ipAddress + '\'' +
                ", isVirtual=" + isVirtual +
                '}';
    }
}
