package exam.model.facade;

import exam.controller.ModelObserver;
import exam.model.cryptoapi.CryptoApi;
import exam.model.cryptoresponse.cryptometadata.CryptoMetadataResponse;
import exam.model.cryptoresponse.cryptometadata.models.cryptometadata.CryptoMetadata;
import exam.model.cryptoresponse.cryptoquote.CryptoQuoteResponse;
import exam.model.cryptoresponse.currencymap.CurrencyMapResponse;
import exam.model.database.Database;
import exam.model.imgurapi.ImgurApi;
import exam.model.imgurresponse.uploadresponse.ImgurUploadResponse;
import exam.model.jsonmapper.JsonMapper;
import exam.model.jsonmapper.JsonMapperImpl;
import exam.model.reportwriter.ReportWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Default implementation of the CryptoFacade interface.
 */
public class CryptoFacadeImpl implements CryptoFacade {

    private final CryptoApi cryptoApi;
    private final ReportWriter reportWriter;
    private final ImgurApi imgurApi;
    private final Database database;
    private final JsonMapper jsonMapper = new JsonMapperImpl();
    private final List<ModelObserver> observers = new ArrayList<>();
    private Map<Integer, String> cryptoMap;
    private Map<Integer, String> fiatMap;
    private CryptoMetadata cryptoMetadata;
    private Double conversion;
    private String imgurLink;
    private String errorMessage;

    /**
     * Default CryptoFacadeImpl constructor.
     *
     * @param cryptoApi    the cryptoAPI provider to be used by the model, not null
     * @param reportWriter the reportWriter provider to be used by the model, not null
     * @param imgurApi     the imgurAPI provider to be used by the model, not null
     * @param database     the database to be used for caching, not null
     */
    public CryptoFacadeImpl(CryptoApi cryptoApi, ReportWriter reportWriter, ImgurApi imgurApi, Database database) {
        if ((null == cryptoApi) || (null == reportWriter) || (null == imgurApi) || (null == database)) {
            throw new IllegalArgumentException();
        }

        this.cryptoApi = cryptoApi;
        this.reportWriter = reportWriter;
        this.imgurApi = imgurApi;
        this.database = database;
    }

    @Override
    public void retrieveCryptoMap() {
        String apiResponse = cryptoApi.getCryptoMap();
        CurrencyMapResponse cryptoMapResponse = jsonMapper.mapCurrencyMapResponse(apiResponse);
        this.cryptoMap = cryptoMapResponse.getCurrencyMap();
        this.errorMessage = cryptoMapResponse.getErrorMessage();
        notifyObservers();
    }

    @Override
    public void retrieveFiatMap() {
        String apiResponse = cryptoApi.getFiatMap();
        CurrencyMapResponse fiatMapResponse = jsonMapper.mapCurrencyMapResponse(apiResponse);
        this.fiatMap = fiatMapResponse.getCurrencyMap();
        this.errorMessage = fiatMapResponse.getErrorMessage();
        notifyObservers();
    }

    @Override
    public void retrieveCryptoMetadata(int id) {
        if (!(this.cryptoMap.containsKey(id))) {
            this.cryptoMetadata = null;
            this.errorMessage = "Invalid ID";
            notifyObservers();
            return;
        }
        String apiResponse = cryptoApi.getCryptoMetadata(id);
        CryptoMetadataResponse cryptoMetadataResponse = jsonMapper.mapCryptoMetadataResponse(apiResponse, id);
        this.cryptoMetadata = cryptoMetadataResponse.getCryptoMetadata();
        this.errorMessage = cryptoMetadataResponse.getErrorMessage();
        if (null != cryptoMetadataResponse.getCryptoMetadata()) {
            this.database.addCryptoMetadata(id, apiResponse);
        }
        notifyObservers();
    }

    @Override
    public void retrieveConversion(int fromId, int toId, double amount) {
        if (!((this.cryptoMap.containsKey(fromId) || this.fiatMap.containsKey(fromId))
                || (this.cryptoMap.containsKey(toId) || this.fiatMap.containsKey(toId)))) {
            this.conversion = null;
            this.errorMessage = "Invalid ID";
            notifyObservers();
            return;
        }
        if (amount < 0) {
            this.conversion = null;
            this.errorMessage = "Invalid amount";
            notifyObservers();
            return;
        }
        String apiResponse = cryptoApi.getCryptoQuote(fromId, toId);
        CryptoQuoteResponse cryptoQuoteResponse = jsonMapper.mapCryptoQuoteResponse(apiResponse, fromId, toId);
        if (null != cryptoQuoteResponse.getCryptoQuote()) {
            this.conversion = cryptoQuoteResponse.getCryptoQuote().getPrice() * amount;
        } else {
            this.conversion = null;
        }
        this.errorMessage = cryptoQuoteResponse.getErrorMessage();
        notifyObservers();
    }

    @Override
    public void sendReport() {
        if (null == this.cryptoMetadata) {
            this.imgurLink = null;
            this.errorMessage = "No cryptocurrency selected";
            notifyObservers();
            return;
        }
        String reportQrCodeAsBase64 = reportWriter.generateReportQrCodeAsBase64(this.cryptoMetadata);
        String apiResponse = imgurApi.uploadBase64Image(reportQrCodeAsBase64);
        ImgurUploadResponse imgurUploadResponse = jsonMapper.mapImgurUploadResponse(apiResponse);
        this.imgurLink = imgurUploadResponse.getLink();
        this.errorMessage = imgurUploadResponse.getErrorMessage();
        notifyObservers();
    }

    @Override
    public boolean checkMetadataCache(int id) {
        return null != database.getCryptoMetadata(id);
    }

    @Override
    public void retrieveMetadataFromCache(int id) {
        if (!(this.cryptoMap.containsKey(id))) {
            this.cryptoMetadata = null;
            this.errorMessage = "Invalid ID";
            notifyObservers();
            return;
        }
        String databaseResponse = database.getCryptoMetadata(id);
        CryptoMetadataResponse cryptoMetadataResponse = jsonMapper.mapCryptoMetadataResponse(databaseResponse, id);
        this.cryptoMetadata = cryptoMetadataResponse.getCryptoMetadata();
        this.errorMessage = cryptoMetadataResponse.getErrorMessage();
        notifyObservers();
    }

    @Override
    public Map<Integer, String> getCryptoMap() {
        return this.cryptoMap;
    }

    @Override
    public Map<Integer, String> getFiatMap() {
        return this.fiatMap;
    }

    @Override
    public CryptoMetadata getCryptoMetadata() {
        return this.cryptoMetadata;
    }

    @Override
    public Double getConversion() {
        return this.conversion;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Override
    public String getImgurLink() {
        return this.imgurLink;
    }

    @Override
    public void addObserver(ModelObserver observer) {
        this.observers.add(observer);
    }

    private void notifyObservers() {
        for (ModelObserver observer : this.observers) {
            observer.notifyObserver();
        }
    }

}
