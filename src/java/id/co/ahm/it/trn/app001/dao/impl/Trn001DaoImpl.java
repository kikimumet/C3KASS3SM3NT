/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.it.trn.app001.dao.impl;
import id.co.ahm.it.trn.app001.dao.Trn001Dao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.it.trn.app000.model.ScTxnVhcls;
import id.co.ahm.it.trn.app000.model.ScTxnVhclsPk;
import id.co.ahm.it.trn.app001.vo.Trn001DetailVo;
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

;
/**
 *
 * @author wahyu.rm
 */

@Repository("trn001Dao")
public class Trn001DaoImpl extends DefaultHibernateDao<ScTxnVhcls, ScTxnVhclsPk> implements Trn001Dao {
   

    @Override
    public int getCountKendaraan(DtoParamPaging dto, Trn001Filter filter) {
        String count_result = "SELECT COUNT(*) FROM SC_TXNVHCLS WHERE DTRANS BETWEEN :from AND :to";
        Query q = getCurrentSession().createSQLQuery(count_result);
        
        Date from = filter.getFromDate();
        Date to = filter.getToDate();
        
        if (from == null || to == null) {
            throw new IllegalArgumentException("from/to date tidak boleh null");
        }

        q.setParameter("from", new java.sql.Timestamp(from.getTime()));
        q.setParameter("to", new java.sql.Timestamp(to.getTime()));

        Number count = (Number) q.uniqueResult();
        return count.intValue();
    }
    
    @Override
    public List<Trn001SummaryVo> getDataKendaraan(DtoParamPaging dto, Trn001Filter filter) {
        String SQL = "SELECT VKEY as key_access, VNOREGIST as registration_number, VNAME as nama_pemilik, VPHONE as nomor_telefon, VMCDESC as merek_kendaraan, DTRANS as tanggal_transaksi FROM SC_TXNVHCLS st JOIN SC_MSTVHCLS sm ON st.VMMCTYPE = sm.VMCTYPE WHERE DTRANS BETWEEN :from AND :to order by DTRANS ASC";
        Query q = getCurrentSession().createSQLQuery(SQL)
        .addScalar("key_access", StringType.INSTANCE)
        .addScalar("registration_number", StringType.INSTANCE)
        .addScalar("nama_pemilik", StringType.INSTANCE)
        .addScalar("nomor_telefon", StringType.INSTANCE)
        .addScalar("merek_kendaraan", StringType.INSTANCE)
        .addScalar("tanggal_transaksi", DateType.INSTANCE) // ini penting

        .setResultTransformer(Transformers.aliasToBean(Trn001SummaryVo.class));
        
        Date from = filter.getFromDate();
        Date to = filter.getToDate();
        
        if (from == null || to == null) {
            throw new IllegalArgumentException("from/to date tidak boleh null");
        }

        q.setParameter("from", new java.sql.Timestamp(from.getTime()));
        q.setParameter("to", new java.sql.Timestamp(to.getTime()));
        
        q.setFirstResult(dto.getOffset());
        q.setMaxResults(dto.getLimit());
        
        return q.list();
    }
    
    @Override
    public Trn001DetailVo getDetailDataKendaraan(String key) {
       String SQL = 
        "SELECT VKEY as key_access, VNOREGIST as registration_number, VNAME as nama_pemilik, " +
        "VPHONE as nomor_telefon, VFRMNO as frame_number, VENGNO as engine_number, " +
        "VMCDESC as description, VADDRESS as address, DTRANS as tanggal_transaksi, " +
        "VMMCTYPE as tipe_motor, VMCLRID as kode_warna " +   
        "FROM SC_TXNVHCLS JOIN SC_MSTVHCLS ON SC_TXNVHCLS.VMMCTYPE = SC_MSTVHCLS.VMCTYPE " + 
        "WHERE VKEY = :key";

        
        Query q = getCurrentSession().createSQLQuery(SQL);
        q.setParameter("key", key);
        Object[] row = (Object[]) q.uniqueResult();
        
        if (row != null) {
            Trn001DetailVo vo = new Trn001DetailVo();
                vo.setKey_access((String) row[0]);
                vo.setRegistration_number((String) row[1]);
                vo.setNama_pemilik((String) row[2]);
                vo.setNomor_telefon((String) row[3]);
                vo.setFrame_number((String) row[4]);
                vo.setEngine_number((String) row[5]);
                vo.setDescription((String) row[6]);
                vo.setAddress((String) row[7]);
                vo.setTanggal_transaksi((Date) row[8]);
                vo.setTipe_motor((String) row[9]);
                vo.setKode_warna((String) row[10]);
                vo.setMerkKendaraan("Honda");
                
                return vo;
        }
        return null;
    }
    
    @Override
    public List<Trn001Lov> getLovKendaraan(String keyword, DtoParamPaging dto) {
        String SQL = "SELECT VMCTYPE AS TYCODE, VMCDESC AS DESCM FROM SC_MSTVHCLS WHERE UPPER(VMCTYPE) LIKE :keyword " + "OR UPPER(VMCDESC) LIKE :keyword " + "ORDER BY VMCTYPE";
        
        Query q = getCurrentSession().createSQLQuery(SQL).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        
        q.setParameter("keyword","%" + keyword.toUpperCase() + "%");
        q.setFirstResult(dto.getOffset());
        q.setMaxResults(dto.getLimit());
        
        List<Map<String, Object>> result = q.list();
        
        List<Trn001Lov> lovKendaraan = new ArrayList<>();
        for (Map<String, Object> row : result) {
            Trn001Lov vo = new Trn001Lov();
            vo.setCode((String) row.get("TYCODE"));
            vo.setDescription((String) row.get("DESCM"));
            lovKendaraan.add(vo);
        }
        return lovKendaraan;
    }
    
    @Override
    public List<Trn001Lov> getLovWarnaKendaraan(String keyword, DtoParamPaging dto) {
        String SQL = "SELECT VCLRID AS COLORID FROM SC_MSTCLRS WHERE UPPER(VCLRID) LIKE :keyword " + "ORDER BY VCLRID";
        Query q = getCurrentSession().createSQLQuery(SQL).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        
        q.setParameter("keyword", keyword.toUpperCase() + "%");
        q.setFirstResult(dto.getOffset());
        q.setMaxResults(dto.getLimit());
        
        List<Map<String, Object>> result = q.list();
        List<Trn001Lov> lovWarna = new ArrayList<>();
        
        for(Map<String, Object> row : result) {
            Trn001Lov vo = new Trn001Lov();
            vo.setCode((String) row.get("COLORID"));
            System.out.println(row);
            lovWarna.add(vo);
        }
        return lovWarna;
    }
}
