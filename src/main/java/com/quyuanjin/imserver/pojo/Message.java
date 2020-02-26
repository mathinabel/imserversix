package com.quyuanjin.imserver.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    private String sendID;
    private String receiveId;
    private String msg;
    private String createTime;
    private String localPath;
    private String netPath;
    private int contentType;
    private float recorderTime;
    private String sendSucceedType;
    private String readType;

    String receiverTime;
    String readState;
    String sendState;

    public Message() {
    }

    public Message(String sendID, String receiveId, String msg, String createTime, String localPath, String netPath, int contentType, float recorderTime, String sendSucceedType, String readType, String receiverTime, String readState, String sendState) {
        this.sendID = sendID;
        this.receiveId = receiveId;
        this.msg = msg;
        this.createTime = createTime;
        this.localPath = localPath;
        this.netPath = netPath;
        this.contentType = contentType;
        this.recorderTime = recorderTime;
        this.sendSucceedType = sendSucceedType;
        this.readType = readType;
        this.receiverTime = receiverTime;
        this.readState = readState;
        this.sendState = sendState;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSendID() {
        return sendID;
    }

    public void setSendID(String sendID) {
        this.sendID = sendID;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getNetPath() {
        return netPath;
    }

    public void setNetPath(String netPath) {
        this.netPath = netPath;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public float getRecorderTime() {
        return recorderTime;
    }

    public void setRecorderTime(float recorderTime) {
        this.recorderTime = recorderTime;
    }

    public String getSendSucceedType() {
        return sendSucceedType;
    }

    public void setSendSucceedType(String sendSucceedType) {
        this.sendSucceedType = sendSucceedType;
    }

    public String getReadType() {
        return readType;
    }

    public void setReadType(String readType) {
        this.readType = readType;
    }

    public String getReceiverTime() {
        return receiverTime;
    }

    public void setReceiverTime(String receiverTime) {
        this.receiverTime = receiverTime;
    }

    public String getReadState() {
        return readState;
    }

    public void setReadState(String readState) {
        this.readState = readState;
    }

    public String getSendState() {
        return sendState;
    }

    public void setSendState(String sendState) {
        this.sendState = sendState;
    }
}
