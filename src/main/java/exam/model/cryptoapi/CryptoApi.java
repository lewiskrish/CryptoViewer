package exam.model.cryptoapi;

/**
 * Interface providing methods used to retrieve data on currencies from the CoinMarketCap API.
 */
public interface CryptoApi {

    /**
     * Returns a JSON response in the format of the CoinMarketCap /v1/cryptocurrency/map endpoint.
     *
     * @return the JSON response
     */
    String getCryptoMap();

    /**
     * Returns a JSON response in the format of the CoinMarketCap /v1/fiat/map endpoint.
     *
     * @return the JSON response
     */
    String getFiatMap();

    /**
     * Returns a JSON response in the format of the CoinMarketCap /v1/cryptocurrency/info endpoint.
     *
     * @param id the CoinMarketCap ID of a cryptocurrency
     * @return the JSON response
     */
    String getCryptoMetadata(int id);

    /**
     * Returns a JSON response in the format of the CoinMarketCap /v1/cryptocurrency/quotes/latest endpoint.
     *
     * @param id        the CoinMarketCap ID of a base cryptocurrency
     * @param convertId the CoinMarketCap ID of a cryptocurrency to receive a quote in
     * @return the JSON response
     */
    String getCryptoQuote(int id, int convertId);
}
