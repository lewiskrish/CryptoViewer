package exam.controller;

import exam.model.cryptoresponse.cryptometadata.models.cryptometadata.CryptoMetadata;
import exam.model.cryptoresponse.cryptometadata.models.urls.Urls;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controller to manage the view associated with displaying an info panel for cryptocurrencies.
 */
public class InfoPanelController extends AbstractController implements ModelObserver {

    @FXML
    private ImageView cryptoImage;

    @FXML
    private Text cryptoName;

    @FXML
    private TextArea cryptoMetadata;

    @Override
    public void notifyObserver() {
        Platform.runLater(this::updateMetadata);
    }

    private void updateMetadata() {
        if (null != model.getErrorMessage()) {
            cryptoMetadata.setText(model.getErrorMessage());
            return;
        }
        if (null == model.getCryptoMetadata()) {
            return;
        }

        CryptoMetadata cryptoMetadata = model.getCryptoMetadata();

        try {
            modelTaskExecutor.execute(() -> {
                Image cryptoLogo = new Image(cryptoMetadata.getLogo());
                Platform.runLater(() -> {
                    cryptoImage.setImage(cryptoLogo);
                });
            });
        } catch (NullPointerException | IllegalArgumentException ignored) {
            cryptoImage.setImage(new Image("/ethereum.png"));
        }
        cryptoName.setText(cryptoMetadata.getName() + " (" + cryptoMetadata.getSymbol() + ")");

        StringBuilder metadata = new StringBuilder();

        metadata.append("Description: \n");
        metadata.append("\t").append(cryptoMetadata.getDescription()).append("\n\n");

        metadata.append("Category: \n");
        metadata.append("\t").append(cryptoMetadata.getCategory()).append("\n\n");

        if (null != cryptoMetadata.getNotice() && cryptoMetadata.getNotice().length() > 0) {
            metadata.append("Notice: \n");
            metadata.append("\t").append(cryptoMetadata.getNotice()).append("\n\n");
        }

        if (null != cryptoMetadata.getPlatform()) {
            metadata.append("Platform: ").append("\n");
            metadata.append("\tParent: ").append(cryptoMetadata.getPlatform().getName()).append("\n");
            metadata.append("\tAddress: ").append(cryptoMetadata.getPlatform().getTokenAddress()).append("\n\n");
        }

        metadata.append("Date added: \n");
        // Date formatting adapted from https://stackoverflow.com/a/11046198
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(cryptoMetadata.getDateAdded());
            String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
            metadata.append("\t").append(formattedDate).append("\n\n");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Urls urls = cryptoMetadata.getUrls();
        metadata.append("Links: \n");

        metadata.append("\tWebsites: \n");
        for (String website : urls.getWebsites()) {
            metadata.append("\t\t").append(website).append("\n");
        }
        metadata.append("\n");
        metadata.append("\tTechnical Docs: \n");
        for (String doc : urls.getTechnicalDocs()) {
            metadata.append("\t\t").append(doc).append("\n");
        }
        metadata.append("\n");
        metadata.append("\tTwitter: \n");
        for (String twitter : urls.getTwitters()) {
            metadata.append("\t\t").append(twitter).append("\n");
        }
        metadata.append("\n");
        metadata.append("\tSubreddits: \n");
        for (String sub : urls.getReddits()) {
            metadata.append("\t\t").append(sub).append("\n");
        }
        metadata.append("\n");
        metadata.append("\tMessage Boards: \n");
        for (String board : urls.getMessageBoards()) {
            metadata.append("\t\t").append(board).append("\n");
        }
        metadata.append("\n");
        metadata.append("\tAnnouncements: \n");
        for (String announcement : urls.getAnnouncements()) {
            metadata.append("\t\t").append(announcement).append("\n");
        }
        metadata.append("\n");
        metadata.append("\tChats: \n");
        for (String chat : urls.getChats()) {
            metadata.append("\t\t").append(chat).append("\n");
        }
        metadata.append("\n");
        metadata.append("\tExplorers: \n");
        for (String explorer : urls.getExplorers()) {
            metadata.append("\t\t").append(explorer).append("\n");
        }
        metadata.append("\n");
        metadata.append("\tSource Code: \n");
        for (String code : urls.getSourceCodes()) {
            metadata.append("\t\t").append(code).append("\n");
        }
        metadata.append("\n");

        metadata.append("Slug: \n");
        metadata.append("\t").append(cryptoMetadata.getSlug()).append("\n\n");

        metadata.append("CoinMarketCap ID: \n");
        metadata.append("\t").append(cryptoMetadata.getId()).append("\n\n");

        if (null != cryptoMetadata.getTags() && cryptoMetadata.getTags().size() > 0) {
            metadata.append("Tags: \n");
            for (String tag : cryptoMetadata.getTags()) {
                metadata.append("\t").append(tag).append(", ");
            }
            metadata.append("\n\n");
        }

        if (!this.cryptoMetadata.getText().equals(metadata.toString())) {
            this.cryptoMetadata.setText(metadata.toString());
        }
    }

}
