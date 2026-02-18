/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.it.trn.app001.vo;

import java.util.Date;

/**
 *
 * @author fadli
 */
public class Trn001Filter {
    private Date fromDate;
    private Date toDate;
    
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }
    
    public Date getFromDate() {
        return fromDate;
    }
    
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
    
    public Date getToDate() {
        return toDate;
    }
}
