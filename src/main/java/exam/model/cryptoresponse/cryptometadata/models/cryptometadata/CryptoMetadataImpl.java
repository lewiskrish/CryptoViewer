package exam.model.cryptoresponse.cryptometadata.models.cryptometadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import exam.model.cryptoresponse.cryptometadata.models.platform.Platform;
import exam.model.cryptoresponse.cryptometadata.models.urls.Urls;

import java.util.List;

/**
 * Default implementation of CryptoMetadata interface.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoMetadataImpl implements CryptoMetadata {

    private Urls urls;
    private String logo;
    private int id;
    private String name;
    private String symbol;
    private String slug;
    private String description;
    private String notice;
    private String dateAdded;
    private List<String> tags;
    private Platform platform;
    private String category;

    @JsonProperty("urls")
    public Urls getUrls() {
        return this.urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    @JsonProperty("logo")
    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @JsonProperty("id")
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("symbol")
    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @JsonProperty("slug")
    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @JsonProperty("description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("notice")
    public String getNotice() {
        return this.notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    @JsonProperty("date_added")
    public String getDateAdded() {
        return this.dateAdded;
    }

    public void setDate_added(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    @JsonProperty("tags")
    public List<String> getTags() {
        return this.tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @JsonProperty("platform")
    public Platform getPlatform() {
        return this.platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    @JsonProperty("category")
    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}