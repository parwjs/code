package xyz.perwjs.qrcode.controller;

import com.google.zxing.WriterException;
import xyz.perwjs.qrcode.config.QRCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@Slf4j
public class QRController {
    /**
     *    二维码的信息，可以用微信、钉钉等任何能扫码的软件扫描
     *      1.如果是普通文本，则扫码显示文本信息
     *      2.如果是链接，则扫码后跳转链接的URL
     */
    private String INFO;

    {
        try {
            INFO = new String("https://www.baidu.com".getBytes("UTF-8"),"ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用ResponseEntity响应文件流（页面显示），Spring会从里面取出数据塞入响应流返回
     * @return
     */
    @GetMapping("qrImageByResponseEntity")
    public ResponseEntity<byte[]> getQRImageByResponseEntity() {

        byte[] qrCode = null;
        try {
            qrCode = QRCodeGenerator.getQRCodeImage(INFO,360,360);
        } catch (Exception e) {
            log.info("Could not generate QR Code , Exception:{}",e.getMessage());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]>(qrCode,headers, HttpStatus.CREATED);
    }


    @GetMapping("downloadQRCode")
    public void downloadQRCode(HttpServletResponse response) throws IOException {
        byte[] qrCode = null;
        try {
            qrCode = QRCodeGenerator.getQRCodeImage(INFO,360,360);
        } catch (Exception e) {
            log.info("Could not generate QR Code , Exception:{}",e.getMessage());
        }

        String fileName = new String("激情二维码.png".getBytes("UTF-8"),"ISO-8859-1");

        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        response.setHeader("content-disposition","attachment;fileName=" + fileName);
        response.setHeader("fileName",fileName);
        response.getOutputStream().write(qrCode);
    }
}
