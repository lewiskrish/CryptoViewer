package exam.model.cryptoresponse.currencymap;

import java.util.Map;

/**
 * Interface used to represent and provide getters to the content returned
 * by calls to the the CoinMarketCap currency map endpoints.
 */
public interface CurrencyMapResponse {

    /**
     * Returns a map of CoinMarketCap ID keys with currency name values.
     *
     * @return the Map with Integer keys and String values
     */
    Map<Integer, String> getCurrencyMap();

    /**
     * Returns a String representing an error that has occurred while retrieving a currency map.
     *
     * @return an error message
     */
    String getErrorMessage();
}
