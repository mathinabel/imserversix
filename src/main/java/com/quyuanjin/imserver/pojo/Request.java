package com.quyuanjin.imserver.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Request {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    String applyKindId;
    String createAt;
    String senderId;
    String ReceiverId;
    String description;
    String type;
    String updateAt;

    public Request(String applyKindId, String createAt, String senderId, String receiverId, String description, String type, String updateAt) {
        this.applyKindId = applyKindId;
        this.createAt = createAt;
        this.senderId = senderId;
        ReceiverId = receiverId;
        this.description = description;
        this.type = type;
        this.updateAt = updateAt;
    }

    public Request() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplyKindId() {
        return applyKindId;
    }

    public void setApplyKindId(String applyKindId) {
        this.applyKindId = applyKindId;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return ReceiverId;
    }

    public void setReceiverId(String receiverId) {
        ReceiverId = receiverId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
