/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.it.trn.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author anjar
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "SC_TXNVHCLS")
public class ScTxnVhcls extends DefaultEntityImpl implements Serializable{
    
    @EmbeddedId
    private ScTxnVhclsPk pk;
    
    @Column(name = "VNAME")
    private String vname; // Nama Pemilik Kendaraan
    
    @Column(name = "VPHONE")
    private String vphone; // Nomor Telepon Pemilik
    
    @Column(name = "VADDRESS")
    private String vaddress; // Alamat Pemilik
    
    @Column(name = "VMMCTYPE")
    private String vmmctype; // Tipe Motor
    
    @Column(name = "VMCLRID")
    private String vmclrid; // Kode Warna Motor
    
    @Column(name = "DTRANS")
    private Date dtrans; // Tanggal Transaksi

    public ScTxnVhclsPk getScTxnVhclsPk() {
        return pk;
    }

    public void setScTxnVhclsPk(ScTxnVhclsPk scTxnVhclsPk) {
        this.pk = scTxnVhclsPk;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getVphone() {
        return vphone;
    }

    public void setVphone(String vphone) {
        this.vphone = vphone;
    }

    public String getVaddress() {
        return vaddress;
    }

    public void setVaddress(String vaddress) {
        this.vaddress = vaddress;
    }

    public String getVmmctype() {
        return vmmctype;
    }

    public void setVmmctype(String vmmctype) {
        this.vmmctype = vmmctype;
    }

    public String getVmclrid() {
        return vmclrid;
    }

    public void setVmclrid(String vmclrid) {
        this.vmclrid = vmclrid;
    }

    public Date getDtrans() {
        return dtrans;
    }

    public void setDtrans(Date dtrans) {
        this.dtrans = dtrans;
    }

}
