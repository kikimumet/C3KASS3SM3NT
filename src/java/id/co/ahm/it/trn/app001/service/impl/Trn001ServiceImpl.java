/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.it.trn.app001.service.impl;

import id.co.ahm.it.trn.app001.dao.Trn001Dao;
import id.co.ahm.it.trn.app001.service.Trn001Service;
import id.co.ahm.it.trn.app001.vo.Trn001DetailVo;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.it.trn.app000.model.ScTxnVhcls;
import id.co.ahm.it.trn.app000.model.ScTxnVhclsPk;
import id.co.ahm.it.trn.app001.vo.Trn001Filter;
import id.co.ahm.it.trn.app001.vo.Trn001Lov;
import id.co.ahm.it.trn.app001.vo.Trn001SummaryVo;
import id.co.ahm.jxf.util.DtoHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fadli
 */

@Service("trn001Service")
@Transactional
public class Trn001ServiceImpl implements Trn001Service {

    @Autowired
    @Qualifier(value = "trn001Dao")
    private Trn001Dao trn001Dao;
    
    Map<String, Object> msg = new HashMap<>();
    
    @Override
    public DtoResponse getDetailDataKendaraan(String key) {
        Trn001DetailVo vo = trn001Dao.getDetailDataKendaraan(key);
        List<Trn001DetailVo> data = new ArrayList<>();
        if (vo != null) {
            data.add(vo);
        }
        
        if (vo != null) {
            msg.put("message", "Data berhasil ditemukan");
            return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, msg, data);
        } else {
            msg.put("message", "Data tidak berhasil ditemukan");
            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
        }
    }
    
    @Override
    public DtoResponse getDataKendaraan(Trn001Filter filter, DtoParamPaging dtoParamPaging) {
        try {

          int count = trn001Dao.getCountKendaraan(dtoParamPaging, filter);
          List<Trn001SummaryVo> data = trn001Dao.getDataKendaraan(dtoParamPaging, filter);

          if (count > 0) {
              msg.put("message", "Data ditemukan");
              return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, msg, data, count);
          } else {
              msg.put("message", "Data tidak ditemukan");
              return DtoHelper.constructResponsePaging(StatusMsgEnum.GAGAL, msg, data, count);
          }

      } catch (Exception e) {
          e.printStackTrace();
          msg.put("error", e.getMessage());
          return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
      }
    }
    
    @Override
    public DtoResponse getLovKendaraan(String keyword, DtoParamPaging dto) {
        Map<String, Object> msg = new HashMap<>();
        
        List<Trn001Lov> vo = trn001Dao.getLovKendaraan(keyword, dto);
        
        if (vo != null) {
            msg.put("message", "Data berhasil ditemukan");
            return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, msg, vo);
        } else {
            msg.put("message", "Data tidak ditemukan");
            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
        }
    }
    
    @Override
    public DtoResponse getLovWarna(String keyword, DtoParamPaging dto) {        
        List<Trn001Lov> vo = trn001Dao.getLovWarnaKendaraan(keyword, dto);
        
        if (vo != null) {
            msg.put("message", "Data berhasil ditemukan");
            return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, msg, vo);
        } else {
            msg.put("message", "Data tidak ditemukan");
            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
        }
    }
    
    @Override
    public DtoResponse saveDataKendaraan(Trn001DetailVo param) {
        Map<String, Object> msg = new HashMap<>();
        
        try {
            String vkey = param.getKey_access();
            String noreg = param.getRegistration_number();
            String noframe = param.getFrame_number();
            String noengine = param.getEngine_number();
            String tipemotor = param.getTipe_motor();
            String namapemilik = param.getNama_pemilik();
            String notelepon = param.getNomor_telefon();
            String alamat = param.getAddress();
            String warna = param.getKode_warna();
            Date tanggaltransaksi = new Date();

            ScTxnVhclsPk pk = new ScTxnVhclsPk();
            pk.setVkey(vkey);
            pk.setVengno(noengine);
            pk.setVfrmno(noframe);
            pk.setVnoregist(noreg);

            ScTxnVhcls p = new ScTxnVhcls();
            p.setScTxnVhclsPk(pk);
            p.setVname(namapemilik);
            p.setVphone(notelepon);
            p.setVmmctype(tipemotor);
            p.setVaddress(alamat);
            p.setVmclrid(warna);
            p.setDtrans(tanggaltransaksi);
            
            trn001Dao.save(p);
            trn001Dao.flush();
            
            msg.put("message", "Data berhasil disimpan");
            return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, msg, null);
        } catch(Exception e) {
            msg.put("message", "Data gagal disimpan, error " + e.getMessage());
            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
        }
    }
    
    @Override
    public DtoResponse updateDataKendaraan(Trn001DetailVo param, String key) {
        Map<String, Object> msg = new HashMap<>();
        ScTxnVhclsPk pk = new ScTxnVhclsPk();
        pk.setVkey(param.getKey_access());
        pk.setVengno(param.getEngine_number());
        pk.setVfrmno(param.getFrame_number());
        pk.setVnoregist(param.getRegistration_number());
        ScTxnVhcls entity = trn001Dao.findOne(pk);
        
        System.out.println("3. Entity found: " + (entity != null));
        
        if (entity != null) {
            entity.setVname(param.getNama_pemilik());
            entity.setVphone(param.getNomor_telefon());
            entity.setVaddress(param.getAddress());
            entity.setVmmctype(param.getTipe_motor());
            entity.setVmclrid(param.getKode_warna());
            
            try {
                trn001Dao.update(entity);
                trn001Dao.flush();
                
                msg.put("message", "Data berhasil diperbarui");
                return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, msg, null);
            } catch(Exception e) {
                msg.put("message", "Data gagal diperbarui, error " + e.getMessage());
                return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
            }
        } else {
            msg.put("message", "Data tidak ditemukan");
            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
        }
    }
    
    @Override 
    public DtoResponse deleteDataKendaraan(Trn001DetailVo param, String key) {
        Map<String, Object> msg = new HashMap<>();
        
        ScTxnVhclsPk pk = new ScTxnVhclsPk();
        pk.setVkey(param.getKey_access());
        pk.setVengno(param.getEngine_number());
        pk.setVfrmno(param.getFrame_number());
        pk.setVnoregist(param.getRegistration_number());
        
        ScTxnVhcls entity = trn001Dao.findOne(pk);
        
        if (entity != null) {
            try {
                trn001Dao.delete(entity);
                trn001Dao.flush();
                
                msg.put("message", "Data berhasil dihapus");
                return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, msg, null);
            } catch (Exception e) {
                msg.put("message", "Data gagal diperbarui, error " + e.getMessage());
                return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
            }
        } else {
            msg.put("message", "Data tidak ditemukan");
            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
        }
    }
}
