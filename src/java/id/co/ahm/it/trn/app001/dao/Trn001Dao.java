/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.it.trn.app001.dao;

/**
 *
 * @author fadli
 */

import java.util.List;
import id.co.ahm.it.trn.app000.model.ScTxnVhcls;
import id.co.ahm.it.trn.app000.model.ScTxnVhclsPk;
import id.co.ahm.it.trn.app001.vo.Trn001ExportVo;
import id.co.ahm.it.trn.app001.vo.Trn001Filter;
import id.co.ahm.it.trn.app001.vo.Trn001Lov;
import id.co.ahm.it.trn.app001.vo.Trn001SummaryVo;
import id.co.ahm.jxf.dao.DefaultDao;
import id.co.ahm.jxf.dto.DtoParamPaging;

public interface Trn001Dao extends DefaultDao<ScTxnVhcls, ScTxnVhclsPk> {
    
    public List<Trn001SummaryVo> getVehicleList(DtoParamPaging dto, Trn001Filter filter);
    
    public List<Trn001Lov> getVehicleLov(String keyword, DtoParamPaging dto); 
    
    public List<Trn001Lov> getVehicleColorLov(String keyword, DtoParamPaging dto);
    
    public int getVehicleCount(DtoParamPaging dto, Trn001Filter filter);
    
    public int getVehicleLovCount(String keyword);
    
    public int getVehicleColorLovCount(String keyword);
    
    List<Trn001ExportVo> getVehicleListExport(Trn001Filter filter);

}