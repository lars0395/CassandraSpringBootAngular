package de.lars0395.github.model;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.List;

public class CassandraEntry {

    private String key;
    private Date timestamp;
    private ByteBuffer metadata;
    private ByteBuffer payload;
    private List<String> characteristics;
    private String localIp;
    private int localPort;
    private String remoteIp;
    private int remotePort;
    private String remoteUser;
    private String remotePath;

    public CassandraEntry() {
    }

    public CassandraEntry(String key, Date timestamp, ByteBuffer metadata, ByteBuffer payload, List<String> characteristics, String localIp, int localPort, String remoteIp, int remotePort, String remoteUser, String remotePath) {
        this.key = key;
        this.timestamp = timestamp;
        this.metadata = metadata;
        this.payload = payload;
        this.characteristics = characteristics;
        this.localIp = localIp;
        this.localPort = localPort;
        this.remoteIp = remoteIp;
        this.remotePort = remotePort;
        this.remoteUser = remoteUser;
        this.remotePath = remotePath;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public ByteBuffer getMetadata() {
        return metadata;
    }

    public void setMetadata(ByteBuffer metadata) {
        this.metadata = metadata;
    }

    public ByteBuffer getPayload() {
        return payload;
    }

    public void setPayload(ByteBuffer payload) {
        this.payload = payload;
    }

    public List<String> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<String> characteristics) {
        this.characteristics = characteristics;
    }

    public String getLocalIp() {
        return localIp;
    }

    public void setLocalIp(String localIp) {
        this.localIp = localIp;
    }

    public int getLocalPort() {
        return localPort;
    }

    public void setLocalPort(int localPort) {
        this.localPort = localPort;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    public void setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
    }

    public String getRemotePath() {
        return remotePath;
    }

    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }
}
