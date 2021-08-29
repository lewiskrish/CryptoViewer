package exam.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.util.Map;

/**
 * Controller to manage the view associated with selecting and generating a report on a cryptocurrency.
 */
public class CryptoListController extends AbstractController implements ModelObserver {

    @FXML
    private TextArea errorMessage;

    @FXML
    private ListView<String> cryptoList;

    @FXML
    private Button selectCrypto;

    @FXML
    private Button createReport;

    @FXML
    private Button yes;

    @FXML
    private Button no;

    @Override
    public void notifyObserver() {
        Platform.runLater(this::showLinkToReport);
    }

    /**
     * Populates a ListView of cryptocurrencies with cryptocurrencies in the form "%CoinMarketCapID% - %name%".
     */
    public void populate() {
        if (null != model.getErrorMessage()) {
            errorMessage.setText(model.getErrorMessage());
            return;
        }

        modelTaskExecutor.execute(() -> {
            if (null == model.getCryptoMap()) {
                model.retrieveCryptoMap();
            }
            Platform.runLater(() -> {
                Map<Integer, String> cryptoMap = model.getCryptoMap();
                // Adapted from https://www.geeksforgeeks.org/iterate-map-java/
                for (Map.Entry<Integer, String> entry : cryptoMap.entrySet()) {
                    cryptoList.getItems().add(entry.getKey() + " - " + entry.getValue());
                }
            });
        });
    }

    /**
     * Instructs the model to retrieve data on the ListView's currently selected cryptocurrency.
     *
     * @param event the MouseEvent created when the selectCrypto {@link Button} is clicked
     */
    @FXML
    public void selectCrypto(MouseEvent event) {
        String selectedCrypto = cryptoList.getSelectionModel().getSelectedItem();
        if (null == selectedCrypto) {
            errorMessage.setText("No crypto selected!");
            return;
        }
        errorMessage.setText("");

        int id = Integer.parseInt(selectedCrypto.split(" -")[0]);

        modelTaskExecutor.execute(() -> {
            if (model.checkMetadataCache(id)) {
                Platform.runLater(this::askToUseCache);
                return;
            }
            model.retrieveCryptoMetadata(id);
        });
    }

    /**
     * Instructs the model to create and upload a report its current stored cryptocurrency.
     *
     * @param event the MouseEvent created when the createReport {@link Button} is clicked
     */
    @FXML
    public void createReport(MouseEvent event) {
        errorMessage.setText("");
        String selectedCrypto = cryptoList.getSelectionModel().getSelectedItem();
        if (null == selectedCrypto) {
            errorMessage.setText("No crypto selected!");
            return;
        }

        modelTaskExecutor.execute(() -> model.sendReport());
    }

    /**
     * Instructs the model to retrieve the requested data from its cache.
     *
     * @param event the MouseEvent created when the yes {@link Button} is clicked
     */
    @FXML
    public void useCache(MouseEvent event) {
        String selectedCrypto = cryptoList.getSelectionModel().getSelectedItem();
        final int AUD_ID = 2782;
        model.retrieveMetadataFromCache(Integer.parseInt(selectedCrypto.split(" -")[0]));
        yes.setDisable(true);
        no.setDisable(true);
        selectCrypto.setDisable(false);
        cryptoList.setDisable(false);
        createReport.setDisable(false);
        errorMessage.setText("");
    }

    /**
     * Instructs the model to retrieve the requested data from the API.
     *
     * @param event the MouseEvent created when the no {@link Button} is clicked
     */
    @FXML
    public void doNotUseCache(MouseEvent event) {
        String selectedCrypto = cryptoList.getSelectionModel().getSelectedItem();
        final int AUD_ID = 2782;
        model.retrieveCryptoMetadata(Integer.parseInt(selectedCrypto.split(" -")[0]));
        yes.setDisable(true);
        no.setDisable(true);
        selectCrypto.setDisable(false);
        cryptoList.setDisable(false);
        createReport.setDisable(false);
        errorMessage.setText("");
    }

    private void askToUseCache() {
        selectCrypto.setDisable(true);
        cryptoList.setDisable(true);
        createReport.setDisable(true);
        errorMessage.setText("Cached data detected!\nWould you like to use it?");
        yes.setDisable(false);
        no.setDisable(false);
    }

    private void showLinkToReport() {
        if (null != model.getErrorMessage()) {
            errorMessage.setText(model.getErrorMessage());
            return;
        }
        if (null != model.getImgurLink()) {
            if (!this.errorMessage.getText().equals(model.getImgurLink())) {
                errorMessage.setText("Report: " + model.getImgurLink());
            }
        }
    }
}
