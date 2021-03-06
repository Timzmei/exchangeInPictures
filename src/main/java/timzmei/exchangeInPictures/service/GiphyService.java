package timzmei.exchangeInPictures.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import timzmei.exchangeInPictures.client.GiphyClient;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

@Service
public class GiphyService {

    @Value("${giphy.key}")
    private String giphyKey;

    private final OxrService oxrService;
    private final GiphyClient giphyClient;

    @Autowired
    public GiphyService(OxrService oxrService, GiphyClient giphyClient) {
        this.oxrService = oxrService;
        this.giphyClient = giphyClient;
    }

    public byte[] getImage(String imageId) {

        String gifURL = "https://i.giphy.com/" + imageId + ".gif";

        try (InputStream inputStream = new BufferedInputStream((new URL(gifURL)).openStream())) {
            return IOUtils.toByteArray(inputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public byte[] getGifResponse(String currencyName) {
        String imageId = (String) getGiphyDataResponse(currencyName).get("id");
        byte[] gifImage = getImage(imageId);
        return gifImage;
    }

    private Map<String, Object> getGiphyDataResponse(String currencyName) {
        boolean resultMovePrice = oxrService.analysisRate(currencyName);

        if (resultMovePrice){
            return giphyClient.getGif(giphyKey, "rich").getData();
        } else {
            return giphyClient.getGif(giphyKey, "broke").getData();
        }
    }
}
