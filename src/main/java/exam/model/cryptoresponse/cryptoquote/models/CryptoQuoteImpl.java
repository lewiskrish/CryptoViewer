package exam.model.cryptoresponse.cryptoquote.models;

/**
 * Default implementation of the CryptoQuote interface.
 */
public class CryptoQuoteImpl implements CryptoQuote {

    double price;
    double volume24H;
    double percentChange1H;
    double percentChange24H;
    double percentChange7D;
    double percentChange30D;
    double marketCap;
    String lastUpdated;
    double circulatingSupply;
    double totalSupply;
    double maxSupply;
    long numMarketPairs;
    long cmcRank;

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getVolume24H() {
        return this.volume24H;
    }

    @Override
    public void setVolume24H(double volume24H) {
        this.volume24H = volume24H;
    }

    @Override
    public double getPercentChange1H() {
        return this.percentChange1H;
    }

    @Override
    public void setPercentChange1H(double percentChange1H) {
        this.percentChange1H = percentChange1H;
    }

    @Override
    public double getPercentChange24H() {
        return this.percentChange24H;
    }

    @Override
    public void setPercentChange24H(double percentChange24H) {
        this.percentChange24H = percentChange24H;
    }

    @Override
    public double getPercentChange7D() {
        return this.percentChange7D;
    }

    @Override
    public void setPercentChange7D(double percentChange7D) {
        this.percentChange7D = percentChange7D;
    }

    @Override
    public double getPercentChange30D() {
        return this.percentChange30D;
    }

    @Override
    public void setPercentChange30D(double percentChange30D) {
        this.percentChange30D = percentChange30D;
    }

    @Override
    public double getMarketCap() {
        return this.marketCap;
    }

    @Override
    public void setMarketCap(double marketCap) {
        if (marketCap < 0) {
            this.marketCap = 0;
            return;
        }
        this.marketCap = marketCap;
    }

    @Override
    public String getLastUpdated() {
        return this.lastUpdated;
    }

    @Override
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public double getCirculatingSupply() {
        return this.circulatingSupply;
    }

    @Override
    public void setCirculatingSupply(double circulatingSupply) {
        if (circulatingSupply < 0) {
            this.circulatingSupply = 0;
            return;
        }
        this.circulatingSupply = circulatingSupply;
    }

    @Override
    public double getTotalSupply() {
        return this.totalSupply;
    }

    @Override
    public void setTotalSupply(double totalSupply) {
        if (totalSupply < 0) {
            this.totalSupply = 0;
            return;
        }
        this.totalSupply = totalSupply;
    }

    @Override
    public double getMaxSupply() {
        return this.maxSupply;
    }

    @Override
    public void setMaxSupply(double maxSupply) {
        if (maxSupply < 0) {
            this.maxSupply = 0;
            return;
        }
        this.maxSupply = maxSupply;
    }

    @Override
    public long getNumMarketPairs() {
        return this.numMarketPairs;
    }

    @Override
    public void setNumMarketPairs(long numMarketPairs) {
        this.numMarketPairs = numMarketPairs;
    }

    @Override
    public long getCmcRank() {
        return this.cmcRank;
    }

    @Override
    public void setCmcRank(long cmcRank) {
        this.cmcRank = cmcRank;
    }
}
