package exam.model.facade;

import exam.controller.ModelObserver;
import exam.model.cryptoresponse.cryptometadata.models.cryptometadata.CryptoMetadata;

import java.util.Map;

/**
 * Facade interface used as the access to the model aspect of the application by the views and controllers.
 */
public interface CryptoFacade {

    /**
     * Retrieves from CoinMarketCap and sets the map of cryptocurrencies to be used by the application.
     */
    void retrieveCryptoMap();

    /**
     * Retrieves from CoinMarketCap and sets the map of fiat currencies to be used by the application.
     */
    void retrieveFiatMap();

    /**
     * Retrieves from CoinMarketCap and sets the current cryptocurrency metadata to be used by the application.
     *
     * <p>Preconditions: The cryptocurrency map has been successfully retrieved and set.</p>
     *
     * @param id the CoinMarketCap ID of the cryptocurrency
     */
    void retrieveCryptoMetadata(int id);

    /**
     * Retrieves from CoinMarketCap and sets the current conversion to be used by the application.
     *
     * <p>Preconditions: The cryptocurrency and fiat maps have been successfully retrieved and set.</p>
     *
     * @param fromId the CoinMarketCap ID of the currency to convert from
     * @param toId   the CoinMarkerCap ID of the currency to convert to
     * @param amount the amount of currency to convert
     */
    void retrieveConversion(int fromId, int toId, double amount);

    /**
     * Creates and uploads a QR code image report to Imgur based on the current cryptocurrency metadata.
     *
     * <p>Preconditions: Cryptocurrency metadata has been successfully retrieved and set.</p>
     */
    void sendReport();

    /**
     * Checks if an entry exists in the database for a given CoinMarketCap ID.
     *
     * @param id a CoinMarketCap ID
     * @return if an entry with a matching ID exists
     */
    boolean checkMetadataCache(int id);

    /**
     * Retrieves from the cache and sets the current cryptocurrency metadata to be used by the application.
     *
     * @param id the CoinMarketCap ID of the cryptocurrency
     */
    void retrieveMetadataFromCache(int id);

    /**
     * Returns the current map of cryptocurrencies being used by the application.
     *
     * @return a map of CoinMarketCap ID keys to name values.
     */
    Map<Integer, String> getCryptoMap();

    /**
     * Returns the current map of fiat currencies being used by the application.
     *
     * @return a map of CoinMarketCap ID keys to name values.
     */
    Map<Integer, String> getFiatMap();

    /**
     * Returns the current cryptocurrency metadata being used by the application.
     *
     * @return cryptocurrency metadata
     */
    CryptoMetadata getCryptoMetadata();

    /**
     * Returns the current conversion being used by the application.
     *
     * @return a conversion between an amount of two currencies
     */
    Double getConversion();

    /**
     * Returns the current error message that has been set by the model.
     *
     * @return an error message
     */
    String getErrorMessage();

    /**
     * Returns the link to the most recent report image that has been successfully generated
     *
     * @return a link to an Imgur image
     */
    String getImgurLink();

    /**
     * Adds an observer to the model to be notified of changes to the model's state.
     *
     * @param observer to observer to add
     */
    void addObserver(ModelObserver observer);

}
