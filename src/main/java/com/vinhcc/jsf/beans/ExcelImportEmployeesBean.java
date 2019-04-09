/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhcc.jsf.beans;

import com.vinhcc.jsf.dao.EmployeeContractDAO;
import com.vinhcc.jsf.dao.EmployeeDAO;
import com.vinhcc.jsf.dao.EmployeeDependentDAO;
import com.vinhcc.jsf.domain.Employee;
import com.vinhcc.jsf.domain.EmployeeContract;
import com.vinhcc.jsf.domain.EmployeeDependent;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;

/**
 *
 * @author VinhE7440
 */
@ManagedBean
@ViewScoped
@Named(value = "excelImportEmployeesBean")
public class ExcelImportEmployeesBean {

    private Part fileUpload;

    public Part getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(Part fileUpload) {
        this.fileUpload = fileUpload;
    }

    public void upload() {
        if (fileUpload != null) {
            String path = "D:/tmp/";
            File file = new File(path);
            if (!file.exists()) {
                path = "/tmp/"; //Server side
            }
            File newfile = new File(fileUpload.getSubmittedFileName());

            try {
                InputStream inputStream = fileUpload.getInputStream();
                String newPath = path + newfile.getName();
                FileOutputStream outputStream = new FileOutputStream(newPath);
                int bytesRead = 0;
                final byte[] chunck = new byte[1024 * 10];
                while ((bytesRead = inputStream.read(chunck)) != -1) {
                    outputStream.write(chunck, 0, bytesRead);
                }
                outputStream.close();

                importExcelToDB(newPath);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload successfully ended!"));
            } catch (IOException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload failed!"));
            }
        }
    }

    private Map<Integer, String> getColumnsMap() {
        Map<Integer, String> map = new HashMap<>();

        map.put(0, "STT");
        map.put(1, "MSNV");
        map.put(2, "MSNV6");
        map.put(3, "HO_VA_TEN");
        map.put(4, "DATE_IN");
        map.put(5, "DATE_OUT");
        map.put(6, "CHUC_DANH");
        map.put(7, "KHOI");
        map.put(8, "PHONG_BAN");
        map.put(9, "BO_PHAN");
        map.put(10, "TO");
        map.put(11, "CHUC_DANH_THUC");
        map.put(12, "TRINH_DO");
        map.put(13, "CHUYEN_MON");
        map.put(14, "GIOI_TINH");
        map.put(15, "NGAY_SINH");
        map.put(16, "NOI_SINH");
        map.put(17, "CMND");
        map.put(18, "CMND_DATE");
        map.put(19, "NOI_CAP");
        map.put(20, "DAN_TOC");
        map.put(21, "QUOC_TICH");
        map.put(22, "NGUYEN_QUAN");
        map.put(23, "DIA_CHI_01");
        map.put(24, "DIA_CHI_02");
        map.put(25, "EMAIL");
        map.put(26, "DIEN_THOAI");
        map.put(27, "NGUOI_THAN");
        map.put(28, "MST");
        map.put(29, "MST_NGAY");
        map.put(30, "CMND_PHU_THUOC");
        map.put(31, "MST_PHU_THUOC");
        map.put(32, "ATM");
        map.put(33, "ATM_NGAN_HANG");
        map.put(34, "SO_BKXH");
        map.put(35, "MA_BENH_VIEN");
        map.put(36, "TEN_BENH_VIEN");
        map.put(37, "MA_BHYT");
        map.put(38, "THAM_GIA_BHXH");
        map.put(39, "KET_THUC_BHXH");
        map.put(40, "LUONG_BHXH");
        map.put(41, "HD_THU_VIEC_01");
        map.put(42, "THOI_GIAN_TV_01");
        map.put(43, "THOI_GIAN_HET_TV_01");
        map.put(44, "HD_THU_VIEC_02");
        map.put(45, "THOI_GIAN_TV_02");
        map.put(46, "THOI_GIAN_HET_TV_02");
        map.put(47, "THOI_HAN_HD_02");
        map.put(48, "HD_THU_VIEC_03");
        map.put(49, "THOI_GIAN_TV_03");
        map.put(50, "THOI_GIAN_HET_TV_03");
        map.put(51, "THOI_HAN_HD_03");
        map.put(52, "HD_THU_VIEC_04");
        map.put(53, "THOI_GIAN_TV_04");
        map.put(54, "THOI_GIAN_HET_TV_04");
        map.put(55, "THOI_HAN_HD_04");
        map.put(56, "HD_THU_VIEC_05");
        map.put(57, "THOI_GIAN_TV_05");
        map.put(58, "THOI_GIAN_HET_TV_05");
        map.put(59, "THOI_HAN_HD_05");
        map.put(60, "SO_TK_BANK");

        return map;
    }

    private Date getDate(String txt) {

        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("M/d/yyyy").parse(txt);
            date1 = new SimpleDateFormat("M/dd/yyyy").parse(txt);
            date1 = new SimpleDateFormat("MM/dd/yyyy").parse(txt);
        } catch (ParseException ex) {

        }

        return date1;
    }

    private String[] splitHoTen(String txt) {
        String[] hoten = new String[2];

        Arrays.fill(hoten, "");

        String arr[] = txt.trim().split(" ");
        if (arr.length >= 2) {
            for (int idx = 0; idx < arr.length - 1; idx++) {
                hoten[0] += arr[idx] + " ";
            }
            hoten[1] = arr[arr.length - 1];
        } else {
            hoten[0] = txt;
        }
        hoten[0] = hoten[0].trim();

        return hoten;
    }

    private void importExcelToDB(String filename) {

        List<String> err = new LinkedList<>();

        try {
            String STT, MSNV, MSNV6, HO, TEN, DATE_IN, DATE_OUT, CHUC_DANH, KHOI, PHONG_BAN, BO_PHAN, TOSX, CHUC_DANH_THUC, TRINH_DO, CHUYEN_MON, GIOI_TINH, NGAY_SINH, NOI_SINH, CMND, CMND_DATE, NOI_CAP, DAN_TOC, QUOC_TICH, NGUYEN_QUAN, DIA_CHI_01, DIA_CHI_02, EMAIL, DIEN_THOAI, NGUOI_THAN, MST, MST_NGAY, CMND_PHU_THUOC, MST_PHU_THUOC, ATM, ATM_NGAN_HANG, SO_BKXH, MA_BENH_VIEN, TEN_BENH_VIEN, MA_BHYT, THAM_GIA_BHXH, KET_THUC_BHXH, LUONG_BHXH, HD_THU_VIEC_01, THOI_GIAN_TV_01, THOI_GIAN_HET_TV_01, HD_THU_VIEC_02, THOI_GIAN_TV_02, THOI_GIAN_HET_TV_02, THOI_HAN_HD_02, HD_THU_VIEC_03, THOI_GIAN_TV_03, THOI_GIAN_HET_TV_03, THOI_HAN_HD_03, HD_THU_VIEC_04, THOI_GIAN_TV_04, THOI_GIAN_HET_TV_04, THOI_HAN_HD_04, HD_THU_VIEC_05, THOI_GIAN_TV_05, THOI_GIAN_HET_TV_05, THOI_HAN_HD_05, SO_TK_BANK;
            String[] arr = new String[61];

            FileInputStream excelFile = new FileInputStream(new File(filename));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            Map<Integer, String> column = getColumnsMap();
            int row_start = 5;

            EmployeeDAO edao = new EmployeeDAO();
            EmployeeDependentDAO dependentDAO = new EmployeeDependentDAO();
            EmployeeContractDAO contractDAO = new EmployeeContractDAO();

            while (iterator.hasNext()) {
                Row r = iterator.next();
                if (--row_start > 0) {
                    continue;
                }

                for (int i = 0; i < arr.length; i++) {
                    arr[i] = "";
                }

                for (int cn = 0; cn < arr.length; cn++) { //r.getLastCellNum()
                    Cell c = r.getCell(cn);
                    if (c == null || c.getCellType() == CellType.BLANK) {
                        // Can't be this cell - it's empty
                        continue;
                    }
                    if (c.getCellType() == CellType.STRING) {
                        String text = c.getStringCellValue();
                        arr[cn] = text;
                    } else if (c.getCellType() == CellType.NUMERIC) {
                        Double dble = c.getNumericCellValue();

                        if (cn == 4 || cn == 5 || cn == 15 || cn == 18 || cn == 42 || cn == 43 || cn == 45 || cn == 46 || cn == 47
                                || cn == 49 || cn == 50
                                || cn == 53 || cn == 54
                                || cn == 57) {
                            Date javaDate = DateUtil.getJavaDate((double) dble);
                            String date = new SimpleDateFormat("MM/dd/yyyy").format(javaDate);
                            arr[cn] = date;

                        } else {
                            if (cn == 1 || cn == 2 || cn == 17 || cn == 28 || cn == 30 || cn == 31 || cn == 32 || cn == 34 || cn == 60) {

                                c.setCellType(CellType.STRING);
                                arr[cn] = c.getStringCellValue();

                            } else {
                                arr[cn] = dble.toString();
                            }

                        }
                    } else if (c.getCellType() == CellType.FORMULA) {
                        if (c.getCachedFormulaResultType() == CellType.NUMERIC) {
                            Double dble = c.getNumericCellValue();
                            arr[cn] = dble.toString();
                        } else {
                            arr[cn] = c.getRichStringCellValue().toString();
                        }
                    } else {
                        arr[cn] = "err:" + cn;
                    }
                }
                String[] arrHoTen = splitHoTen(arr[3]);

                STT = arr[0];
                MSNV = arr[1];
                MSNV6 = arr[2];

                HO = arrHoTen[0];
                TEN = arrHoTen[1];

                DATE_IN = arr[4];
                DATE_OUT = arr[5];
                CHUC_DANH = arr[6];
                KHOI = arr[7];
                PHONG_BAN = arr[8];
                BO_PHAN = arr[9];
                TOSX = arr[10];
                CHUC_DANH_THUC = arr[11];
                TRINH_DO = arr[12];
                CHUYEN_MON = arr[13];
                GIOI_TINH = arr[14];
                NGAY_SINH = arr[15];
                NOI_SINH = arr[16];
                CMND = arr[17];
                CMND_DATE = arr[18];
                NOI_CAP = arr[19];
                DAN_TOC = arr[20];
                QUOC_TICH = arr[21];
                NGUYEN_QUAN = arr[22];
                DIA_CHI_01 = arr[23];
                DIA_CHI_02 = arr[24];
                EMAIL = arr[25];
                DIEN_THOAI = arr[26];
                NGUOI_THAN = arr[27];
                MST = arr[28];
                MST_NGAY = arr[29];
                CMND_PHU_THUOC = arr[30];
                MST_PHU_THUOC = arr[31];
                ATM = arr[32];
                ATM_NGAN_HANG = arr[33];
                SO_BKXH = arr[34];
                MA_BENH_VIEN = arr[35];
                TEN_BENH_VIEN = arr[36];
                MA_BHYT = arr[37];
                THAM_GIA_BHXH = arr[38];
                KET_THUC_BHXH = arr[39];
                LUONG_BHXH = arr[40];
                HD_THU_VIEC_01 = arr[41];
                THOI_GIAN_TV_01 = arr[42];
                THOI_GIAN_HET_TV_01 = arr[43];
                HD_THU_VIEC_02 = arr[44];
                THOI_GIAN_TV_02 = arr[45];
                THOI_GIAN_HET_TV_02 = arr[46];
                THOI_HAN_HD_02 = arr[47];
                HD_THU_VIEC_03 = arr[48];
                THOI_GIAN_TV_03 = arr[49];
                THOI_GIAN_HET_TV_03 = arr[50];
                THOI_HAN_HD_03 = arr[51];
                HD_THU_VIEC_04 = arr[52];
                THOI_GIAN_TV_04 = arr[53];
                THOI_GIAN_HET_TV_04 = arr[54];
                THOI_HAN_HD_04 = arr[55];
                HD_THU_VIEC_05 = arr[56];
                THOI_GIAN_TV_05 = arr[57];
                THOI_GIAN_HET_TV_05 = arr[58];
                THOI_HAN_HD_05 = arr[59];
                SO_TK_BANK = arr[60];

                boolean empty = true;
                for (Integer idx = 0; idx < arr.length; idx++) {
                    if (!arr[idx].isEmpty()) {
                        empty = false;
                        break;
                    }

                }

                if (MSNV.isEmpty() && MSNV6.isEmpty()) {
                    continue;
                }
                if (empty) {
                    continue;
                }

                Double doubleLUONG_BHXH = 0.0;
                Employee empl = new Employee(0,
                        MSNV, MSNV6, HO, TEN,
                        getDate(DATE_IN), getDate(DATE_OUT), CHUC_DANH,
                        KHOI, PHONG_BAN, BO_PHAN,
                        TOSX, CHUC_DANH_THUC, TRINH_DO,
                        CHUYEN_MON, GIOI_TINH, getDate(NGAY_SINH),
                        NOI_SINH, CMND, getDate(CMND_DATE),
                        NOI_CAP, DAN_TOC, QUOC_TICH,
                        NGUYEN_QUAN, DIA_CHI_01, DIA_CHI_02,
                        EMAIL, DIEN_THOAI, NGUOI_THAN,
                        MST, getDate(MST_NGAY), ATM,
                        ATM_NGAN_HANG, SO_BKXH, MA_BENH_VIEN,
                        TEN_BENH_VIEN, MA_BHYT, getDate(THAM_GIA_BHXH),
                        getDate(KET_THUC_BHXH), doubleLUONG_BHXH, SO_TK_BANK,
                        "");

                int employeeId = edao.getEmployeeId(MSNV6);

                if (employeeId == 0) {
                    edao.insert(empl, err);
                    employeeId = empl.getId();
                } else {
                    empl.setId(employeeId);
                    edao.update(empl, err);
                }
                if (!CMND_PHU_THUOC.isEmpty() || !MST_PHU_THUOC.isEmpty()) {
                    EmployeeDependent dependent = new EmployeeDependent(employeeId);
                    dependent.setCode(CMND_PHU_THUOC);
                    dependent.setName(MST_PHU_THUOC);

                    int dependentId = dependentDAO.getEmployeeDependentId(employeeId, CMND_PHU_THUOC);
                    if (dependentId == 0) {
                        dependentDAO.insert(dependent, err);
                    } else {
                        dependent.setId(dependentId);
                        dependentDAO.update(dependent, err);
                    }
                }
                if (!THOI_GIAN_TV_01.isEmpty()) {
                    EmployeeContract contract = new EmployeeContract(employeeId);
                    contract.setCode(HD_THU_VIEC_01);
                    contract.setStartDate(getDate(THOI_GIAN_TV_01));
                    contract.setEndDate(getDate(THOI_GIAN_HET_TV_01));
                    contract.setContractType("Thử việc");
                    
                    int contractId = contractDAO.getEmployeeContractId(employeeId, HD_THU_VIEC_01);
                    if (contractId == 0) {
                        contractDAO.insert(contract, err);
                    } else {
                        contract.setId(contractId);
                        contractDAO.update(contract, err);
                    }
                }
                
                if (!THOI_GIAN_TV_02.isEmpty()) {
                    EmployeeContract contract = new EmployeeContract(employeeId);
                    contract.setCode(HD_THU_VIEC_02);
                    contract.setStartDate(getDate(THOI_GIAN_TV_02));
                    contract.setEndDate(getDate(THOI_GIAN_HET_TV_02));
                    contract.setContractType("Hợp đồng");
                    
                    int contractId = contractDAO.getEmployeeContractId(employeeId, HD_THU_VIEC_02);
                    if (contractId == 0) {
                        contractDAO.insert(contract, err);
                    } else {
                        contract.setId(contractId);
                        contractDAO.update(contract, err);
                    }
                }
                
                if (!THOI_GIAN_TV_03.isEmpty()) {
                    EmployeeContract contract = new EmployeeContract(employeeId);
                    contract.setCode(HD_THU_VIEC_03);
                    contract.setStartDate(getDate(THOI_GIAN_TV_03));
                    contract.setEndDate(getDate(THOI_GIAN_HET_TV_03));
                    contract.setContractType("Hợp đồng");
                    
                    int contractId = contractDAO.getEmployeeContractId(employeeId, HD_THU_VIEC_03);
                    if (contractId == 0) {
                        contractDAO.insert(contract, err);
                    } else {
                        contract.setId(contractId);
                        contractDAO.update(contract, err);
                    }
                }
                
                if (!THOI_GIAN_TV_04.isEmpty()) {
                    EmployeeContract contract = new EmployeeContract(employeeId);
                    contract.setCode(HD_THU_VIEC_04);
                    contract.setStartDate(getDate(THOI_GIAN_TV_04));
                    contract.setEndDate(getDate(THOI_GIAN_HET_TV_04));
                    contract.setContractType("Hợp đồng");
                    
                    int contractId = contractDAO.getEmployeeContractId(employeeId, HD_THU_VIEC_04);
                    if (contractId == 0) {
                        contractDAO.insert(contract, err);
                    } else {
                        contract.setId(contractId);
                        contractDAO.update(contract, err);
                    }
                }
                
                if (!THOI_GIAN_TV_05.isEmpty()) {
                    EmployeeContract contract = new EmployeeContract(employeeId);
                    contract.setCode(HD_THU_VIEC_05);
                    contract.setStartDate(getDate(THOI_GIAN_TV_05));
                    contract.setEndDate(getDate(THOI_GIAN_HET_TV_05));
                    contract.setContractType("Hợp đồng");
                    
                    int contractId = contractDAO.getEmployeeContractId(employeeId, HD_THU_VIEC_05);
                    if (contractId == 0) {
                        contractDAO.insert(contract, err);
                    } else {
                        contract.setId(contractId);
                        contractDAO.update(contract, err);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (err.size() > 0) {
            for (String s : err) {
                System.out.println(s);
            }
        }
    }

    boolean isCellEmpty(final XSSFCell cell) {
        if (cell == null) { // use row.getCell(x, Row.CREATE_NULL_AS_BLANK) to avoid null cells
            return true;
        }

        if (cell.getCellType() == CellType.BLANK) {
            return true;
        }

        if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().trim().isEmpty()) {
            return true;
        }

        return false;
    }
}
