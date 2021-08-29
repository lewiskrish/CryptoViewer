package exam.model.database;

/**
 * Interface providing methods used to add and retrieve cached data on cryptocurrencies
 */
public interface Database {

    /**
     * Initializes a database, creating it if it does not exist and creates relevant tables.
     */
    void init();

    /**
     * Adds metadata of a cryptocurrency to the database.
     *
     * @param id       the CoinMarketCap ID of the cryptocurrency
     * @param jsonData a {@link String} of JSON data in the format returned by the
     *                 CoinMarketCap /v1/cryptocurrency/info endpoint
     */
    void addCryptoMetadata(int id, String jsonData);

    /**
     * Returns metadata of a cryptocurrency from the database.
     *
     * @param id the CoinMarketCap ID of the cryptocurrency
     * @return a {@link String} of JSON data in the format returned by the
     * CoinMarketCap /v1/cryptocurrency/info endpoint
     */
    String getCryptoMetadata(int id);

}
