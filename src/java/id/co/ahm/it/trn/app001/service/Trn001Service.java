/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.it.trn.app001.service;

import id.co.ahm.it.trn.app001.dto.BaseResponseDto;
import id.co.ahm.it.trn.app001.vo.Trn001DetailVo;
import id.co.ahm.it.trn.app001.vo.Trn001Filter;
import id.co.ahm.it.trn.app001.vo.Trn001ExportVo;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.List;

/**
 *
 * @author fadli
 */
public interface Trn001Service {

    BaseResponseDto<?> getVehicles(Trn001Filter filter, DtoParamPaging paging);

    BaseResponseDto<?> getVehicleLov(String keyword, DtoParamPaging paging);

    BaseResponseDto<?> getVehicleColorLov(String keyword, DtoParamPaging paging);

    BaseResponseDto<?> createVehicle(Trn001DetailVo param);

    BaseResponseDto<?> updateVehicle(Trn001DetailVo param, String key);

    BaseResponseDto<?> deleteVehicle(Trn001DetailVo param, String key);
    
    List<Trn001ExportVo> getVehicleExport(Trn001Filter filter);
}

