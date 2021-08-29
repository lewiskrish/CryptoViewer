package exam.model.reportwriter;

import exam.model.cryptoresponse.cryptometadata.models.cryptometadata.CryptoMetadata;

/**
 * Interface used to provide methods to generate a report.
 */
public interface ReportWriter {

    /**
     * Returns a QR code encoded in base64 containing a string of a report about a cryptocurrency.
     *
     * @param cryptoMetadata the metadata of a cryptocurrency to create a report QR code about
     * @return A base64 encoded QR code
     */
    String generateReportQrCodeAsBase64(CryptoMetadata cryptoMetadata);
}
