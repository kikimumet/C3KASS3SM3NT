/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.it.trn.app001.service;

import id.co.ahm.it.trn.app001.vo.Trn001DetailVo;
import id.co.ahm.it.trn.app001.vo.Trn001Filter;
import id.co.ahm.it.trn.app001.vo.Trn001SummaryVo;
import java.util.List;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;

/**
 *
 * @author wahyu.rm
 */
public interface Trn001Service {
    DtoResponse getDataKendaraan(Trn001Filter filter, DtoParamPaging dtoParamPaging);
    DtoResponse getLovKendaraan(String keyword, DtoParamPaging dto);
    DtoResponse getLovWarna(String keyword, DtoParamPaging dto);
    DtoResponse getDetailDataKendaraan(String key);
    DtoResponse saveDataKendaraan(Trn001DetailVo param);
    DtoResponse updateDataKendaraan(Trn001DetailVo param, String key);
    DtoResponse deleteDataKendaraan(Trn001DetailVo param, String key);
}
