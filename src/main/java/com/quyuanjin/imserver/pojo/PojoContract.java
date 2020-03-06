package com.quyuanjin.imserver.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PojoContract {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    private String myUserId;//实际上是数字userid
    private String contractUserId;
    private   String portraitImageView;//头像
    private   String nameTextView;//用户昵称（自己设定的优先）
    private  String contentTextView;//个性签名
    private  String sex;
    private  String userid;
    private String tag;
    private String pinyin;

    public PojoContract() {
    }

    public PojoContract(String myUserId, String contractUserId, String portraitImageView, String nameTextView, String contentTextView, String sex, String userid, String tag, String pinyin) {
        this.myUserId = myUserId;
        this.contractUserId = contractUserId;
        this.portraitImageView = portraitImageView;
        this.nameTextView = nameTextView;
        this.contentTextView = contentTextView;
        this.sex = sex;
        this.userid = userid;
        this.tag = tag;
        this.pinyin = pinyin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMyUserId() {
        return myUserId;
    }

    public void setMyUserId(String myUserId) {
        this.myUserId = myUserId;
    }

    public String getContractUserId() {
        return contractUserId;
    }

    public void setContractUserId(String contractUserId) {
        this.contractUserId = contractUserId;
    }

    public String getPortraitImageView() {
        return portraitImageView;
    }

    public void setPortraitImageView(String portraitImageView) {
        this.portraitImageView = portraitImageView;
    }

    public String getNameTextView() {
        return nameTextView;
    }

    public void setNameTextView(String nameTextView) {
        this.nameTextView = nameTextView;
    }

    public String getContentTextView() {
        return contentTextView;
    }

    public void setContentTextView(String contentTextView) {
        this.contentTextView = contentTextView;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
