package exam.model.cryptoresponse.cryptometadata.models.platform;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Interface that models the platform data from the response from the CoinMarketCap /v1/cryptocurrency/info endpoint.
 */
@JsonDeserialize(as = PlatformImpl.class)
public interface Platform {

    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    String getSymbol();

    void setSymbol(String symbol);

    String getSlug();

    void setSlug(String slug);

    String getTokenAddress();

    void setTokenAddress(String tokenAddress);
}
