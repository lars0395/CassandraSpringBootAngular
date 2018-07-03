package de.lars0395.github.model;

import java.util.Date;

public class CassandraEntry {

  private String key;
  private Date timestamp;
  private String metadata;
  private String payload;

  public CassandraEntry() {
  }

  public CassandraEntry(String key, Date timestamp, String metadata, String payload) {
    this.key = key;
    this.timestamp = timestamp;
    this.metadata = metadata;
    this.payload = payload;
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

  public String getMetadata() {
    return metadata;
  }

  public void setMetadata(String metadata) {
    this.metadata = metadata;
  }

  public String getPayload() {
    return payload;
  }

  public void setPayload(String payload) {
    this.payload = payload;
  }
}
