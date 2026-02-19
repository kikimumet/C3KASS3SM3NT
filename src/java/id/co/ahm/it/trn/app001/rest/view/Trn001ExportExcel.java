package id.co.ahm.it.trn.app001.rest.view;

/**
 *
 * @author fadli
 */

import id.co.ahm.it.trn.app001.vo.Trn001ExportVo;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.springframework.web.servlet.view.document.AbstractXlsxStreamingView; 

public class Trn001ExportExcel extends AbstractXlsxStreamingView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        try {
            List<Trn001ExportVo> dataList = (List<Trn001ExportVo>) model.get("data");
            SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("Data Kendaraan");
            
            // Styles
            CellStyle titleStyle = workbook.createCellStyle();
            Font titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 14);
            titleStyle.setFont(titleFont);
            titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
            
            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle textStyle = createBorderStyle(workbook, CellStyle.ALIGN_LEFT);
            CellStyle centerStyle = createBorderStyle(workbook, CellStyle.ALIGN_CENTER);
            
            // Title
            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("LAPORAN DATA KENDARAAN");
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 11)); // Merge sampai kolom terakhir
            
            // Header Table
            Row header = sheet.createRow(2);
            String[] headers = {
                "No", "No Registrasi", "Nama Pemilik", "Nomor Telepon", "Alamat", 
                "Tanggal Transaksi", "Kode Motor", "Deskripsi Motor", 
                "Kode Warna", "Deskripsi Warna", // Header Deskripsi Warna
                "Nomor Frame", "Nomor Engine"
            };
            
            for (int i = 0; i < headers.length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }
            
            // Isi
            int rowNum = 3;
            int no = 1;
            
            if (dataList != null && !dataList.isEmpty()) {
                for (Trn001ExportVo vo : dataList) {
                    Row row = sheet.createRow(rowNum++);
                    int col = 0;
                    
                    createCell(centerStyle, row, no++, col++); 
                    createCell(textStyle, row, vo.getRegistrationNumber(), col++);
                    createCell(textStyle, row, vo.getOwnerName(), col++);
                    createCell(textStyle, row, vo.getPhoneNumber(), col++);
                    createCell(textStyle, row, vo.getAddress(), col++);
                    createCell(centerStyle, row, vo.getTransactionDate(), col++);
                    createCell(textStyle, row, vo.getMotorcycleCode(), col++);
                    createCell(textStyle, row, vo.getMotorcycleDescription(), col++);
                    createCell(textStyle, row, vo.getColorCode(), col++);
                    createCell(textStyle, row, vo.getColorDescription(), col++); // Isi Data Deskripsi Warna
                    createCell(textStyle, row, vo.getFrameNumber(), col++);
                    createCell(textStyle, row, vo.getEngineNumber(), col++);
                }
            }
            
            for(int i=0; i<headers.length; i++) {
                sheet.setColumnWidth(i, 20 * 256);
            }
            
            // Name File
            SimpleDateFormat sdfFile = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String fileName = "Laporan_Kendaraan_" + sdfFile.format(new Date()) + ".xlsx";
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal generate excel file");
        }
    }
    
    // Helper Methods
    private CellStyle createHeaderStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        setBorders(style);
        return style;
    }
    
    private CellStyle createBorderStyle(Workbook wb, short alignment) {
        CellStyle style = wb.createCellStyle();
        style.setAlignment(alignment);
        setBorders(style);
        return style;
    }
    
    private void setBorders(CellStyle style) {
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
    }
    
    private void createCell(CellStyle style, Row row, Object obj, int col) {
        Cell cell = row.createCell(col);
        cell.setCellStyle(style);
        
        if (obj instanceof String) {
            cell.setCellValue((String) obj);
        } else if (obj instanceof Integer) {
            cell.setCellValue((Integer) obj);
        } else if (obj instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            cell.setCellValue(sdf.format((Date) obj));
        } else {
            cell.setCellValue("");
        }
    }
}