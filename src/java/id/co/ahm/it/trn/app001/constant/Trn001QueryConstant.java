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

    //  Detail
    public static final String SQL_GET_VEHICLE_DETAIL = 
            "SELECT VKEY, VNOREGIST, VNAME, " +
            "VPHONE, VFRMNO, VENGNO, " +
            "VMCDESC, VADDRESS, DTRANS, " +
            "VMMCTYPE, VMCLRID " +
            "FROM SC_TXNVHCLS JOIN SC_MSTVHCLS ON SC_TXNVHCLS.VMMCTYPE = SC_MSTVHCLS.VMCTYPE " +
            "WHERE VKEY = :key";

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
}