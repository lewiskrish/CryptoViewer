package exam.model.jsonmapper;

import exam.model.cryptoresponse.cryptometadata.CryptoMetadataResponse;
import exam.model.cryptoresponse.cryptoquote.CryptoQuoteResponse;
import exam.model.cryptoresponse.currencymap.CurrencyMapResponse;
import exam.model.imgurresponse.uploadresponse.ImgurUploadResponse;

/**
 * Interface used to provide methods for mapping JSON data strings to objects modelling API responses.
 */
public interface JsonMapper {

    /**
     * Returns a CurrencyMapResponse mapped from JSON data from the CoinMarketCap /v1/XXX/map endpoints.
     *
     * @param jsonData JSON in the format returned by the CoinMarketCap /v1/XXX/map endpoints
     * @return a CurrencyMapResponse mapped from the JSON data
     */
    CurrencyMapResponse mapCurrencyMapResponse(String jsonData);

    /**
     * Returns a CryptoMetadataResponse mapped from JSON data from the CoinMarketCap /v1/cryptocurrency/info endpoint.
     *
     * @param jsonData JSON in the format returned by the CoinMarketCap /v1/cryptocurrency/info endpoint
     * @return a CryptoMetadataResponse mapped from the JSON data
     */
    CryptoMetadataResponse mapCryptoMetadataResponse(String jsonData, int id);

    /**
     * Returns a CryptoQuoteResponse mapped from JSON data from the CoinMarketCap /v1/cryptocurrency/quotes/latest endpoint.
     *
     * @param jsonData JSON in the format returned by the CoinMarketCap /v1/cryptocurrency/quotes/latest endpoint
     * @return a CryptoQuoteResponse mapped from the JSON data
     */
    CryptoQuoteResponse mapCryptoQuoteResponse(String jsonData, int id, int convertId);

    /**
     * Returns an ImgurUploadResponse mapped from JSON data from the Imgur /3/image endpoint.
     *
     * @param jsonData JSON in the format returned by the Imgur /3/image endpoint
     * @return an ImgurUploadResponse mapped from the JSON data
     */
    ImgurUploadResponse mapImgurUploadResponse(String jsonData);

}
