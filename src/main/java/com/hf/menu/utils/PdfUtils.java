package com.hf.menu.utils;


import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.LogMessageConstant;
import com.itextpdf.html2pdf.css.apply.impl.DefaultCssApplierFactory;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.util.StreamUtil;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.font.Range;
import com.itextpdf.layout.font.RangeBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @ClassName : PdfUtil
 * @Description : pdf工具类
 * @Author : mark
 * @Date: 2022-10-17 11:47
 */
@Slf4j
@Component
public class PdfUtils {

    public  void createPDF(OutputStream out, String html) {
        try {
            Range range = new RangeBuilder().addRange(0, 0x058F).addRange(0x0E80, Integer.MAX_VALUE).create();
            ConverterProperties properties = new ConverterProperties();
            properties.setCharset("UTF-8");
            DefaultCssApplierFactory cssApplierFactory = new DefaultCssApplierFactory();
            properties.setCssApplierFactory(cssApplierFactory);
            FontProvider fontProvider = new DefaultFontProvider();
            fontProvider.addSystemFonts();
            try (InputStream stream = PdfUtils.class.getResource("/template/font/simkai.ttf").openStream()) {
                byte[] fontProgramBytes = StreamUtil.inputStreamToArray(stream);
                fontProvider.addFont(fontProgramBytes, null, range);
            } catch (Exception e) {
               log.error(LogMessageConstant.ERROR_LOADING_FONT,e);
            }
            try (InputStream stream = PdfUtils.class.getResource("/template/chinese.simfang.ttf").openStream()) {
                byte[] fontProgramBytes = StreamUtil.inputStreamToArray(stream);
                fontProvider.addFont(fontProgramBytes, null,range );
            } catch (Exception e) {
               log.error(LogMessageConstant.ERROR_LOADING_FONT,e);
            }
            properties.setFontProvider(fontProvider);

            HtmlConverter.convertToPdf(html, out, properties);
        } catch (Exception e) {
            log.error("生成pdf异常", e);
        }
    }


    public InputStream pdfToImage(InputStream inputStream) {
        //图像合并使用参数
        int width = 0; // 总宽度
        int[] singleImgRGB; // 保存一张图片中的RGB数据
        int shiftHeight = 0;
        BufferedImage imageResult = null;//保存每张图片的像素值
        InputStream input=null;
        try {
            //利用PdfBox生成图像
            PDDocument pdDocument = PDDocument.load(inputStream);
            PDFRenderer renderer = new PDFRenderer(pdDocument);
            //循环每个页码
            for (int i = 0, len = pdDocument.getNumberOfPages(); i < len; i++) {
                //dpi参数越大，越清晰
                BufferedImage image =null;
                try{
                    image=renderer.renderImageWithDPI(i, 80, ImageType.RGB);
                }catch (Exception e){

                }
                int imageHeight = image.getHeight();
                int imageWidth = image.getWidth();
                if (i == 0) {//计算高度和偏移量
                    width = imageWidth;//使用第一张图片宽度;
                    //保存每页图片的像素值
                    imageResult = new BufferedImage(width, imageHeight * len, BufferedImage.TYPE_INT_RGB);
                } else {
                    shiftHeight += imageHeight; // 计算偏移高度
                }
                singleImgRGB = image.getRGB(0, 0, width, imageHeight, null, 0, width);
                imageResult.setRGB(0, shiftHeight, width, imageHeight, singleImgRGB, 0, width); // 写入流中
            }
            pdDocument.close();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(imageResult, "png", os);
            input = new ByteArrayInputStream(os.toByteArray());
        } catch (Exception e) {
            log.error(" 从PDF转换图片格式异常!", e);
        }
        return input;
    }





}
