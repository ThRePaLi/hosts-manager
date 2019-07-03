package me.chenjp.hostsmanager.utils;

public enum  OSTypes {

    WIN("windows", "C:\\Windows\\System32\\drivers\\etc\\hosts");
    private String osType;
    private String path;

    OSTypes(String osType, String path){
        this.osType = osType;
        this.path = path;
    }
}
