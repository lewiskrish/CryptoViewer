package exam.model.facade;

import exam.model.cryptoapi.CryptoApi;
import exam.model.cryptoresponse.cryptometadata.models.cryptometadata.CryptoMetadata;
import exam.model.cryptoresponse.cryptoquote.models.CryptoQuote;
import exam.model.database.Database;
import exam.model.imgurapi.ImgurApi;
import exam.model.reportwriter.ReportWriter;

import org.junit.Test;

import javax.xml.crypto.Data;
import java.util.Map;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CryptoFacadeTest {

    // Test JSON data obtained from API documentation
    String fakeMap = "{\n" +
            "\n" +
            "    \"data\": [\n" +
            "        {\n" +
            "            \"id\": 1,\n" +
            "            \"rank\": 1,\n" +
            "            \"name\": \"Bitcoin\",\n" +
            "            \"symbol\": \"BTC\",\n" +
            "            \"slug\": \"bitcoin\",\n" +
            "            \"is_active\": 1,\n" +
            "            \"first_historical_data\": \"2013-04-28T18:47:21.000Z\",\n" +
            "            \"last_historical_data\": \"2020-05-05T20:44:01.000Z\",\n" +
            "            \"platform\": null\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 2781,\n" +
            "            \"rank\": 3,\n" +
            "            \"name\": \"Binance Coin\",\n" +
            "            \"symbol\": \"BNB\",\n" +
            "            \"slug\": \"binance-coin\",\n" +
            "            \"is_active\": 1,\n" +
            "            \"first_historical_data\": \"2017-07-25T04:30:05.000Z\",\n" +
            "            \"last_historical_data\": \"2020-05-05T20:44:02.000Z\",\n" +
            "            \"platform\": {\n" +
            "                \"id\": 1027,\n" +
            "                \"name\": \"Ethereum\",\n" +
            "                \"symbol\": \"ETH\",\n" +
            "                \"slug\": \"ethereum\",\n" +
            "                \"token_address\": \"0xB8c77482e45F1F44dE1745F52C74426C631bDD52\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 825,\n" +
            "            \"rank\": 5,\n" +
            "            \"name\": \"Tether\",\n" +
            "            \"symbol\": \"USDT\",\n" +
            "            \"slug\": \"tether\",\n" +
            "            \"is_active\": 1,\n" +
            "            \"first_historical_data\": \"2015-02-25T13:34:26.000Z\",\n" +
            "            \"last_historical_data\": \"2020-05-05T20:44:01.000Z\",\n" +
            "            \"platform\": {\n" +
            "                \"id\": 1027,\n" +
            "                \"name\": \"Ethereum\",\n" +
            "                \"symbol\": \"ETH\",\n" +
            "                \"slug\": \"ethereum\",\n" +
            "                \"token_address\": \"0xdac17f958d2ee523a2206206994597c13d831ec7\"\n" +
            "            }\n" +
            "        }\n" +
            "    ],\n" +
            "    \"status\": {\n" +
            "        \"timestamp\": \"2018-06-02T22:51:28.209Z\",\n" +
            "        \"error_code\": 0,\n" +
            "        \"error_message\": null,\n" +
            "        \"elapsed\": 10,\n" +
            "        \"credit_count\": 1\n" +
            "    }\n" +
            "\n" +
            "}";

    String fakeMetadata = "{\n" +
            "\n" +
            "    \"data\": {\n" +
            "        \"1\": {\n" +
            "            \"urls\": {\n" +
            "                \"website\": [\n" +
            "                    \"https://www.ethereum.org/\"\n" +
            "                ],\n" +
            "                \"technical_doc\": [\n" +
            "                    \"https://github.com/ethereum/wiki/wiki/White-Paper\"\n" +
            "                ],\n" +
            "                \"twitter\": [\n" +
            "                    \"https://twitter.com/ethereum\"\n" +
            "                ],\n" +
            "                \"reddit\": [\n" +
            "                    \"https://reddit.com/r/ethereum\"\n" +
            "                ],\n" +
            "                \"message_board\": [\n" +
            "                    \"https://forum.ethereum.org/\"\n" +
            "                ],\n" +
            "                \"announcement\": [\n" +
            "                    \"https://bitcointalk.org/index.php?topic=428589.0\"\n" +
            "                ],\n" +
            "                \"chat\": [\n" +
            "                    \"https://gitter.im/orgs/ethereum/rooms\"\n" +
            "                ],\n" +
            "                \"explorer\": [\n" +
            "                    \"https://blockchain.coinmarketcap.com/chain/ethereum\",\n" +
            "                    \"https://etherscan.io/\",\n" +
            "                    \"https://ethplorer.io/\"\n" +
            "                ],\n" +
            "                \"source_code\": [\n" +
            "                    \"https://github.com/ethereum\"\n" +
            "                ]\n" +
            "            },\n" +
            "            \"logo\": \"https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png\",\n" +
            "            \"id\": 1,\n" +
            "            \"name\": \"Ethereum\",\n" +
            "            \"symbol\": \"ETH\",\n" +
            "            \"slug\": \"ethereum\",\n" +
            "            \"description\": \"fake description\",\n" +
            "            \"notice\": null,\n" +
            "            \"date_added\": \"2015-08-07T00:00:00.000Z\",\n" +
            "            \"tags\": [\n" +
            "                \"mineable\"\n" +
            "            ],\n" +
            "            \"platform\": null,\n" +
            "            \"category\": \"coin\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"status\": {\n" +
            "        \"timestamp\": \"2021-05-20T05:54:37.580Z\",\n" +
            "        \"error_code\": 0,\n" +
            "        \"error_message\": null,\n" +
            "        \"elapsed\": 10,\n" +
            "        \"credit_count\": 1\n" +
            "    }\n" +
            "\n" +
            "}";

    String fakeQuote = "{\n" +
            "\n" +
            "    \"data\": {\n" +
            "        \"1\": {\n" +
            "            \"id\": 1,\n" +
            "            \"name\": \"Bitcoin\",\n" +
            "            \"symbol\": \"BTC\",\n" +
            "            \"slug\": \"bitcoin\",\n" +
            "            \"is_active\": 1,\n" +
            "            \"is_fiat\": 0,\n" +
            "            \"circulating_supply\": 17199862,\n" +
            "            \"total_supply\": 17199862,\n" +
            "            \"max_supply\": 21000000,\n" +
            "            \"date_added\": \"2013-04-28T00:00:00.000Z\",\n" +
            "            \"num_market_pairs\": 331,\n" +
            "            \"cmc_rank\": 1,\n" +
            "            \"last_updated\": \"2018-08-09T21:56:28.000Z\",\n" +
            "            \"tags\": [\n" +
            "                \"mineable\"\n" +
            "            ],\n" +
            "            \"platform\": null,\n" +
            "            \"quote\": {\n" +
            "                \"2781\": {\n" +
            "                    \"price\": 6602.60701122,\n" +
            "                    \"volume_24h\": 4314444687.5194,\n" +
            "                    \"percent_change_1h\": 0.988615,\n" +
            "                    \"percent_change_24h\": 4.37185,\n" +
            "                    \"percent_change_7d\": -12.1352,\n" +
            "                    \"percent_change_30d\": -12.1352,\n" +
            "                    \"market_cap\": 113563929433.21645,\n" +
            "                    \"last_updated\": \"2018-08-09T21:56:28.000Z\"\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "    },\n" +
            "    \"status\": {\n" +
            "        \"timestamp\": \"2021-06-01T04:54:46.232Z\",\n" +
            "        \"error_code\": 0,\n" +
            "        \"error_message\": null,\n" +
            "        \"elapsed\": 10,\n" +
            "        \"credit_count\": 1\n" +
            "    }\n" +
            "\n" +
            "}";

    String fakeReportResponse = "{\n" +
            "  \"data\": {\n" +
            "    \"id\": \"orunSTu\",\n" +
            "    \"title\": null,\n" +
            "    \"description\": null,\n" +
            "    \"datetime\": 1495556889,\n" +
            "    \"type\": \"image/gif\",\n" +
            "    \"animated\": false,\n" +
            "    \"width\": 1,\n" +
            "    \"height\": 1,\n" +
            "    \"size\": 42,\n" +
            "    \"views\": 0,\n" +
            "    \"bandwidth\": 0,\n" +
            "    \"vote\": null,\n" +
            "    \"favorite\": false,\n" +
            "    \"nsfw\": null,\n" +
            "    \"section\": null,\n" +
            "    \"account_url\": null,\n" +
            "    \"account_id\": 0,\n" +
            "    \"is_ad\": false,\n" +
            "    \"in_most_viral\": false,\n" +
            "    \"tags\": [],\n" +
            "    \"ad_type\": 0,\n" +
            "    \"ad_url\": \"\",\n" +
            "    \"in_gallery\": false,\n" +
            "    \"deletehash\": \"x70po4w7BVvSUzZ\",\n" +
            "    \"name\": \"\",\n" +
            "    \"link\": \"http://i.imgur.com/orunSTu.gif\"\n" +
            "  },\n" +
            "  \"success\": true,\n" +
            "  \"status\": 200\n" +
            "}";

    @Test
    public void getCryptoMap() {
        Map<Integer, String> expected = new HashMap<>();
        expected.put(1, "Bitcoin");
        expected.put(2781, "Binance Coin");
        expected.put(825, "Tether");
        CryptoApi cryptoApiMock = mock(CryptoApi.class);
        when(cryptoApiMock.getCryptoMap()).thenReturn(fakeMap);

        CryptoFacade cryptoFacade = new CryptoFacadeImpl(cryptoApiMock, mock(ReportWriter.class), mock(ImgurApi.class), mock(Database.class));
        cryptoFacade.retrieveCryptoMap();
        assertEquals(expected, cryptoFacade.getCryptoMap());
        assertNull(cryptoFacade.getErrorMessage());
    }

    @Test
    public void getCryptoMapError() {
        String expected = "Connection to Coinmarketcap has failed!";
        String fakeStatus = "{\"status\": {\"error_message\": \"Connection to Coinmarketcap has failed!\"}}";
        CryptoApi cryptoApiMock = mock(CryptoApi.class);
        when(cryptoApiMock.getCryptoMap()).thenReturn(fakeStatus);

        CryptoFacade cryptoFacade = new CryptoFacadeImpl(cryptoApiMock, mock(ReportWriter.class), mock(ImgurApi.class), mock(Database.class));
        cryptoFacade.retrieveCryptoMap();
        assertEquals(expected, cryptoFacade.getErrorMessage());
        assertNull(cryptoFacade.getCryptoMap());
    }

    @Test
    public void getFiatMap() {
        Map<Integer, String> expected = new HashMap<>();
        expected.put(1, "Bitcoin");
        expected.put(2781, "Binance Coin");
        expected.put(825, "Tether");
        CryptoApi cryptoApiMock = mock(CryptoApi.class);
        when(cryptoApiMock.getFiatMap()).thenReturn(fakeMap);

        CryptoFacade cryptoFacade = new CryptoFacadeImpl(cryptoApiMock, mock(ReportWriter.class), mock(ImgurApi.class), mock(Database.class));
        cryptoFacade.retrieveFiatMap();
        assertEquals(expected, cryptoFacade.getFiatMap());
        assertNull(cryptoFacade.getErrorMessage());
    }

    @Test
    public void getFiatMapError() {
        String expected = "Connection to Coinmarketcap has failed!";
        String fakeStatus = "{\"status\": {\"error_message\": \"Connection to Coinmarketcap has failed!\"}}";
        CryptoApi cryptoApiMock = mock(CryptoApi.class);
        when(cryptoApiMock.getFiatMap()).thenReturn(fakeStatus);

        CryptoFacade cryptoFacade = new CryptoFacadeImpl(cryptoApiMock, mock(ReportWriter.class), mock(ImgurApi.class), mock(Database.class));
        cryptoFacade.retrieveFiatMap();
        assertEquals(expected, cryptoFacade.getErrorMessage());
        assertNull(cryptoFacade.getFiatMap());
    }

    @Test
    public void getCryptoMetadata() {
        CryptoApi cryptoApiMock = mock(CryptoApi.class);
        when(cryptoApiMock.getCryptoMap()).thenReturn(fakeMap);
        when(cryptoApiMock.getCryptoMetadata(anyInt())).thenReturn(fakeMetadata);

        CryptoFacade cryptoFacade = new CryptoFacadeImpl(cryptoApiMock, mock(ReportWriter.class), mock(ImgurApi.class), mock(Database.class));
        cryptoFacade.retrieveCryptoMap();
        cryptoFacade.retrieveCryptoMetadata(1);
        CryptoMetadata actual = cryptoFacade.getCryptoMetadata();

        assertNotNull(actual);
        assertEquals("coin", actual.getCategory());
        assertEquals("Ethereum", actual.getName());
        assertNull(actual.getPlatform());
        assertTrue(actual.getUrls().getExplorers().contains("https://blockchain.coinmarketcap.com/chain/ethereum"));
    }

    @Test
    public void getCryptoMetadataError() {
        String expected = "Connection to Coinmarketcap has failed!";
        String fakeStatus = "{\"status\": {\"error_message\": \"Connection to Coinmarketcap has failed!\"}}";
        CryptoApi cryptoApiMock = mock(CryptoApi.class);
        when(cryptoApiMock.getCryptoMetadata(anyInt())).thenReturn(fakeStatus);
        when(cryptoApiMock.getCryptoMap()).thenReturn(fakeMap);

        CryptoFacade cryptoFacade = new CryptoFacadeImpl(cryptoApiMock, mock(ReportWriter.class), mock(ImgurApi.class), mock(Database.class));
        cryptoFacade.retrieveCryptoMap();
        cryptoFacade.retrieveCryptoMetadata(1);
        assertEquals(expected, cryptoFacade.getErrorMessage());
        assertNull(cryptoFacade.getCryptoMetadata());
    }

    @Test
    public void getConversion() {
        CryptoApi cryptoApiMock = mock(CryptoApi.class);
        when(cryptoApiMock.getCryptoQuote(anyInt(), anyInt())).thenReturn(fakeQuote);
        when(cryptoApiMock.getCryptoMap()).thenReturn(fakeMap);

        CryptoFacade cryptoFacade = new CryptoFacadeImpl(cryptoApiMock, mock(ReportWriter.class), mock(ImgurApi.class), mock(Database.class));
        cryptoFacade.retrieveCryptoMap();
        cryptoFacade.retrieveConversion(1, 2781, 123);
        Double actual = cryptoFacade.getConversion();
        assertEquals(6602.60701122 * 123, actual, 0.0);
    }

    @Test
    public void getConversionError() {
        String expected = "Connection to Coinmarketcap has failed!";
        String fakeStatus = "{\"status\": {\"error_message\": \"Connection to Coinmarketcap has failed!\"}}";
        CryptoApi cryptoApiMock = mock(CryptoApi.class);
        when(cryptoApiMock.getCryptoQuote(anyInt(), anyInt())).thenReturn(fakeStatus);
        when(cryptoApiMock.getCryptoMap()).thenReturn(fakeMap);

        CryptoFacade cryptoFacade = new CryptoFacadeImpl(cryptoApiMock, mock(ReportWriter.class), mock(ImgurApi.class), mock(Database.class));
        cryptoFacade.retrieveCryptoMap();
        cryptoFacade.retrieveConversion(1, 2781, 123);
        assertEquals(expected, cryptoFacade.getErrorMessage());
    }

    @Test
    public void sendReport() {
        CryptoApi cryptoApiMock = mock(CryptoApi.class);
        when(cryptoApiMock.getCryptoMap()).thenReturn(fakeMap);
        when(cryptoApiMock.getCryptoMetadata(anyInt())).thenReturn(fakeMetadata);
        when(cryptoApiMock.getCryptoQuote(anyInt(), anyInt())).thenReturn(fakeQuote);

        ImgurApi imgurApiMock = mock(ImgurApi.class);
        ReportWriter reportWriterMock = mock(ReportWriter.class);

        CryptoFacade cryptoFacade = new CryptoFacadeImpl(cryptoApiMock, reportWriterMock, imgurApiMock, mock(Database.class));
        cryptoFacade.retrieveCryptoMap();
        cryptoFacade.retrieveCryptoMetadata(1);
        CryptoMetadata cryptoMetadata = cryptoFacade.getCryptoMetadata();

        when(reportWriterMock.generateReportQrCodeAsBase64(cryptoMetadata)).thenReturn("somebase64string");
        when(imgurApiMock.uploadBase64Image("somebase64string")).thenReturn(fakeReportResponse);

        cryptoFacade.sendReport();

        assertNull(cryptoFacade.getErrorMessage());
        assertEquals("http://i.imgur.com/orunSTu.gif", cryptoFacade.getImgurLink());
    }

    @Test
    public void sendReportError() {
        CryptoApi cryptoApiMock = mock(CryptoApi.class);
        when(cryptoApiMock.getCryptoMap()).thenReturn(fakeMap);
        when(cryptoApiMock.getCryptoMetadata(anyInt())).thenReturn(fakeMetadata);
        when(cryptoApiMock.getCryptoQuote(anyInt(), anyInt())).thenReturn(fakeQuote);

        ImgurApi imgurApiMock = mock(ImgurApi.class);
        ReportWriter reportWriterMock = mock(ReportWriter.class);

        CryptoFacade cryptoFacade = new CryptoFacadeImpl(cryptoApiMock, reportWriterMock, imgurApiMock, mock(Database.class));
        cryptoFacade.retrieveCryptoMap();
        cryptoFacade.retrieveCryptoMetadata(1);
        CryptoMetadata cryptoMetadata = cryptoFacade.getCryptoMetadata();

        String fakeResponse = "{\"success\": false}";

        when(reportWriterMock.generateReportQrCodeAsBase64(cryptoMetadata)).thenReturn("somebase64string");
        when(imgurApiMock.uploadBase64Image("somebase64string")).thenReturn(fakeResponse);

        cryptoFacade.sendReport();

        assertEquals("Upload failed!", cryptoFacade.getErrorMessage());
        assertNull(cryptoFacade.getImgurLink());
    }

    @Test
    public void checkMetadataCachePresent() {
        CryptoApi cryptoApiMock = mock(CryptoApi.class);
        when(cryptoApiMock.getCryptoMap()).thenReturn(fakeMap);

        Database databaseMock = mock(Database.class);
        when(databaseMock.getCryptoMetadata(1)).thenReturn("some non-null data");

        CryptoFacade cryptoFacade = new CryptoFacadeImpl(cryptoApiMock, mock(ReportWriter.class), mock(ImgurApi.class), databaseMock);
        cryptoFacade.retrieveCryptoMap();
        assertTrue(cryptoFacade.checkMetadataCache(1));
    }

    @Test
    public void checkMetadataCacheNotPresent() {
        CryptoApi cryptoApiMock = mock(CryptoApi.class);
        when(cryptoApiMock.getCryptoMap()).thenReturn(fakeMap);

        Database databaseMock = mock(Database.class);
        when(databaseMock.getCryptoMetadata(1)).thenReturn(null);

        CryptoFacade cryptoFacade = new CryptoFacadeImpl(cryptoApiMock, mock(ReportWriter.class), mock(ImgurApi.class), databaseMock);
        cryptoFacade.retrieveCryptoMap();
        assertFalse(cryptoFacade.checkMetadataCache(1));
    }

    @Test
    public void retrieveMetadataFromCache() {
        CryptoApi cryptoApiMock = mock(CryptoApi.class);
        when(cryptoApiMock.getCryptoMap()).thenReturn(fakeMap);

        Database databaseMock = mock(Database.class);
        when(databaseMock.getCryptoMetadata(1)).thenReturn(fakeMetadata);

        CryptoFacade cryptoFacade = new CryptoFacadeImpl(cryptoApiMock, mock(ReportWriter.class), mock(ImgurApi.class), databaseMock);
        cryptoFacade.retrieveCryptoMap();
        cryptoFacade.retrieveMetadataFromCache(1);
        CryptoMetadata actual = cryptoFacade.getCryptoMetadata();

        assertNotNull(actual);
        assertEquals("coin", actual.getCategory());
        assertEquals("Ethereum", actual.getName());
        assertNull(actual.getPlatform());
        assertTrue(actual.getUrls().getExplorers().contains("https://blockchain.coinmarketcap.com/chain/ethereum"));
    }

    @Test
    public void metadataCacheIsUpdated() {
        CryptoApi cryptoApiMock = mock(CryptoApi.class);
        when(cryptoApiMock.getCryptoMap()).thenReturn(fakeMap);
        when(cryptoApiMock.getCryptoMetadata(1)).thenReturn(fakeMetadata);

        Database databaseMock = mock(Database.class);

        CryptoFacade cryptoFacade = new CryptoFacadeImpl(cryptoApiMock, mock(ReportWriter.class), mock(ImgurApi.class), databaseMock);
        cryptoFacade.retrieveCryptoMap();
        cryptoFacade.retrieveCryptoMetadata(1);
        verify(databaseMock).addCryptoMetadata(1, fakeMetadata);
    }

}
