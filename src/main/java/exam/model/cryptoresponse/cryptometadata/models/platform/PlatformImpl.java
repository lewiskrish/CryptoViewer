package exam.model.cryptoresponse.cryptometadata.models.platform;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Default implementation of the Platform interface.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlatformImpl implements Platform {

    private int id;
    private String name;
    private String symbol;
    private String slug;
    private String tokenAddress;

    @Override
    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    @JsonProperty("symbol")
    public String getSymbol() {
        return symbol;
    }

    @Override
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    @JsonProperty("slug")
    public String getSlug() {
        return slug;
    }

    @Override
    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    @JsonProperty("token_address")
    public String getTokenAddress() {
        return tokenAddress;
    }

    @Override
    public void setTokenAddress(String tokenAddress) {
        this.tokenAddress = tokenAddress;
    }

}
