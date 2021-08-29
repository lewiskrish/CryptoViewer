package exam.model.cryptoapi;

/**
 * Dummy implementation of the CryptoApi interface that returns static responses mimicking calls to the real API.
 */
public class CryptoApiDummy implements CryptoApi {

    @Override
    public String getCryptoMap() {
        return "{\n" +
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
                "            \"id\": 1027,\n" +
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
    }

    @Override
    public String getFiatMap() {
        return "{\n" +
                "\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"rank\": 1,\n" +
                "            \"name\": \"Australian Dollar\",\n" +
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
                "            \"name\": \"New Zealand Dollar\",\n" +
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
                "            \"id\": 1027,\n" +
                "            \"rank\": 5,\n" +
                "            \"name\": \"United States Dollar\",\n" +
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
    }

    @Override
    public String getCryptoMetadata(int id) {
        return "{\n" +
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
                "            \"logo\": null,\n" +
                "            \"id\": 1,\n" +
                "            \"name\": \"Ethereum\",\n" +
                "            \"symbol\": \"ETH\",\n" +
                "            \"slug\": \"ethereum\",\n" +
                "            \"description\": \"Ethereum is a decentralized open-source blockchain system that features its own cryptocurrency, Ether. ETH works as a platform for numerous other cryptocurrencies, as well as for the execution of decentralized smart contracts.\",\n" +
                "            \"notice\": null,\n" +
                "            \"date_added\": \"2015-08-07T00:00:00.000Z\",\n" +
                "            \"tags\": [\n" +
                "                \"mineable\"\n" +
                "            ],\n" +
                "            \"platform\": null,\n" +
                "            \"category\": \"coin\"\n" +
                "        },\n" +
                "        \"2781\": {\n" +
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
                "            \"logo\": null,\n" +
                "            \"id\": 2781,\n" +
                "            \"name\": \"Ethereum2\",\n" +
                "            \"symbol\": \"ETH2\",\n" +
                "            \"slug\": \"ethereum\",\n" +
                "            \"description\": \"Ethereum is a decentralized open-source blockchain system that features its own cryptocurrency, Ether. ETH works as a platform for numerous other cryptocurrencies, as well as for the execution of decentralized smart contracts.\",\n" +
                "            \"notice\": null,\n" +
                "            \"date_added\": \"2015-08-07T00:00:00.000Z\",\n" +
                "            \"tags\": [\n" +
                "                \"mineable\"\n" +
                "            ],\n" +
                "            \"platform\": null,\n" +
                "            \"category\": \"coin\"\n" +
                "        },\n" +
                "        \"1027\": {\n" +
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
                "            \"logo\": null,\n" +
                "            \"id\": 1027,\n" +
                "            \"name\": \"Ethereum3\",\n" +
                "            \"symbol\": \"ETH3\",\n" +
                "            \"slug\": \"ethereum\",\n" +
                "            \"description\": \"Ethereum is a decentralized open-source blockchain system that features its own cryptocurrency, Ether. ETH works as a platform for numerous other cryptocurrencies, as well as for the execution of decentralized smart contracts.\",\n" +
                "            \"notice\": null,\n" +
                "            \"date_added\": \"2015-08-07T00:00:00.000Z\",\n" +
                "            \"tags\": [\n" +
                "                \"mineable\"\n" +
                "            ],\n" +
                "            \"platform\": null,\n" +
                "            \"category\": \"coin\"\n" +
                "        }\n" +
                "    }\n" +
                "    \"status\": {\n" +
                "        \"timestamp\": \"2021-05-20T05:54:37.580Z\",\n" +
                "        \"error_code\": 0,\n" +
                "        \"error_message\": null,\n" +
                "        \"elapsed\": 10,\n" +
                "        \"credit_count\": 1\n" +
                "    }\n" +
                "\n" +
                "}";
    }

    @Override
    public String getCryptoQuote(int id, int convertId) {
        return "{\n" +
                "\n" +
                "    \"data\": {\n" +
                "        \"" + id + "\": {\n" +
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
                "                \"" + convertId + "\": {\n" +
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
    }
}
