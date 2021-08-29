package exam.model.reportwriter;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import exam.model.cryptoresponse.cryptometadata.models.cryptometadata.CryptoMetadata;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * Default implementation of ReportWriter interface.
 */
public class ReportWriterImpl implements ReportWriter {

    @Override
    public String generateReportQrCodeAsBase64(CryptoMetadata cryptoMetadata) {
        String reportString = formatReport(cryptoMetadata);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix;
        try {
            bitMatrix = qrCodeWriter.encode(reportString, BarcodeFormat.QR_CODE, 575, 575);
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        byte[] image = byteArrayOutputStream.toByteArray();
        return Base64.getEncoder().encodeToString(image);
    }

    private String formatReport(CryptoMetadata cryptoMetadata) {
        StringBuilder report = new StringBuilder();
        report.append("Name: ").append(cryptoMetadata.getName()).append("\n");
        report.append("Symbol: ").append(cryptoMetadata.getSymbol()).append("\n");
        report.append("Category: ").append(cryptoMetadata.getCategory()).append("\n");
        report.append("CoinMarketCap ID: ").append(cryptoMetadata.getId()).append("\n");
        report.append("Description: ").append(cryptoMetadata.getDescription()).append("\n");
        if (null != cryptoMetadata.getUrls().getWebsites() && cryptoMetadata.getUrls().getWebsites().size() > 0) {
            report.append("Website: ").append(cryptoMetadata.getUrls().getWebsites().get(0)).append("\n");
        }

        return report.toString();
    }
}
