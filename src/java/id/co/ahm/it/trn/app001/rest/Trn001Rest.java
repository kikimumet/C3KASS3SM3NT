/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.it.trn.app001.rest;

import id.co.ahm.it.trn.app001.service.Trn001Service;
import id.co.ahm.it.trn.app001.vo.Trn001DetailVo;
import id.co.ahm.it.trn.app001.vo.Trn001Filter;
import id.co.ahm.jxf.constant.CommonConstant;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author fadli
 */

@RestController
@RequestMapping("it/trn001")
public class Trn001Rest {
    
    @Autowired
    @Qualifier(value = "trn001Service")
    private Trn001Service service;
    
    @GetMapping("status")
    public String getStatus(){
        return "berhasil";
    }
    
    // Show data kendaraan
    @RequestMapping(value = "show-data-kendaraan", method=RequestMethod.POST,
           consumes = {MediaType.APPLICATION_JSON_VALUE},
           produces = MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody
    DtoResponse getDataKendaraan(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody Trn001Filter param) {
        DtoParamPaging dtoParamPaging = new DtoParamPaging();
        return service.getDataKendaraan(param, dtoParamPaging);
    }
    
    // Detail Data Kendaraan
    @RequestMapping(value = "show-detail-data-kendaraan", method=RequestMethod.POST,
           consumes = {MediaType.APPLICATION_JSON_VALUE},
           produces = MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody
    DtoResponse getDetailDataKendaraan(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody Trn001DetailVo param) {
        return service.getDetailDataKendaraan(param.getKey_access());
    }
   
    
    // Save Data Kendaraan 
    @RequestMapping(value = "save-data-kendaraan", method=RequestMethod.POST, 
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponse saveDataKendaraan(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody Trn001DetailVo param) {
        return service.saveDataKendaraan(param);
    }
    
    // Update Data Kendaraan
    @RequestMapping(value = "update-data-kendaraan", method=RequestMethod.POST, 
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponse updateDataKendaraan(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody Trn001DetailVo param) {
        return service.updateDataKendaraan(param, param.getKey_access());
    }
    
    // Hapus Data Kendaraan
    @RequestMapping(value = "hapus-data-kendaraan", method=RequestMethod.POST, 
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponse deleteDataKendaraan(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody Trn001DetailVo param) {
        return service.deleteDataKendaraan(param, param.getKey_access());
    }
    
    // LoV Kendaraan
    @RequestMapping(value = "get-lov-kendaraan", method=RequestMethod.POST, 
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponse getLovKendaraan(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody Trn001DetailVo param) {
        DtoParamPaging dto = new DtoParamPaging();
        dto.setOffset(0);
        dto.setLimit(10);
        return service.getLovKendaraan(param.getKeyword(), dto);
    }
    
    // Lov Kode Warna
    @RequestMapping(value = "get-lov-warna-kendaraan", method=RequestMethod.POST, 
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponse getLovWarna(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody Trn001DetailVo param) {
        DtoParamPaging dto = new DtoParamPaging();
        dto.setOffset(0);
        dto.setLimit(10);
        return service.getLovWarna(param.getKeyword(), dto);
    }
}
