package com.c4po.template.db.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 设备表
 * @author Lisa
 * @date 2018/8/22
 */

@Entity
public class DeviceEntity {


    /**
     * 设备ID【楼号ID+小区ID】
     */
    private String deviceId;

    /**
     * 设备名称，如：明发园12栋
     */
    private String deviceName;

    /**
     * 社区名称，如：明发园小区
     */
    private String communityName;

    /**
     * 楼号，如：12
     */
    private String buildingNumber;
    /**
     * 单元号，如：12
     */
    private String apartmentNumber;

    /**
     * 人脸识别激活码
     */
    private String faceSerialNumber;


    @Generated(hash = 831126042)
    public DeviceEntity(String deviceId, String deviceName, String communityName,
            String buildingNumber, String apartmentNumber,
            String faceSerialNumber) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.communityName = communityName;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
        this.faceSerialNumber = faceSerialNumber;
    }

    @Generated(hash = 1449836520)
    public DeviceEntity() {
    }


    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getFaceSerialNumber() {
        return faceSerialNumber;
    }

    public void setFaceSerialNumber(String faceSerialNumber) {
        this.faceSerialNumber = faceSerialNumber;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
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
}
