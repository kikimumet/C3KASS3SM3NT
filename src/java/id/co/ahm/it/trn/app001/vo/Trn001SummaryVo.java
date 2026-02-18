/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.it.trn.app001.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Date;

/**
 *
 * @author wahyu.rm
 */

@JsonPropertyOrder({"key_access", "nama_pemilik", "registration_number", "nomor_telefon", "merek_kendaraan", "tanggal_transaksi"})
public class Trn001SummaryVo {
    
    
    private String key_access;
    private String nama_pemilik;
    private String registration_number;
    private String nomor_telefon;
    private String merek_kendaraan;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    private Date tanggal_transaksi;
    
    public void setKey_access(String key_access) {
        this.key_access = key_access;
    }
    
    public String getKey_access() {
        return key_access;
    }
    
    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }
    
    public String getRegistration_number() {
        return registration_number;
    }
    
    public void setNama_pemilik(String nama_pemilik) {
        this.nama_pemilik = nama_pemilik;
    }
    
    public String getNama_pemilik() {
        return nama_pemilik;
    }
    
    public void setNomor_telefon(String nomor_telefon) {
        this.nomor_telefon = nomor_telefon;
    }
    
    public String getNomor_telefon() {
        return nomor_telefon;
    }
    
    public void setMerek_kendaraan(String merek_kendaraan) {
        this.merek_kendaraan = merek_kendaraan;
    }
    
    public String getMerek_kendaraan() {
        return merek_kendaraan;
    }
    
    public void setTanggal_transaksi(Date tanggal_transaksi) {
        this.tanggal_transaksi = tanggal_transaksi;
    }
    
    public Date getTanggal_transaksi() {
        return tanggal_transaksi;
    }
}
