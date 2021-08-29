package exam.model.cryptoresponse.cryptometadata.models.urls;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Default implementation of Urls interface.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UrlsImpl implements Urls {

    private List<String> websites;
    private List<String> technicalDocs;
    private List<String> twitters;
    private List<String> reddits;
    private List<String> messageBoards;
    private List<String> announcements;
    private List<String> chats;
    private List<String> explorers;
    private List<String> sourceCodes;

    @Override
    @JsonProperty("website")
    public List<String> getWebsites() {
        return this.websites;
    }

    @Override
    public void setWebsites(List<String> websites) {
        this.websites = websites;
    }

    @Override
    @JsonProperty("technical_doc")
    public List<String> getTechnicalDocs() {
        return this.technicalDocs;
    }

    @Override
    public void setTechnicalDocs(List<String> technicaDocs) {
        this.technicalDocs = technicaDocs;
    }

    @Override
    @JsonProperty("twitter")
    public List<String> getTwitters() {
        return this.twitters;
    }

    @Override
    public void setTwitters(List<String> twitters) {
        this.twitters = twitters;
    }

    @Override
    @JsonProperty("reddit")
    public List<String> getReddits() {
        return this.reddits;
    }

    @Override
    public void setReddits(List<String> reddits) {
        this.reddits = reddits;
    }

    @Override
    @JsonProperty("message_board")
    public List<String> getMessageBoards() {
        return this.messageBoards;
    }

    @Override
    public void setMessageBoards(List<String> messageBoards) {
        this.messageBoards = messageBoards;
    }

    @Override
    @JsonProperty("announcement")
    public List<String> getAnnouncements() {
        return this.announcements;
    }

    @Override
    public void setAnnouncements(List<String> announcements) {
        this.announcements = announcements;
    }

    @Override
    @JsonProperty("chat")
    public List<String> getChats() {
        return this.chats;
    }

    @Override
    public void setChats(List<String> chats) {
        this.chats = chats;
    }

    @Override
    @JsonProperty("explorer")
    public List<String> getExplorers() {
        return this.explorers;
    }

    @Override
    public void setExplorers(List<String> explorers) {
        this.explorers = explorers;
    }

    @Override
    @JsonProperty("source_code")
    public List<String> getSourceCodes() {
        return this.sourceCodes;
    }

    @Override
    public void setSourceCodes(List<String> sourceCodes) {
        this.sourceCodes = sourceCodes;
    }

}
