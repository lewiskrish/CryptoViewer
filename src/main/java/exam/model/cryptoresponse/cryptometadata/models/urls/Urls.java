package exam.model.cryptoresponse.cryptometadata.models.urls;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

/**
 * Interface that models the url data from the response from the CoinMarketCap /v1/cryptocurrency/info endpoint.
 */
@JsonDeserialize(as = UrlsImpl.class)
public interface Urls {

    List<String> getWebsites();

    void setWebsites(List<String> websites);

    List<String> getTechnicalDocs();

    void setTechnicalDocs(List<String> technicaDocs);

    List<String> getTwitters();

    void setTwitters(List<String> twitters);

    List<String> getReddits();

    void setReddits(List<String> reddits);

    List<String> getMessageBoards();

    void setMessageBoards(List<String> messageBoards);

    List<String> getAnnouncements();

    void setAnnouncements(List<String> announcements);

    List<String> getChats();

    void setChats(List<String> chats);

    List<String> getExplorers();

    void setExplorers(List<String> explorers);

    List<String> getSourceCodes();

    void setSourceCodes(List<String> sourceCodes);
}
