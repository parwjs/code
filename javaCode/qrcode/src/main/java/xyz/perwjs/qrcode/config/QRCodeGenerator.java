package xyz.perwjs.qrcode.config;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;


public class QRCodeGenerator {

    /**
     *
     * @param text 二维码需要包含的信息
     * @param width 二维码的宽度
     * @param height 二维码的高度
     * @param filePath 图片保存路径
     * @throws WriterException 异常
     * @throws IOException 异常
     */
    private static void generateCodeImage(String text,int width,int height,String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE,width,height);
        Path path = FileSystems.getDefault().getPath(filePath);

        MatrixToImageWriter.writeToPath(bitMatrix,"PNG",path);
    }


    /**
     *
     * @param text  生成二维码需要的信息
     * @param width 二维码的宽度
     * @param height 二维码的高度
     * @return
     * @throws WriterException 异常
     * @throws IOException 异常
     */
    public static byte[] getQRCodeImage(String text,int width,int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text,BarcodeFormat.QR_CODE,width,height);

        ByteArrayOutputStream pngOutPutStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix,"PNG",pngOutPutStream);
        byte[] pngData = pngOutPutStream.toByteArray();
        return pngData;
    }
}
