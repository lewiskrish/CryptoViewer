package exam.model.cryptoresponse.cryptoquote.models;

/**
 * Interface that models some key data from a response from the
 * CoinMarketCap /v1/cryptocurrency/info endpoint.
 */
public interface CryptoQuote {

    double getPrice();

    void setPrice(double price);

    double getVolume24H();

    void setVolume24H(double volume24H);

    double getPercentChange1H();

    void setPercentChange1H(double percentChange1H);

    double getPercentChange24H();

    void setPercentChange24H(double percentChange24H);

    double getPercentChange7D();

    void setPercentChange7D(double percentChange7D);

    double getPercentChange30D();

    void setPercentChange30D(double percentChange30D);

    double getMarketCap();

    void setMarketCap(double marketCap);

    String getLastUpdated();

    void setLastUpdated(String lastUpdated);

    double getCirculatingSupply();

    void setCirculatingSupply(double circulatingSupply);

    double getTotalSupply();

    void setTotalSupply(double totalSupply);

    double getMaxSupply();

    void setMaxSupply(double maxSupply);

    long getNumMarketPairs();

    void setNumMarketPairs(long numMarketPairs);

    long getCmcRank();

    void setCmcRank(long cmcRank);
}
