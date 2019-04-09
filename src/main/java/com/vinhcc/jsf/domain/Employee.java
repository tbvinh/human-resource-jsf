/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhcc.jsf.domain;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author VinhE7440
 */
public class Employee {

    int id;
    
    String MSNV,
    MSNV6	,
    HO, TEN;
    Date DATE_IN,
    DATE_OUT;
    String CHUC_DANH,
    KHOI,
    PHONG_BAN,
    BO_PHAN,
    TOSX,
	CHUC_DANH_THUC,
    TRINH_DO,
    CHUYEN_MON,
    GIOI_TINH, TINH_TRANG;
    Date NGAY_SINH;
    String NOI_SINH,
            
    CMND, CMND_01;
    Date CMND_DATE, CMND_DATE_01;
    String NOI_CAP, NOI_CAP_01,
            
    DAN_TOC,
    QUOC_TICH,
    NGUYEN_QUAN,
    DIA_CHI_01,
    DIA_CHI_02,
    EMAIL,
    DIEN_THOAI,
    NGUOI_THAN,
    MST;
    Date MST_NGAY;
    
    String ATM,
    ATM_NGAN_HANG,
            
    ATM_01,
    ATM_NGAN_HANG_01,
            
    SO_BKXH,
    MA_BENH_VIEN,
    TEN_BENH_VIEN,
    MA_BHYT;
    Date THAM_GIA_BHXH,
    KET_THUC_BHXH;
    Double LUONG_BHXH;
    String SO_TK_BANK,
    NOTE;
    Timestamp CREATED_DATE;

    public Employee() {

    }

    public Employee(int id, String MSNV, String MSNV6, String HO, String TEN, Date DATE_IN, Date DATE_OUT, String CHUC_DANH, String KHOI, String PHONG_BAN, String BO_PHAN, String TOSX, String CHUC_DANH_THUC, String TRINH_DO, String CHUYEN_MON, String GIOI_TINH, Date NGAY_SINH, String NOI_SINH, String CMND, Date CMND_DATE, String NOI_CAP, String DAN_TOC, String QUOC_TICH, String NGUYEN_QUAN, String DIA_CHI_01, String DIA_CHI_02, String EMAIL, String DIEN_THOAI, String NGUOI_THAN, String MST, Date MST_NGAY, String ATM, String ATM_NGAN_HANG, String SO_BKXH, String MA_BENH_VIEN, String TEN_BENH_VIEN, String MA_BHYT, Date THAM_GIA_BHXH, Date KET_THUC_BHXH, Double LUONG_BHXH, String SO_TK_BANK, String NOTE) {
        this.id = id;
        this.MSNV = MSNV;
        this.MSNV6 = MSNV6;
        this.HO = HO;
        this.TEN = TEN;
        
        this.DATE_IN = DATE_IN;
        this.DATE_OUT = DATE_OUT;
        this.CHUC_DANH = CHUC_DANH;
        this.KHOI = KHOI;
        this.PHONG_BAN = PHONG_BAN;
        this.BO_PHAN = BO_PHAN;
        this.TOSX = TOSX;
        this.CHUC_DANH_THUC = CHUC_DANH_THUC;
        this.TRINH_DO = TRINH_DO;
        this.CHUYEN_MON = CHUYEN_MON;
        this.GIOI_TINH = GIOI_TINH;
        this.NGAY_SINH = NGAY_SINH;
        this.NOI_SINH = NOI_SINH;
        this.CMND = CMND;
        this.CMND_DATE = CMND_DATE;
        this.NOI_CAP = NOI_CAP;
        this.DAN_TOC = DAN_TOC;
        this.QUOC_TICH = QUOC_TICH;
        this.NGUYEN_QUAN = NGUYEN_QUAN;
        this.DIA_CHI_01 = DIA_CHI_01;
        this.DIA_CHI_02 = DIA_CHI_02;
        this.EMAIL = EMAIL;
        this.DIEN_THOAI = DIEN_THOAI;
        this.NGUOI_THAN = NGUOI_THAN;
        this.MST = MST;
        this.MST_NGAY = MST_NGAY;
        this.ATM = ATM;
        this.ATM_NGAN_HANG = ATM_NGAN_HANG;
        this.SO_BKXH = SO_BKXH;
        this.MA_BENH_VIEN = MA_BENH_VIEN;
        this.TEN_BENH_VIEN = TEN_BENH_VIEN;
        this.MA_BHYT = MA_BHYT;
        this.THAM_GIA_BHXH = THAM_GIA_BHXH;
        this.KET_THUC_BHXH = KET_THUC_BHXH;
        this.LUONG_BHXH = LUONG_BHXH;
        this.SO_TK_BANK = SO_TK_BANK;
        this.NOTE = NOTE;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMSNV() {
        return MSNV;
    }

    public void setMSNV(String MSNV) {
        this.MSNV = MSNV;
    }

    public String getMSNV6() {
        return MSNV6;
    }

    public void setMSNV6(String MSNV6) {
        this.MSNV6 = MSNV6;
    }

    public String getHO() {
        return HO;
    }

    public void setHO(String HO) {
        this.HO = HO;
    }

    public String getTEN() {
        return TEN;
    }

    public void setTEN(String TEN) {
        this.TEN = TEN;
    }

    

    public Date getDATE_IN() {
        return DATE_IN;
    }

    public void setDATE_IN(Date DATE_IN) {
        this.DATE_IN = DATE_IN;
    }

    public Date getDATE_OUT() {
        return DATE_OUT;
    }

    public void setDATE_OUT(Date DATE_OUT) {
        this.DATE_OUT = DATE_OUT;
    }

    public String getCHUC_DANH() {
        return CHUC_DANH;
    }

    public void setCHUC_DANH(String CHUC_DANH) {
        this.CHUC_DANH = CHUC_DANH;
    }

    public String getKHOI() {
        return KHOI;
    }

    public void setKHOI(String KHOI) {
        this.KHOI = KHOI;
    }

    public String getPHONG_BAN() {
        return PHONG_BAN;
    }

    public void setPHONG_BAN(String PHONG_BAN) {
        this.PHONG_BAN = PHONG_BAN;
    }

    public String getBO_PHAN() {
        return BO_PHAN;
    }

    public void setBO_PHAN(String BO_PHAN) {
        this.BO_PHAN = BO_PHAN;
    }

    public String getTOSX() {
        return TOSX;
    }

    public void setTOSX(String TOSX) {
        this.TOSX = TOSX;
    }

    public String getCHUC_DANH_THUC() {
        return CHUC_DANH_THUC;
    }

    public void setCHUC_DANH_THUC(String CHUC_DANH_THUC) {
        this.CHUC_DANH_THUC = CHUC_DANH_THUC;
    }

    public String getTRINH_DO() {
        return TRINH_DO;
    }

    public void setTRINH_DO(String TRINH_DO) {
        this.TRINH_DO = TRINH_DO;
    }

    public String getCHUYEN_MON() {
        return CHUYEN_MON;
    }

    public void setCHUYEN_MON(String CHUYEN_MON) {
        this.CHUYEN_MON = CHUYEN_MON;
    }

    public String getGIOI_TINH() {
        return GIOI_TINH;
    }

    public void setGIOI_TINH(String GIOI_TINH) {
        this.GIOI_TINH = GIOI_TINH;
    }

    public Date getNGAY_SINH() {
        return NGAY_SINH;
    }

    public void setNGAY_SINH(Date NGAY_SINH) {
        this.NGAY_SINH = NGAY_SINH;
    }

    public String getNOI_SINH() {
        return NOI_SINH;
    }

    public void setNOI_SINH(String NOI_SINH) {
        this.NOI_SINH = NOI_SINH;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public Date getCMND_DATE() {
        return CMND_DATE;
    }

    public void setCMND_DATE(Date CMND_DATE) {
        this.CMND_DATE = CMND_DATE;
    }

    public String getNOI_CAP() {
        return NOI_CAP;
    }

    public void setNOI_CAP(String NOI_CAP) {
        this.NOI_CAP = NOI_CAP;
    }

    public String getDAN_TOC() {
        return DAN_TOC;
    }

    public void setDAN_TOC(String DAN_TOC) {
        this.DAN_TOC = DAN_TOC;
    }

    public String getQUOC_TICH() {
        return QUOC_TICH;
    }

    public void setQUOC_TICH(String QUOC_TICH) {
        this.QUOC_TICH = QUOC_TICH;
    }

    public String getNGUYEN_QUAN() {
        return NGUYEN_QUAN;
    }

    public void setNGUYEN_QUAN(String NGUYEN_QUAN) {
        this.NGUYEN_QUAN = NGUYEN_QUAN;
    }

    public String getDIA_CHI_01() {
        return DIA_CHI_01;
    }

    public void setDIA_CHI_01(String DIA_CHI_01) {
        this.DIA_CHI_01 = DIA_CHI_01;
    }

    public String getDIA_CHI_02() {
        return DIA_CHI_02;
    }

    public void setDIA_CHI_02(String DIA_CHI_02) {
        this.DIA_CHI_02 = DIA_CHI_02;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getDIEN_THOAI() {
        return DIEN_THOAI;
    }

    public void setDIEN_THOAI(String DIEN_THOAI) {
        this.DIEN_THOAI = DIEN_THOAI;
    }

    public String getNGUOI_THAN() {
        return NGUOI_THAN;
    }

    public void setNGUOI_THAN(String NGUOI_THAN) {
        this.NGUOI_THAN = NGUOI_THAN;
    }

    public String getMST() {
        return MST;
    }

    public void setMST(String MST) {
        this.MST = MST;
    }

    public Date getMST_NGAY() {
        return MST_NGAY;
    }

    public void setMST_NGAY(Date MST_NGAY) {
        this.MST_NGAY = MST_NGAY;
    }

    public String getATM() {
        return ATM;
    }

    public void setATM(String ATM) {
        this.ATM = ATM;
    }

    public String getATM_NGAN_HANG() {
        return ATM_NGAN_HANG;
    }

    public void setATM_NGAN_HANG(String ATM_NGAN_HANG) {
        this.ATM_NGAN_HANG = ATM_NGAN_HANG;
    }

    public String getSO_BKXH() {
        return SO_BKXH;
    }

    public void setSO_BKXH(String SO_BKXH) {
        this.SO_BKXH = SO_BKXH;
    }

    public String getMA_BENH_VIEN() {
        return MA_BENH_VIEN;
    }

    public void setMA_BENH_VIEN(String MA_BENH_VIEN) {
        this.MA_BENH_VIEN = MA_BENH_VIEN;
    }

    public String getTEN_BENH_VIEN() {
        return TEN_BENH_VIEN;
    }

    public void setTEN_BENH_VIEN(String TEN_BENH_VIEN) {
        this.TEN_BENH_VIEN = TEN_BENH_VIEN;
    }

    public String getMA_BHYT() {
        return MA_BHYT;
    }

    public void setMA_BHYT(String MA_BHYT) {
        this.MA_BHYT = MA_BHYT;
    }

    public Date getTHAM_GIA_BHXH() {
        return THAM_GIA_BHXH;
    }

    public void setTHAM_GIA_BHXH(Date THAM_GIA_BHXH) {
        this.THAM_GIA_BHXH = THAM_GIA_BHXH;
    }

    public Date getKET_THUC_BHXH() {
        return KET_THUC_BHXH;
    }

    public void setKET_THUC_BHXH(Date KET_THUC_BHXH) {
        this.KET_THUC_BHXH = KET_THUC_BHXH;
    }

    public Double getLUONG_BHXH() {
        return LUONG_BHXH;
    }

    public void setLUONG_BHXH(Double LUONG_BHXH) {
        this.LUONG_BHXH = LUONG_BHXH;
    }

    public String getSO_TK_BANK() {
        return SO_TK_BANK;
    }

    public void setSO_TK_BANK(String SO_TK_BANK) {
        this.SO_TK_BANK = SO_TK_BANK;
    }

    public String getNOTE() {
        return NOTE;
    }

    public void setNOTE(String NOTE) {
        this.NOTE = NOTE;
    }

    public Timestamp getCREATED_DATE() {
        return CREATED_DATE;
    }

    public void setCREATED_DATE(Timestamp CREATED_DATE) {
        this.CREATED_DATE = CREATED_DATE;
    }

    public String getCMND_01() {
        return CMND_01;
    }

    public void setCMND_01(String CMND_01) {
        this.CMND_01 = CMND_01;
    }

    public Date getCMND_DATE_01() {
        return CMND_DATE_01;
    }

    public void setCMND_DATE_01(Date CMND_DATE_01) {
        this.CMND_DATE_01 = CMND_DATE_01;
    }

    public String getNOI_CAP_01() {
        return NOI_CAP_01;
    }

    public void setNOI_CAP_01(String NOI_CAP_01) {
        this.NOI_CAP_01 = NOI_CAP_01;
    }

    public String getATM_01() {
        return ATM_01;
    }

    public void setATM_01(String ATM_01) {
        this.ATM_01 = ATM_01;
    }

    public String getATM_NGAN_HANG_01() {
        return ATM_NGAN_HANG_01;
    }

    public void setATM_NGAN_HANG_01(String ATM_NGAN_HANG_01) {
        this.ATM_NGAN_HANG_01 = ATM_NGAN_HANG_01;
    }

    public String getTINH_TRANG() {
        return TINH_TRANG;
    }

    public void setTINH_TRANG(String TINH_TRANG) {
        this.TINH_TRANG = TINH_TRANG;
    }

}
