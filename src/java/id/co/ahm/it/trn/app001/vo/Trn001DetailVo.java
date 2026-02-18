/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.it.trn.app001.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
/**
 *
 * @author wahyu.rm
 */
public class Trn001DetailVo {
    
    // PK
    private String key_access;
    private String registration_number;
    private String frame_number;
    private String engine_number;
    
    // Entities
    private String nama_pemilik;
    private String nomor_telefon;
    private String tipe_motor;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date tanggal_transaksi;
    private String kode_warna;
    private String address;
    private String description;
    private String merkKendaraan;
    
    private String keyword;
    
    // PK
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
   
    public void setFrame_number(String nomor_rangka) {
       this.frame_number = nomor_rangka;
   }
   
   public String getFrame_number() {
       return frame_number;
   }
   
   public void setEngine_number(String nomor_mesin) {
       this.engine_number = nomor_mesin;
   }
   
   public String getEngine_number() {
       return engine_number;
   }
   
   // Entities
   public void setNama_pemilik(String nama_pemilik) {
       this.nama_pemilik = nama_pemilik;
   }
   
   public String getNama_pemilik() {
       return nama_pemilik;
   }
   
   public void setNomor_telefon(String nomortelefon_pemilik) {
       this.nomor_telefon = nomortelefon_pemilik;
   }
   
   public String getNomor_telefon() {
       return nomor_telefon;
   }
   
   public void setTipe_motor(String tipe_motor) {
       this.tipe_motor = tipe_motor;
   }
   
   public String getTipe_motor() {
       return tipe_motor;
   }
   
   public void setKode_warna(String kode_warna) {
       this.kode_warna = kode_warna;
   }
   
   public String getKode_warna() {
       return kode_warna;
   }
   
   public void setTanggal_transaksi(Date tanggal_transaksi) {
       this.tanggal_transaksi = tanggal_transaksi;
   }
   
   public Date getTanggal_transaksi() {
       return tanggal_transaksi;
   }

   public void setAddress(String address) {
       this.address = address;
   }
   
   public String getAddress() {
       return address;
   }
   
   public void setDescription(String description) {
       this.description = description;
   }
   
   public String getDescription() {
       return description;
   }
   
   public void setMerkKendaraan(String merkKendaraan) {
       this.merkKendaraan = merkKendaraan;
   }
   
   public String getMerkKendaraan() {
       return merkKendaraan;
   }
   
   public void setKeyword(String keyword) {
       this.keyword = keyword;
   }
   
   public String getKeyword() {
       return keyword;
   }
}
