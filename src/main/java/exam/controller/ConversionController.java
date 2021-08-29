package exam.controller;

import exam.model.cryptoresponse.cryptometadata.models.cryptometadata.CryptoMetadata;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.Map;

/**
 * Controller to manage the view associated with converting amounts between cryptocurrencies.
 */
public class ConversionController extends AbstractController implements ModelObserver {

    @FXML
    private ComboBox<String> dropdown;

    @FXML
    private TextField amount;

    @FXML
    private TextArea conversion;

    @FXML
    private Button convertTo;

    @FXML
    private Button convertFrom;

    @FXML
    private Text toName;

    @FXML
    private Text fromName;

    @Override
    public void notifyObserver() {
        Platform.runLater(() -> {
            updateNames();
            updateConversion();
        });
    }

    /**
     * Populates a ComboBox of cryptocurrencies with fiat and cryptocurrencies in the form "%CoinMarketCapID% - %name%".
     */
    public void populate() {
        if (null != model.getErrorMessage()) {
            conversion.setText(model.getErrorMessage());
            return;
        }

        modelTaskExecutor.execute(() -> {
            if (null == model.getCryptoMap()) {
                model.retrieveCryptoMap();
            }
            if (null == model.getFiatMap()) {
                model.retrieveFiatMap();
            }
            Platform.runLater(() -> {
                Map<Integer, String> fiatMap = model.getFiatMap();
                // Adapted from https://www.geeksforgeeks.org/iterate-map-java/
                for (Map.Entry<Integer, String> entry : fiatMap.entrySet()) {
                    dropdown.getItems().add(entry.getKey() + " - " + entry.getValue());
                }

                Map<Integer, String> cryptoMap = model.getCryptoMap();
                // Adapted from https://www.geeksforgeeks.org/iterate-map-java/
                for (Map.Entry<Integer, String> entry : cryptoMap.entrySet()) {
                    dropdown.getItems().add(entry.getKey() + " - " + entry.getValue());
                }
            });
        });
    }

    private void updateNames() {
        if (null != model.getErrorMessage()) {
            conversion.setText(model.getErrorMessage());
            return;
        }
        if (null == model.getCryptoMetadata()) {
            conversion.setText("");
            return;
        }

        CryptoMetadata cryptoMetadata = model.getCryptoMetadata();
        this.toName.setText(cryptoMetadata.getSymbol());
        this.fromName.setText(cryptoMetadata.getSymbol());
    }

    private void updateConversion() {
        if (null != model.getErrorMessage()) {
            conversion.setText(model.getErrorMessage());
            return;
        }
        if (null == model.getConversion()) {
            conversion.setText("");
            return;
        }
        this.conversion.setText("= " + String.format("%f", model.getConversion()));
    }

    /**
     * Instructs the model to perform a conversion from its current base currency.
     *
     * @param event the MouseEvent created when the convertFrom {@link Button} is clicked
     */
    @FXML
    public void convertFrom(MouseEvent event) {
        String selectedCrypto = dropdown.getValue();
        if (null == selectedCrypto) {
            conversion.setText("No currency selected!");
            return;
        }
        if (null == model.getCryptoMetadata()) {
            conversion.setText("No base crypto selected!");
            return;
        }
        if (null == amount) {
            conversion.setText("No amount given!");
            return;
        }

        double amountDouble;
        try {
            amountDouble = Double.parseDouble(amount.getText());
        } catch (NumberFormatException e) {
            conversion.setText("Not a valid number!");
            return;
        }
        if (amountDouble < 0) {
            conversion.setText("Amount cannot be less than zero!");
            return;
        }
        int id = Integer.parseInt(selectedCrypto.split(" -")[0]);

        modelTaskExecutor.execute(() -> model.retrieveConversion(model.getCryptoMetadata().getId(), id, amountDouble));
    }

    /**
     * Instructs the model to perform a conversion to its current base currency.
     *
     * @param event the MouseEvent created when the convertTo {@link Button} is clicked
     */
    @FXML
    public void convertTo(MouseEvent event) {
        String selectedCrypto = dropdown.getValue();
        if (null == selectedCrypto) {
            conversion.setText("No currency selected!");
            return;
        }
        if (null == model.getCryptoMetadata()) {
            conversion.setText("No base crypto selected!");
            return;
        }
        if (null == amount) {
            conversion.setText("No amount given!");
            return;
        }

        double amountDouble;
        try {
            amountDouble = Double.parseDouble(amount.getText());
        } catch (NumberFormatException e) {
            conversion.setText("Not a valid number!");
            return;
        }
        if (amountDouble < 0) {
            conversion.setText("Amount cannot be less than zero!");
            return;
        }
        int id = Integer.parseInt(selectedCrypto.split(" -")[0]);

        modelTaskExecutor.execute(() -> model.retrieveConversion(id, model.getCryptoMetadata().getId(), amountDouble));
    }
}
