/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.it.trn.app001.vo;

/**
 *
 * @author fadli
 */
import java.util.Date;

public class Trn001ExportVo {
    
    private String registrationNumber;
    private String ownerName;
    private String phoneNumber;
    private String address;
    private Date transactionDate;
    private String motorcycleCode;
    private String motorcycleDescription;
    private String colorCode;
    private String colorDescription;
    private String frameNumber;
    private String engineNumber;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getMotorcycleCode() {
        return motorcycleCode;
    }

    public void setMotorcycleCode(String motorcycleCode) {
        this.motorcycleCode = motorcycleCode;
    }

    public String getMotorcycleDescription() {
        return motorcycleDescription;
    }

    public void setMotorcycleDescription(String motorcycleDescription) {
        this.motorcycleDescription = motorcycleDescription;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorDescription() {
        return colorDescription;
    }

    public void setColorDescription(String colorDescription) {
        this.colorDescription = colorDescription;
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }
}