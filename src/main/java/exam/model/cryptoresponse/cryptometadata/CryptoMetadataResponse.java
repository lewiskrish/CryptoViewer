package exam.model.cryptoresponse.cryptometadata;

import exam.model.cryptoresponse.cryptometadata.models.cryptometadata.CryptoMetadata;

/**
 * Interface used to represent and provide getters to the content returned
 * by a call to the the CoinMarketCap /v1/cryptocurrency/info endpoint.
 */
public interface CryptoMetadataResponse {

    /**
     * Returns metadata of a cryptocurrency as a CryptoMetadata object.
     *
     * @return metadata of a cryptocurrency.
     */
    CryptoMetadata getCryptoMetadata();

    /**
     * Returns a String representing an error that has occurred while retrieving metadata.
     *
     * @return an error message
     */
    String getErrorMessage();
}
