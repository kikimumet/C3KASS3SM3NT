/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.it.trn.app001.service.impl;

/**
 *
 * @author fadli
 */

import id.co.ahm.it.trn.app001.dao.Trn001Dao;
import id.co.ahm.it.trn.app001.dto.BaseResponseDto;
import id.co.ahm.it.trn.app001.service.Trn001Service;
import id.co.ahm.it.trn.app001.vo.Trn001DetailVo;
import id.co.ahm.it.trn.app001.vo.Trn001Filter;
import id.co.ahm.it.trn.app001.vo.Trn001Lov;
import id.co.ahm.it.trn.app001.vo.Trn001SummaryVo;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.List;
import id.co.ahm.it.trn.app000.model.ScTxnVhcls;
import id.co.ahm.it.trn.app000.model.ScTxnVhclsPk;
import id.co.ahm.it.trn.app001.vo.Trn001ExportVo;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("trn001Service")
@Transactional
public class Trn001ServiceImpl implements Trn001Service {

    @Autowired
    @Qualifier("trn001Dao")
    private Trn001Dao trn001Dao;

    // GET LIST
    @Override
    public BaseResponseDto<?> getVehicles(Trn001Filter filter, DtoParamPaging paging) {
        try {

            List<Trn001SummaryVo> list = trn001Dao.getVehicleList(paging, filter);
            int count = trn001Dao.getVehicleCount(paging, filter);

            Map<String, Object> result = new HashMap<>();
            result.put("list", list);
            result.put("count", count);

            return BaseResponseDto.success("Data kendaraan ditemukan", result);

        } catch (Exception e) {
            return BaseResponseDto.error("Gagal mengambil data kendaraan : " + e.getMessage(), null);
        }
    }

    // LOV VEHICLE
    @Override
    public BaseResponseDto<?> getVehicleLov(String keyword, DtoParamPaging paging) {
        try {

            List<Trn001Lov> list = trn001Dao.getVehicleLov(keyword, paging);
            int count = trn001Dao.getVehicleLovCount(keyword);

            if (list == null || list.isEmpty()) {
                return BaseResponseDto.error("Data kendaraan tidak ditemukan", null);
            }

            Map<String, Object> result = new HashMap<>();
            result.put("count", count);
            result.put("list", list);

            return BaseResponseDto.success("Data kendaraan ditemukan", result);

        } catch (Exception e) {
            return BaseResponseDto.error("Gagal mengambil LOV kendaraan : " + e.getMessage(), null);
        }
    }

    // LOV COLOR
    @Override
    public BaseResponseDto<?> getVehicleColorLov(String keyword, DtoParamPaging paging) {
        try {

            List<Trn001Lov> list = trn001Dao.getVehicleColorLov(keyword, paging);
            int count = trn001Dao.getVehicleColorLovCount(keyword);

            if (list == null || list.isEmpty()) {
                return BaseResponseDto.error("Data warna tidak ditemukan", null);
            }

            Map<String, Object> result = new HashMap<>();
            result.put("count", count);
            result.put("list", list);

            return BaseResponseDto.success("Data warna ditemukan", result);

        } catch (Exception e) {
            return BaseResponseDto.error("Gagal mengambil LOV warna : " + e.getMessage(), null);
        }
    }

    // CREATE
    @Override
    public BaseResponseDto<?> createVehicle(Trn001DetailVo param) {
        try {

            if (!"TRN001".equals(param.getKeyAccess())) {
                return BaseResponseDto.error("Key access tidak valid", null);
            }

            if (param.getRegistrationNumber() == null ||
                param.getFrameNumber() == null ||
                param.getEngineNumber() == null ||
                param.getMotorcycleType() == null ||
                param.getColorCode() == null) {

                return BaseResponseDto.error("Data mandatory tidak boleh kosong", null);
            }

            ScTxnVhclsPk pk = new ScTxnVhclsPk();
            pk.setVkey(param.getKeyAccess());
            pk.setVengno(param.getEngineNumber());
            pk.setVfrmno(param.getFrameNumber());
            pk.setVnoregist(param.getRegistrationNumber());

            if (trn001Dao.findOne(pk) != null) {
                return BaseResponseDto.error("Data kendaraan sudah ada", null);
            }

            ScTxnVhcls entity = new ScTxnVhcls();
            entity.setScTxnVhclsPk(pk);
            entity.setVname(param.getOwnerName());
            entity.setVphone(param.getPhoneNumber());
            entity.setVaddress(param.getAddress());
            entity.setVmmctype(param.getMotorcycleType());
            entity.setVmclrid(param.getColorCode());
            entity.setDtrans(new Date());

            trn001Dao.save(entity);
            trn001Dao.flush();

            return BaseResponseDto.success("Data kendaraan berhasil disimpan", null);

        } catch (Exception e) {
            return BaseResponseDto.error("Data kendaraan gagal disimpan : " + e.getMessage(), null);
        }
    }

    // UPDATE
    @Override
    public BaseResponseDto<?> updateVehicle(Trn001DetailVo param, String key) {
        try {

            ScTxnVhclsPk pk = new ScTxnVhclsPk();
            pk.setVkey(param.getKeyAccess());
            pk.setVengno(param.getEngineNumber());
            pk.setVfrmno(param.getFrameNumber());
            pk.setVnoregist(param.getRegistrationNumber());

            ScTxnVhcls entity = trn001Dao.findOne(pk);

            if (entity == null) {
                return BaseResponseDto.error("Data kendaraan tidak ditemukan", null);
            }

            entity.setVname(param.getOwnerName());
            entity.setVphone(param.getPhoneNumber());
            entity.setVaddress(param.getAddress());
            entity.setVmmctype(param.getMotorcycleType());
            entity.setVmclrid(param.getColorCode());

            trn001Dao.update(entity);
            trn001Dao.flush();

            return BaseResponseDto.success("Data kendaraan berhasil diperbarui", null);

        } catch (Exception e) {
            return BaseResponseDto.error("Data kendaraan gagal diperbarui : " + e.getMessage(), null);
        }
    }

    // DELETE
    @Override
    @Transactional
    public BaseResponseDto<?> deleteVehicle(Trn001DetailVo param, String key) {

        try {

            ScTxnVhclsPk pk = new ScTxnVhclsPk();
            pk.setVkey(key.trim());
            pk.setVengno(param.getEngineNumber().trim());
            pk.setVfrmno(param.getFrameNumber().trim());
            pk.setVnoregist(param.getRegistrationNumber().trim());

            ScTxnVhcls entity = trn001Dao.findOne(pk);

            if (entity == null) {
                return BaseResponseDto.error("Data kendaraan tidak ditemukan", null);
            }

            trn001Dao.delete(entity);
            trn001Dao.flush();

            return BaseResponseDto.success("Data kendaraan berhasil dihapus", null);

        } catch (Exception e) {
            return BaseResponseDto.error("Data kendaraan gagal dihapus : " + e.getMessage(), null);
        }
    }
    
    @Override
    public List<Trn001ExportVo> getVehicleExport(Trn001Filter filter) {
        try {
            return trn001Dao.getVehicleListExport(filter);
        } catch (Exception e) {
            return new ArrayList<>(); 
        }
    }
}
