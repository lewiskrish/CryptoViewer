# CryptoViewer

Work completed by me (Lewis Krishnamurti) at the University of Sydney as part of a SOFT3202 examination.   
  
The application leverages the CoinMarketCap and Imgur APIs. CoinMarketCap is used for getting data about cryptocurrencies while Imgur is used for uploading an image of a QR code that contains basic data about a desired cryptocurrency.  
  
It also features a local sqlite database which caches data about cryptocurrenices - once a cryptocurrency has been viewed, next time you select the same one you will be presented with the option of using the cache or retrieving fresh data.  
  
Basic concurrency has also been implemented so that the application remains responsive while accessing the APIs or database.

## How to run:
### Config file:  
There is a JSON file found at /src/main/resources/keys.json  
Please replace   
<i><<YOUR_COINMARKETCAP_API_KEY>></i>  
with your own CoinMarketCap API key and  
<i><<YOUR_IMGUR_CLIENT_ID>></i>  
with your own Imgur client ID

It should look similar to:  
```
{
  "input_key": "xxxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
  "output_key": "xxxxxxxxxxxxxxx"
}
```
### Database:
A sqlite database named database.db will be created automatically in the root directory of the project after
the first run.  
### Running:
It can be built and run via Gradle (I used 6.6). 
The program requires two command line arguments both of which can be either 'offline' or 'online'. This corresponds to whether dummy data or the live APIs will be used respectively.  
e.g: 
```
gradle build
gradle run --args="online online" 
``` 
Will use both APIs. 


## References:  
https://stackoverflow.com/a/19671853  
https://github.com/james-d/SimpleMVP  
https://www.geeksforgeeks.org/iterate-map-java/  
https://stackoverflow.com/a/11046198  
https://stackoverflow.com/a/32235935  
https://www.baeldung.com/java-url-encoding-decoding  
https://stackoverflow.com/a/30564698  
https://stackoverflow.com/a/26611622  
https://www.baeldung.com/java-9-http-client  
https://openjdk.java.net/groups/net/httpclient/recipes.html  


