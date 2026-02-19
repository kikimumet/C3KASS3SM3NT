package id.co.ahm.it.trn.app001.constant;

/**
 *
 * @author fadli
 */

public class Trn001QueryConstant {

    //  Paging
    public static final String SQL_COUNT_VEHICLE = 
            "SELECT COUNT(*) FROM SC_TXNVHCLS WHERE DTRANS BETWEEN :from AND :to";

    //  Summary
    public static final String SQL_GET_VEHICLE_LIST = 
            "SELECT VKEY as keyAccess, " +
            "VNOREGIST as registrationNumber, " +
            "VNAME as ownerName, " +
            "VPHONE as phoneNumber, " +
            "VMCDESC as motorcycleType, " +
            "DTRANS as transactionDate " +
            "FROM SC_TXNVHCLS st " +
            "JOIN SC_MSTVHCLS sm ON st.VMMCTYPE = sm.VMCTYPE " +
            "WHERE DTRANS BETWEEN :from AND :to " +
            "ORDER BY DTRANS ASC";

    //  LoV Vehicle
    public static final String SQL_LOV_VEHICLE = 
            "SELECT VMCTYPE AS code, VMCDESC AS description " +
            "FROM SC_MSTVHCLS " +
            "WHERE UPPER(VMCTYPE) LIKE :keyword " +
            "OR UPPER(VMCDESC) LIKE :keyword " +
            "ORDER BY VMCTYPE";

    //  LoV Color
    public static final String SQL_LOV_COLOR = 
            "SELECT VCLRID AS code, VCLRID AS description FROM SC_MSTCLRS " +
            "WHERE UPPER(VCLRID) LIKE :keyword " +
            "ORDER BY VCLRID";
    
    // Export Excell
    public static final String SQL_EXPORT_VEHICLE = 
            "SELECT st.VNOREGIST as registrationNumber, " +
            "st.VNAME as ownerName, " +
            "st.VPHONE as phoneNumber, " +
            "st.VADDRESS as address, " +
            "st.DTRANS as transactionDate, " +
            "st.VMMCTYPE as motorcycleCode, " +
            "sm.VMCDESC as motorcycleDescription, " +
            "st.VMCLRID as colorCode, " +
            "sett.VDESC as colorDescription, " +
            "st.VFRMNO as frameNumber, " +
            "st.VENGNO as engineNumber " +
            "FROM SC_TXNVHCLS st " +
            "LEFT JOIN SC_MSTVHCLS sm ON st.VMMCTYPE = sm.VMCTYPE " +
            "LEFT JOIN SC_MSTSETTINGS sett ON st.VMCLRID = sett.VITEMCODE " +
            "ORDER BY st.DTRANS ASC";
}