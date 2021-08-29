package exam.model.cryptoresponse.cryptoquote;

import exam.model.cryptoresponse.cryptoquote.models.CryptoQuote;

/**
 * Default implementation of the CryptoQuoteResponse interface.
 */
public class CryptoQuoteResponseImpl implements CryptoQuoteResponse {

    private final CryptoQuote cryptoQuote;
    private final String errorMessage;

    /**
     * Default CryptoQuoteResponseImpl constructor
     *
     * @param cryptoQuote  quote of a cryptocurrency pair
     * @param errorMessage a String representing an error that has occurred while retrieving a quote
     * @throws IllegalArgumentException if both parameters are null, or both are not null
     */
    public CryptoQuoteResponseImpl(CryptoQuote cryptoQuote, String errorMessage) {
        if ((null == cryptoQuote && null == errorMessage) || (null != cryptoQuote && null != errorMessage)) {
            throw new IllegalArgumentException();
        }
        this.cryptoQuote = cryptoQuote;
        this.errorMessage = errorMessage;
    }

    @Override
    public CryptoQuote getCryptoQuote() {
        return this.cryptoQuote;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
