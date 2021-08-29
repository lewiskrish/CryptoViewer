package exam.model.cryptoresponse.cryptometadata.models.cryptometadata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import exam.model.cryptoresponse.cryptometadata.models.platform.Platform;
import exam.model.cryptoresponse.cryptometadata.models.urls.Urls;

import java.util.List;

/**
 * Interface that models a response from the CoinMarketCap /v1/cryptocurrency/info endpoint.
 */
@JsonDeserialize(as = CryptoMetadataImpl.class)
public interface CryptoMetadata {

    Urls getUrls();

    void setUrls(Urls urls);

    String getLogo();

    void setLogo(String logo);

    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    String getSymbol();

    void setSymbol(String symbol);

    String getSlug();

    void setSlug(String slug);

    String getDescription();

    void setDescription(String description);

    String getNotice();

    void setNotice(String notice);

    String getDateAdded();

    void setDate_added(String dateAdded);

    List<String> getTags();

    void setTags(List<String> tags);

    Platform getPlatform();

    void setPlatform(Platform platform);

    String getCategory();

    void setCategory(String category);
}
