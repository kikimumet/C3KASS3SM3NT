/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.it.trn.app000.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author anjar
 */
@Embeddable
public class ScTxnVhclsPk implements Serializable {
    
    
    @Column(name = "VKEY")
    private String vkey;
    
    @Column(name = "VNOREGIST")
    private String vnoregist;
    
    @Column(name = "VFRMNO")
    private String vfrmno;
    
    @Column(name = "VENGNO")
    private String vengno;

    public String getVkey() {
        return vkey;
    }

    public void setVkey(String vkey) {
        this.vkey = vkey;
    }

    public String getVnoregist() {
        return vnoregist;
    }

    public void setVnoregist(String vnoregist) {
        this.vnoregist = vnoregist;
    }

    public String getVfrmno() {
        return vfrmno;
    }

    public void setVfrmno(String vfrmno) {
        this.vfrmno = vfrmno;
    }

    public String getVengno() {
        return vengno;
    }

    public void setVengno(String vengno) {
        this.vengno = vengno;
    }
}
