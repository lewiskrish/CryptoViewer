package exam.model.cryptoresponse.cryptometadata;

import exam.model.cryptoresponse.cryptometadata.models.cryptometadata.CryptoMetadata;

/**
 * Default implementation of the CryptoMetadataResponse interface.
 */
public class CryptoMetadataResponseImpl implements CryptoMetadataResponse {

    private final CryptoMetadata cryptoMetadata;
    private final String errorMessage;

    /**
     * Default CryptoMetadataResponseImpl constructor
     *
     * @param cryptoMetadata metadata of a cryptocurrency
     * @param errorMessage   a String representing an error that has occurred while retrieving metadata
     * @throws IllegalArgumentException if both parameters are null, or both are not null
     */
    public CryptoMetadataResponseImpl(CryptoMetadata cryptoMetadata, String errorMessage) {
        if ((null == cryptoMetadata && null == errorMessage) || (null != cryptoMetadata && null != errorMessage)) {
            throw new IllegalArgumentException("exactly one parameter may be not null");
        }
        this.cryptoMetadata = cryptoMetadata;
        this.errorMessage = errorMessage;
    }

    @Override
    public CryptoMetadata getCryptoMetadata() {
        return this.cryptoMetadata;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
