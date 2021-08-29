package exam.model.cryptoapi;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Default implementation of the CryptoApi interface.
 */
public class CryptoApiImpl implements CryptoApi {

    private final String apiKey;
    private final HttpClient client = HttpClient.newHttpClient();

    /**
     * Default CryptoApiIml constructor.
     *
     * @param key a CoinMarketCap API key, not null
     */
    public CryptoApiImpl(String key) {
        if (null == key) {
            throw new NullPointerException("key cannot be null");
        }
        apiKey = key;
    }

    //  Sources used for creating http requests:
    //      https://www.baeldung.com/java-9-http-client
    //      https://openjdk.java.net/groups/net/httpclient/recipes.html

    @Override
    public String getCryptoMap() {
        HttpRequest getRequest = null;
        HttpResponse<String> response;

        try {
            getRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://pro-api.coinmarketcap.com/v1/cryptocurrency/map"))
                    .header("Accept", "application/json")
                    .header("X-CMC_PRO_API_KEY", apiKey)
                    .timeout(Duration.ofSeconds(10))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return "{\"status\": {\"error_message\": \"Connection to Coinmarketcap has failed!\"}}";
        }

        if (null == response) {
            return "{\"status\": {\"error_message\": \"Connection to Coinmarketcap has failed!\"}}";
        }

        return response.body();
    }

    @Override
    public String getFiatMap() {
        HttpRequest getRequest = null;
        HttpResponse<String> response;

        try {
            getRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://pro-api.coinmarketcap.com/v1/fiat/map"))
                    .header("Accept", "application/json")
                    .header("X-CMC_PRO_API_KEY", apiKey)
                    .timeout(Duration.ofSeconds(10))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return "{\"status\": {\"error_message\": \"Connection to Coinmarketcap has failed!\"}}";
        }

        if (null == response || response.statusCode() >= 500) {
            return "{\"status\": {\"error_message\": \"Connection to Coinmarketcap has failed!\"}}";
        }

        return response.body();
    }

    @Override
    public String getCryptoMetadata(int id) {
        HttpRequest getRequest = null;
        HttpResponse<String> response;

        try {
            getRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://pro-api.coinmarketcap.com/v1/cryptocurrency/info?id=" + id))
                    .header("Accept", "application/json")
                    .header("X-CMC_PRO_API_KEY", apiKey)
                    .timeout(Duration.ofSeconds(10))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return "{\"status\": {\"error_message\": \"Connection to Coinmarketcap has failed!\"}}";
        }

        if (null == response || response.statusCode() >= 500) {
            return "{\"status\": {\"error_message\": \"Connection to Coinmarketcap has failed!\"}}";
        }

        return response.body();
    }

    @Override
    public String getCryptoQuote(int id, int convertId) {
        HttpRequest getRequest = null;
        HttpResponse<String> response;

        try {
            getRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?id=" + id + "&convert_id=" + convertId))
                    .header("Accept", "application/json")
                    .header("X-CMC_PRO_API_KEY", apiKey)
                    .timeout(Duration.ofSeconds(10))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return "{\"status\": {\"error_message\": \"Connection to Coinmarketcap has failed!\"}}";
        }

        if (null == response || response.statusCode() >= 500) {
            return "{\"status\": {\"error_message\": \"Connection to Coinmarketcap has failed!\"}}";
        }

        return response.body();
    }
}
