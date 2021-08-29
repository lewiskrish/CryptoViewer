package exam.model.cryptoresponse.currencymap;

import java.util.Map;

/**
 * Default implementation of the CryptoMapResponse interface.
 */
public class CurrencyMapResponseImpl implements CurrencyMapResponse {

    private final Map<Integer, String> cryptoMap;
    private final String errorMessage;

    /**
     * Default CryptoMapResponseImpl constructor.
     *
     * @param cryptoMap    A {@link Map} of the format outlined by the {@link CurrencyMapResponse} getCryptoMap method
     * @param errorMessage A {@link String} representing an error that has occurred while retrieving a cryptocurrency map
     * @throws IllegalArgumentException if both parameters are null, or both are not null
     */
    public CurrencyMapResponseImpl(Map<Integer, String> cryptoMap, String errorMessage) {
        if ((null == cryptoMap && null == errorMessage) || (null != cryptoMap && null != errorMessage)) {
            throw new IllegalArgumentException("exactly one parameter may be not null");
        }
        this.cryptoMap = cryptoMap;
        this.errorMessage = errorMessage;
    }

    @Override
    public Map<Integer, String> getCurrencyMap() {
        return this.cryptoMap;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
