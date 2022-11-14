package com.hf.menu.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.hf.menu.common.BizMergeStrategy;
import com.hf.menu.common.RowRangeDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: easyexcel-demo
 * @description:
 * @author: lhf
 * @create: 2020-04-17 16:09
 **/
public class EasyExcelUtils {


    /**
     * 导入Excel
     *
     * @param outputStream      输出流
     * @param dataList          导出的数据
     * @param classT            模板类
     * @param sheetName         sheetName
     * @param cellWriteHandlers 样式处理类
     */
    public static void writeExcelWithModel(String fileName, HttpServletResponse response, List<? extends Object> dataList, Class<? extends Object> classT, String sheetName, CellWriteHandler... cellWriteHandlers) throws IOException {

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String fileNames = URLEncoder.encode(fileName + "-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()), StandardCharsets.UTF_8.name());
        response.setHeader("Content-disposition", "attachment;filename=" + fileNames + ".xlsx");

        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 单元格策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 初始化表格样式
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        ExcelWriterSheetBuilder excelWriterSheetBuilder = EasyExcel.write(response.getOutputStream(), classT).sheet(sheetName).registerWriteHandler(horizontalCellStyleStrategy);
        if (null != cellWriteHandlers && cellWriteHandlers.length > 0) {
            for (int i = 0; i < cellWriteHandlers.length; i++) {
                excelWriterSheetBuilder.registerWriteHandler(cellWriteHandlers[i]);
            }
        }
        // 开始导出
        excelWriterSheetBuilder.doWrite(dataList);
    }


    /**
     * 导出 Excel ：一个 sheet，带表头
     *
     * @param fileName    导出的文件名
     * @param response    HttpServletResponse
     * @param classModule 映射实体类，Excel 模型
     * @param data        数据 list
     * @throws IOException
     */
    public static void exportExcel(String fileName, HttpServletResponse response, Class classModule, List data) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String fileNames = URLEncoder.encode(fileName + "-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()), StandardCharsets.UTF_8.name());
        response.setHeader("Content-disposition", "attachment;filename=" + fileNames + ".xlsx");
        EasyExcel.write(response.getOutputStream(), classModule).sheet(fileName).doWrite(data);
    }

    /**
     * excel 合并导出
     *
     * @param fileName
     * @param response
     * @param classModule
     * @param data
     * @throws IOException
     */
    public static void exportMergeExcel(String fileName, HttpServletResponse response, Class classModule, List data,
                                        Map<String, List<RowRangeDto>> strategyMap) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String fileNames = URLEncoder.encode(fileName + "-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()), StandardCharsets.UTF_8.name());
        response.setHeader("Content-disposition", "attachment;filename=" + fileNames + ".xlsx");

        //自定义 合并行 列
        int[] mergeColumeIndex = {0, 1, 2};
        int mergeRowIndex = 1;
        /*EasyExcel.write(response.getOutputStream(),classModule).sheet(fileName)
                .registerWriteHandler(new ExcelFillCellMergeStrategy(mergeRowIndex, mergeColumeIndex)).doWrite(data);*/
        EasyExcel.write(response.getOutputStream(), classModule)
                .registerWriteHandler(new BizMergeStrategy(strategyMap))
                .sheet(fileName).doWrite(data);
    }

}
