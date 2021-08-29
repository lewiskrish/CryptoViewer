package exam.model.cryptoresponse.cryptoquote;

import exam.model.cryptoresponse.cryptoquote.models.CryptoQuote;

/**
 * Interface used to represent and provide getters to the content returned
 * by a call to the the CoinMarketCap /v1/cryptocurrency/quotes/latest endpoint.
 */
public interface CryptoQuoteResponse {

    /**
     * Returns quote data of a cryptocurrency pair as a CryptoQuote object.
     *
     * @return quote for a cryptocurrency pair.
     */
    CryptoQuote getCryptoQuote();

    /**
     * Returns a String representing an error that has occurred while retrieving a quote.
     *
     * @return an error message
     */
    String getErrorMessage();
}
