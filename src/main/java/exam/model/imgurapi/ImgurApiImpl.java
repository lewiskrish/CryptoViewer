package exam.model.imgurapi;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class ImgurApiImpl implements ImgurApi {

    private final String apiKey;
    private final HttpClient client = HttpClient.newHttpClient();

    /**
     * Default ImgurAPIImpl constructor.
     *
     * @param key an Imgur API Client ID, not null
     */
    public ImgurApiImpl(String key) {
        if (null == key) {
            throw new NullPointerException("key cannot be null");
        }
        this.apiKey = key;
    }

    @Override
    public String uploadBase64Image(String image) {
        // https://www.baeldung.com/java-url-encoding-decoding
        String encodedImageString = URLEncoder.encode(image, StandardCharsets.UTF_8);

        HttpRequest postRequest = null;
        HttpResponse<String> response;
        try {
            postRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://api.imgur.com/3/image"))
                    .header("Authorization", "Client-ID " + apiKey)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString("image=" + encodedImageString))
                    .timeout(Duration.ofSeconds(10))
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            response = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return "{\"success\": false}";
        }

        if (null == response || response.statusCode() >= 500) {
            return "{\"success\": false}";
        }
        return response.body();
    }
}
