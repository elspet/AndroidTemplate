package com.c4po.template.db.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 用户信息表
 * @author Lisa
 * @date 2018/8/22
 */
@Entity
public class UserInfoEntity {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 性别
     */
    private String gender;

    /**
     * IC卡号
     */
    private String icCardNumber;

    /**
     * 社区名称，如：明发园小区
     */
    private String communityName;

    /**
     * 楼号，如：12
     */
    private String buildingNumber;

    /**
     * 房间号，如：520
     */
    private String roomNumber;

    /**
     * 设备ID【楼号ID+小区ID】
     */
    private String deviceId;

    /**
     * 上次进门时间
     */
    private long lastEnterTime;


    @Generated(hash = 544179055)
    public UserInfoEntity(String userId, String userName, String gender,
            String icCardNumber, String communityName, String buildingNumber,
            String roomNumber, String deviceId, long lastEnterTime) {
        this.userId = userId;
        this.userName = userName;
        this.gender = gender;
        this.icCardNumber = icCardNumber;
        this.communityName = communityName;
        this.buildingNumber = buildingNumber;
        this.roomNumber = roomNumber;
        this.deviceId = deviceId;
        this.lastEnterTime = lastEnterTime;
    }

    @Generated(hash = 2042969639)
    public UserInfoEntity() {
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIcCardNumber() {
        return icCardNumber;
    }

    public void setIcCardNumber(String icCardNumber) {
        this.icCardNumber = icCardNumber;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public long getLastEnterTime() {
        return lastEnterTime;
    }

    public void setLastEnterTime(long lastEnterTime) {
        this.lastEnterTime = lastEnterTime;
    }
}
