/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.it.trn.app001.rest;

/**
 *
 * @author fadli
 */

import id.co.ahm.it.trn.app001.dto.BaseResponseDto;
import id.co.ahm.it.trn.app001.service.Trn001Service;
import id.co.ahm.it.trn.app001.vo.Trn001DetailVo;
import id.co.ahm.it.trn.app001.vo.Trn001Filter;
import id.co.ahm.jxf.constant.CommonConstant;
import id.co.ahm.jxf.dto.DtoParamPaging;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    
    // Get vehicle list
    @RequestMapping(value = "show-data-kendaraan", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponseDto<?> getVehicles(
            @RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody Trn001Filter param) {

        DtoParamPaging paging = new DtoParamPaging();
        return service.getVehicles(param, paging);
    }

    // Get vehicle detail
    @RequestMapping(value = "show-detail-data-kendaraan", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponseDto<?> getVehicleDetail(
            @RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody Trn001DetailVo param) {

        return service.getVehicleDetail(param.getKeyAccess());
    }

    // Create vehicle
    @RequestMapping(value = "save-data-kendaraan", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponseDto<?> createVehicle(
            @RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody Trn001DetailVo param) {

        return service.createVehicle(param);
    }

    // Update vehicle
    @RequestMapping(value = "update-data-kendaraan", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponseDto<?> updateVehicle(
            @RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody Trn001DetailVo param) {

        return service.updateVehicle(param, param.getKeyAccess());
    }

    // Delete vehicle
    @RequestMapping(value = "delete-data-kendaraan", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponseDto<?> deleteVehicle(
            @RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody Trn001DetailVo param) {

        return service.deleteVehicle(param, param.getKeyAccess());
    }

    // LOV vehicle
    @RequestMapping(value = "get-lov-kendaraan", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponseDto<?> getVehicleLov(
            @RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody Trn001DetailVo param) {

        DtoParamPaging paging = new DtoParamPaging();
        paging.setOffset(0);
        paging.setLimit(10);
        return service.getVehicleLov(param.getKeyword(), paging);
    }

    // LOV vehicle color
    @RequestMapping(value = "get-lov-warna-kendaraan", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponseDto<?> getVehicleColorLov(
            @RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
            @RequestBody Trn001DetailVo param) {

        DtoParamPaging paging = new DtoParamPaging();
        paging.setOffset(0);
        paging.setLimit(10);
        return service.getVehicleColorLov(param.getKeyword(), paging);
    }
}
