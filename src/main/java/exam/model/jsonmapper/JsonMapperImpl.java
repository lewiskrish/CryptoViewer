package exam.model.jsonmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exam.model.cryptoresponse.cryptometadata.CryptoMetadataResponse;
import exam.model.cryptoresponse.cryptometadata.CryptoMetadataResponseImpl;
import exam.model.cryptoresponse.cryptometadata.models.cryptometadata.CryptoMetadata;
import exam.model.cryptoresponse.cryptometadata.models.cryptometadata.CryptoMetadataImpl;
import exam.model.cryptoresponse.cryptoquote.CryptoQuoteResponse;
import exam.model.cryptoresponse.cryptoquote.CryptoQuoteResponseImpl;
import exam.model.cryptoresponse.cryptoquote.models.CryptoQuote;
import exam.model.cryptoresponse.cryptoquote.models.CryptoQuoteImpl;
import exam.model.cryptoresponse.currencymap.CurrencyMapResponse;
import exam.model.cryptoresponse.currencymap.CurrencyMapResponseImpl;
import exam.model.imgurresponse.uploadresponse.ImgurUploadResponse;
import exam.model.imgurresponse.uploadresponse.ImgurUploadResponseImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Defeault implementation of the JsonMapper interface.
 */
public class JsonMapperImpl implements JsonMapper {

    // https://stackoverflow.com/a/32235935
    private static long longValue(Object value) {
        return (value instanceof Number ? ((Number) value).longValue() : -1);
    }

    // https://stackoverflow.com/a/32235935
    private static double doubleValue(Object value) {
        return (value instanceof Number ? ((Number) value).doubleValue() : -1.0);
    }

    @Override
    public CurrencyMapResponse mapCurrencyMapResponse(String jsonData) {
        Map<Integer, String> currencyMap = new HashMap<>();
        JSONParser parser = new JSONParser();

        try {
            JSONObject parsedJson = (JSONObject) parser.parse(jsonData);
            JSONObject status = (JSONObject) parsedJson.get("status");
            String errorMessage = (String) status.get("error_message");
            // An error has occurred
            if (null != errorMessage) {
                return new CurrencyMapResponseImpl(null, errorMessage);
            }
            JSONArray data = (JSONArray) parsedJson.get("data");
            for (Object o : data) {
                JSONObject j = (JSONObject) o;
                Integer id = (int) (long) j.get("id");
                String name = (String) j.get("name");
                currencyMap.put(id, name);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Sorting map by values adapted from https://stackoverflow.com/a/19671853
        Map<Integer, String> sortedMap =
                currencyMap.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(String.CASE_INSENSITIVE_ORDER))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));
        return new CurrencyMapResponseImpl(sortedMap, null);
    }

    @Override
    public CryptoMetadataResponse mapCryptoMetadataResponse(String jsonData, int id) {
        CryptoMetadata cryptoMetadata = null;
        JSONParser parser = new JSONParser();

        try {
            JSONObject parsedJson = (JSONObject) parser.parse(jsonData);
            JSONObject status = (JSONObject) parsedJson.get("status");
            String errorMessage = (String) status.get("error_message");
            // An error has occurred
            if (null != errorMessage) {
                return new CryptoMetadataResponseImpl(null, errorMessage);
            }

            JSONObject data = (JSONObject) parsedJson.get("data");
            JSONObject cryptoInfo = (JSONObject) data.get(String.valueOf(id));
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                cryptoMetadata = objectMapper.readValue(cryptoInfo.toString(), CryptoMetadataImpl.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new CryptoMetadataResponseImpl(cryptoMetadata, null);
    }

    @Override
    public CryptoQuoteResponse mapCryptoQuoteResponse(String jsonData, int id, int convertId) {
        CryptoQuote cryptoQuote = new CryptoQuoteImpl();
        JSONParser parser = new JSONParser();

        try {
            JSONObject parsedJson = (JSONObject) parser.parse(jsonData);
            JSONObject status = (JSONObject) parsedJson.get("status");
            String errorMessage = (String) status.get("error_message");
            // An error has occurred
            if (null != errorMessage) {
                return new CryptoQuoteResponseImpl(null, errorMessage);
            }

            JSONObject data = (JSONObject) parsedJson.get("data");
            JSONObject cryptoData = (JSONObject) data.get(String.valueOf(id));
            JSONObject quoteData = (JSONObject) cryptoData.get("quote");
            JSONObject quote = (JSONObject) quoteData.get(String.valueOf(convertId));

            // https://stackoverflow.com/a/32235935
            cryptoQuote.setPrice(doubleValue(quote.get("price")));
            cryptoQuote.setVolume24H(doubleValue(quote.get("volume_24h")));
            cryptoQuote.setPercentChange1H(doubleValue(quote.get("percent_change_1h")));
            cryptoQuote.setPercentChange24H(doubleValue(quote.get("percent_change_24h")));
            cryptoQuote.setPercentChange7D(doubleValue(quote.get("percent_change_7d")));
            cryptoQuote.setPercentChange30D(doubleValue(quote.get("percent_change_30d")));
            cryptoQuote.setMarketCap(doubleValue(quote.get("market_cap")));
            cryptoQuote.setLastUpdated((String) quote.get("last_updated"));
            cryptoQuote.setCirculatingSupply(doubleValue(cryptoData.get("circulating_supply")));
            cryptoQuote.setTotalSupply(doubleValue(cryptoData.get("total_supply")));
            cryptoQuote.setMaxSupply(doubleValue(cryptoData.get("max_supply")));
            cryptoQuote.setNumMarketPairs(longValue(cryptoData.get("num_market_pairs")));
            cryptoQuote.setCmcRank(longValue(cryptoData.get("cmc_rank")));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new CryptoQuoteResponseImpl(cryptoQuote, null);
    }

    @Override
    public ImgurUploadResponse mapImgurUploadResponse(String jsonData) {
        String link = null;
        JSONParser parser = new JSONParser();

        try {
            JSONObject parsedJson = (JSONObject) parser.parse(jsonData);
            boolean status = (boolean) parsedJson.get("success");
            // An error has occurred
            if (!status) {
                return new ImgurUploadResponseImpl(null, "Upload failed!");
            }

            JSONObject data = (JSONObject) parsedJson.get("data");
            link = (String) data.get("link");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new ImgurUploadResponseImpl(link, null);
    }

}
