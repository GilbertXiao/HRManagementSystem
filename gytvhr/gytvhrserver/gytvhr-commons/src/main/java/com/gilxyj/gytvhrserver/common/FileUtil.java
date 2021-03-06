package com.gilxyj.gytvhrserver.common;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;


import com.gilxyj.gytvhrserver.exception.NormalException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class FileUtil {

    public static final String[] EXCEL_EXT = {"xls","xlsx"};

    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,String fileName,boolean isCreateHeader, HttpServletResponse response){
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);

    }
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,String fileName, HttpServletResponse response){
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }

    /**
     *
     * @param templateExcelPath
     * @param sheetName
     * @param dataMap
     * @param fileName
     * @param response
     */
    public static void  exportExcelFromTemplate(String templateExcelPath,String[] sheetName ,Map<String,Object> dataMap, String fileName, HttpServletResponse response ){
        TemplateExportParams templateExportParams = new TemplateExportParams();
        templateExportParams.setTemplateUrl("templates/excels/"+templateExcelPath);
        templateExportParams.setSheetName(sheetName);
        defaultExportFromTemplate(dataMap,fileName,response,templateExportParams);
    }

    public static ResponseEntity<byte[]> exportExcelFromTemplate2(String templateExcelPath, String[] sheetName , Map<String,Object> dataMap, String fileName ){
        HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;
        TemplateExportParams templateExportParams = new TemplateExportParams();
        templateExportParams.setTemplateUrl("templates/excels/"+templateExcelPath);
        templateExportParams.setSheetName(sheetName);
       /* Integer[] sheetNum={0,1};
        templateExportParams.setSheetNum(sheetNum);*/
        Workbook workbook = ExcelExportUtil.exportExcel(templateExportParams,dataMap);
        try {
            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            baos = new ByteArrayOutputStream();
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(),headers, HttpStatus.CREATED);
    }

    /**
     * 一个excel 创建多个sheet
     *
     * @param list 多个Map key title 对应表格Title key entity 对应表格对应实体 key data
     *            Collection 数据
     * @param fileName
     * @param response
     */
    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response){
    	defaultExport(list, fileName, response);
    }

    public static void  defaultExportFromTemplate(Map<String,Object> dataMap, String fileName, HttpServletResponse response, TemplateExportParams templateExportParams){
        Workbook workbook = ExcelExportUtil.exportExcel(templateExportParams,dataMap);
        if (workbook != null){
            downLoadExcel(fileName, response, workbook);
        }
    }

    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response, ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,pojoClass,list);
        if (workbook != null){
            downLoadExcel(fileName, response, workbook);
        }
    }

    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new NormalException(e.getMessage());
        }
    }

    /**
     * 一个excel 创建多个sheet
     *
     * @param list 多个Map key title 对应表格Title key entity 对应表格对应实体 key data
     *            Collection 数据
     * @param fileName
     * @param response
     */
    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
    	Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
    	if (workbook != null){
            downLoadExcel(fileName, response, workbook);
        }
    }

    public static <T> List<T> importExcel(String filePath,Integer titleRows,Integer headerRows, Class<T> pojoClass){
        if (StringUtils.isBlank(filePath)){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        }catch (NoSuchElementException e){
            throw new NormalException("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            throw new NormalException(e.getMessage());
        }
        return list;
    }

    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass){
        if (file == null){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        }catch (NoSuchElementException e){
            throw new NormalException("excel文件不能为空");
        } catch (Exception e) {
            throw new NormalException(e.getMessage());
        }
        return list;
    }

    /**
     * 检查文件名是否符合要求
     * @param fileName 待监测文件名
     * @param exts 后缀名集合
     * @return
     */
    public static boolean checkExt(String fileName,String[] exts){
        if(StringUtils.isBlank(fileName)){
            throw new NormalException("待校验文件名不能为空");
        }

        if (exts == null || exts.length == 0){
            throw new NormalException("文件后缀名数组不能为空");
        }

        String ext = fileName.substring(fileName.lastIndexOf(".")+1);
        for (int i = 0;i<exts.length;i++){
            if (ext.equals(exts[i])){
                return true;
            }
        }
        return false;
    }


}
