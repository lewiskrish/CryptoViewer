package exam;

import exam.controller.ConversionController;
import exam.controller.CryptoListController;
import exam.controller.InfoPanelController;
import exam.model.cryptoapi.CryptoApi;
import exam.model.cryptoapi.CryptoApiDummy;
import exam.model.cryptoapi.CryptoApiImpl;
import exam.model.database.Database;
import exam.model.database.DatabaseImpl;
import exam.model.facade.CryptoFacade;
import exam.model.facade.CryptoFacadeImpl;
import exam.model.imgurapi.ImgurApi;
import exam.model.imgurapi.ImgurApiDummy;
import exam.model.imgurapi.ImgurApiImpl;
import exam.model.reportwriter.ReportWriterImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main driver class to initialize and start the JavaFX application.
 */
public class CryptoApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        if(getParameters().getRaw().size() != 2) {
            System.err.println("Forgot your command line arguments?");
            System.exit(-4);
        }

        String inputApi = getParameters().getRaw().get(0);
        String outputApi = getParameters().getRaw().get(1);

        BorderPane root = new BorderPane();
        CryptoListController cryptoListController = null;
        InfoPanelController infoPanelController = null;
        ConversionController conversionController = null;

        // Loading views and controllers
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cryptoList.fxml"));
            root.setLeft(loader.load());
            cryptoListController = loader.getController();

            loader = new FXMLLoader(getClass().getResource("/infoPanel.fxml"));
            root.setCenter(loader.load());
            infoPanelController = loader.getController();

            loader = new FXMLLoader(getClass().getResource("/convert.fxml"));
            root.setBottom(loader.load());
            conversionController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not load FXML files");
            System.exit(-3);
        }

        // Checking whether to use online of offline APIs
        CryptoApi cryptoApi = null;
        if (inputApi.equals("offline")) {
            cryptoApi = new CryptoApiDummy();
        } else if (inputApi.equals("online")) {
            cryptoApi = new CryptoApiImpl(KeyParser.getInputKey());
        } else {
            System.err.println("Invalid first argument");
            System.exit(-1);
        }

        ImgurApi imgurApi = null;
        if (outputApi.equals("offline")) {
            imgurApi = new ImgurApiDummy();
        } else if (outputApi.equals("online")) {
            imgurApi = new ImgurApiImpl(KeyParser.getOutputKey());
        } else {
            System.err.println("Invalid second argument");
            System.exit(-2);
        }

        // Initializes the database used for caching
        Database database = new DatabaseImpl("database.db");
        database.init();

        // Creating the model and adding observers
        CryptoFacade cryptoFacade = new CryptoFacadeImpl(cryptoApi, new ReportWriterImpl(), imgurApi, database);
        cryptoFacade.addObserver(cryptoListController);
        cryptoFacade.addObserver(infoPanelController);
        cryptoFacade.addObserver(conversionController);

        // https://stackoverflow.com/a/26611622
        // Using a singleThreadExecutor to avoid any race conditions caused by using a threadpool/multiple threads
        ExecutorService modelTaskExecutor = Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r);
            t.setDaemon(true); // to stop the thread from blocking the app from closing
            return t;
        });

        // Setting up the application window
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cryptocurrency Info");
        primaryStage.getIcons().add(new Image("/ethereum.png"));
        primaryStage.show();

        cryptoListController.setModel(cryptoFacade);
        cryptoListController.setModelTaskExecutor(modelTaskExecutor);

        infoPanelController.setModel(cryptoFacade);
        infoPanelController.setModelTaskExecutor(modelTaskExecutor);

        conversionController.setModel(cryptoFacade);
        conversionController.setModelTaskExecutor(modelTaskExecutor);

        cryptoListController.populate();
        conversionController.populate();
    }
}
