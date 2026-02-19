/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.it.trn.app001.dao.impl;

/**
 *
 * @author fadli
 */

import id.co.ahm.it.trn.app001.constant.Trn001QueryConstant;
import id.co.ahm.it.trn.app001.dao.Trn001Dao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.it.trn.app000.model.ScTxnVhcls;
import id.co.ahm.it.trn.app000.model.ScTxnVhclsPk;
import id.co.ahm.it.trn.app001.vo.Trn001ExportVo;
import id.co.ahm.it.trn.app001.vo.Trn001Filter;
import id.co.ahm.it.trn.app001.vo.Trn001Lov;
import id.co.ahm.it.trn.app001.vo.Trn001SummaryVo;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.StringType;

@Repository("trn001Dao")
public class Trn001DaoImpl extends DefaultHibernateDao<ScTxnVhcls, ScTxnVhclsPk> implements Trn001Dao {
    
    @Override
    public int getVehicleCount(DtoParamPaging dto, Trn001Filter filter) {
        Query q = getCurrentSession().createSQLQuery(Trn001QueryConstant.SQL_COUNT_VEHICLE);
        
        Date from = filter.getFromDate();
        Date to = filter.getToDate();
        
        if (from == null || to == null) {
            throw new IllegalArgumentException("From/To Date not allow null");
        }

        q.setParameter("from", new java.sql.Timestamp(from.getTime()));
        q.setParameter("to", new java.sql.Timestamp(to.getTime()));

        Number count = (Number) q.uniqueResult();
        return count.intValue();
    }
    
    @Override
    public int getVehicleLovCount(String keyword) {

        String sql =
            "SELECT COUNT(*) " +
            "FROM SC_MSTVHCLS " +
            "WHERE UPPER(VMCTYPE) LIKE :keyword " +
            "OR UPPER(VMCDESC) LIKE :keyword";

        Query q = getCurrentSession().createSQLQuery(sql);
        q.setParameter("keyword", "%" + keyword.toUpperCase() + "%");

        Number count = (Number) q.uniqueResult();
        return count == null ? 0 : count.intValue();
    }
    
    @Override
    public int getVehicleColorLovCount(String keyword) {

        String sql =
            "SELECT COUNT(*) " +
            "FROM SC_MSTCLRS " +
            "WHERE UPPER(VCLRID) LIKE :keyword ";

        Query q = getCurrentSession().createSQLQuery(sql);
        
        q.setParameter("keyword", "%" + keyword.toUpperCase() + "%"); 

        Number count = (Number) q.uniqueResult();
        return count == null ? 0 : count.intValue();
    }
    
    @Override
    public List<Trn001SummaryVo> getVehicleList(DtoParamPaging dto, Trn001Filter filter) {
        Query q = getCurrentSession().createSQLQuery(Trn001QueryConstant.SQL_GET_VEHICLE_LIST)
        .addScalar("keyAccess", StringType.INSTANCE)
        .addScalar("registrationNumber", StringType.INSTANCE)
        .addScalar("ownerName", StringType.INSTANCE)
        .addScalar("phoneNumber", StringType.INSTANCE)
        .addScalar("motorcycleType", StringType.INSTANCE)
        .addScalar("transactionDate", DateType.INSTANCE)
        .setResultTransformer(Transformers.aliasToBean(Trn001SummaryVo.class));
        
        Date from = filter.getFromDate();
        Date to = filter.getToDate();
        
        if (from == null || to == null) {
            throw new IllegalArgumentException("From/To Date not allow null");
        }

        q.setParameter("from", new java.sql.Timestamp(from.getTime()));
        q.setParameter("to", new java.sql.Timestamp(to.getTime()));
        
        q.setFirstResult(dto.getOffset());
        q.setMaxResults(dto.getLimit());
        
        return q.list();
    }
    
    @Override
    public List<Trn001Lov> getVehicleLov(String keyword, DtoParamPaging dto) {
        Query q = getCurrentSession().createSQLQuery(Trn001QueryConstant.SQL_LOV_VEHICLE)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        
        q.setParameter("keyword","%" + keyword.toUpperCase() + "%");
        q.setFirstResult(dto.getOffset());
        q.setMaxResults(dto.getLimit());
        
        List<Map<String, Object>> result = q.list();
        List<Trn001Lov> lovVehicle = new ArrayList<>();
        
        for (Map<String, Object> row : result) {
            Trn001Lov vo = new Trn001Lov();
            vo.setCode((String) row.get("CODE"));
            vo.setDescription((String) row.get("DESCRIPTION"));
            lovVehicle.add(vo);
        }
        return lovVehicle;
    }
    
    @Override
    public List<Trn001Lov> getVehicleColorLov(String keyword, DtoParamPaging dto) {
        Query q = getCurrentSession().createSQLQuery(Trn001QueryConstant.SQL_LOV_COLOR)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        
        q.setParameter("keyword", keyword.toUpperCase() + "%");
        q.setFirstResult(dto.getOffset());
        q.setMaxResults(dto.getLimit());
        
        List<Map<String, Object>> result = q.list();
        List<Trn001Lov> lovColor = new ArrayList<>();
        
        for(Map<String, Object> row : result) {
            Trn001Lov vo = new Trn001Lov();
            vo.setCode((String) row.get("CODE"));
            vo.setDescription((String) row.get("DESCRIPTION"));
            lovColor.add(vo);
        }
        return lovColor;
    }
    
    @Override
    public List<Trn001ExportVo> getVehicleListExport(Trn001Filter filter) {
        Query q = getCurrentSession().createSQLQuery(Trn001QueryConstant.SQL_EXPORT_VEHICLE)
        .addScalar("registrationNumber", StringType.INSTANCE)
        .addScalar("ownerName", StringType.INSTANCE)
        .addScalar("phoneNumber", StringType.INSTANCE)
        .addScalar("address", StringType.INSTANCE)
        .addScalar("transactionDate", DateType.INSTANCE)
        .addScalar("motorcycleCode", StringType.INSTANCE)
        .addScalar("motorcycleDescription", StringType.INSTANCE)
        .addScalar("colorCode", StringType.INSTANCE)
        .addScalar("colorDescription", StringType.INSTANCE)
        .addScalar("frameNumber", StringType.INSTANCE)
        .addScalar("engineNumber", StringType.INSTANCE)
        .setResultTransformer(Transformers.aliasToBean(Trn001ExportVo.class));
        
        return q.list();
    }
}