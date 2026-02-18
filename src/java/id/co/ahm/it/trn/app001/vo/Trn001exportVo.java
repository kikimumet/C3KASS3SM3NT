/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.it.trn.app001.vo;

/**
 *
 * @author wahyu.rm
 */
public class Trn001exportVo extends Trn001DetailVo {
    private String vmcode;
    private String vdesc;
    
    public void setMcode(String vmcode) {
        this.vmcode = vmcode;
    }
    
    public String getMcode() {
        return vmcode;
    }
    
    public void setMdesc(String vdesc) {
        this.vdesc = vdesc;
    }
    
    public String getMdesc() {
        return vdesc;
    }
}
