/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.it.trn.app001.dao;
import java.util.List;
import id.co.ahm.it.trn.app000.model.ScTxnVhcls;
import id.co.ahm.it.trn.app000.model.ScTxnVhclsPk;
import id.co.ahm.it.trn.app001.vo.Trn001DetailVo;
import id.co.ahm.it.trn.app001.vo.Trn001Filter;
import id.co.ahm.it.trn.app001.vo.Trn001Lov;
import id.co.ahm.it.trn.app001.vo.Trn001SummaryVo;
import id.co.ahm.jxf.dao.DefaultDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
/**
 *
 * @author fadli
 */
public interface Trn001Dao extends DefaultDao<ScTxnVhcls, ScTxnVhclsPk> {
    public int getCountKendaraan(DtoParamPaging dto, Trn001Filter filter);
    public List<Trn001SummaryVo> getDataKendaraan(DtoParamPaging dto, Trn001Filter filter);
    public List<Trn001Lov> getLovKendaraan(String keyword, DtoParamPaging dto); 
    public List<Trn001Lov> getLovWarnaKendaraan(String keyword, DtoParamPaging dto);
    public Trn001DetailVo getDetailDataKendaraan(String key); 
}
