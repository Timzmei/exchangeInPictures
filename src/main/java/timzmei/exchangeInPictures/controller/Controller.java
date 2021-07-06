package timzmei.exchangeInPictures.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import timzmei.exchangeInPictures.client.GiphyClient;
import timzmei.exchangeInPictures.client.OxrClient;
import timzmei.exchangeInPictures.service.GiphyService;
import timzmei.exchangeInPictures.service.OxrService;

import java.util.Map;

@RestController
public class Controller {
    @Autowired
    private OxrClient oxrClient;

    @Autowired
    private GiphyClient giphyClient;

    @Autowired
    private GiphyService giphyService;

    @Autowired
    private OxrService oxrService;

    @Value("${oxr.id}")
    private String oxrId;

    @Value("${giphy.key}")
    private String giphyKey;

    @GetMapping(path = "/eRate/{currency}")
    public HttpEntity<byte[]> getRate(@PathVariable("currency") String currency) {
        Double currentRUB = oxrService.analysisRate(oxrClient.getCurrentRates(oxrId, currency).getBody());
        Double yesterdayRUB = oxrService.analysisRate(oxrClient.getYesterdayRates(oxrService.getYesterdayDate(), oxrId, currency).getBody());
        Map finalResponse = null;
        if ((currentRUB - yesterdayRUB) >= 0) {
            finalResponse = giphyClient.getRichGif(giphyKey).getBody();
        } else finalResponse = giphyClient.getBrokeGif(giphyKey).getBody();

        Map<String, String> gifInfo = (Map<String, String>) finalResponse.get("data");
        byte[] gifImage = giphyService.grabGIF(gifInfo.get("id"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_GIF);
        headers.setContentLength(gifImage.length);
        return new HttpEntity<>(gifImage, headers);
    }
}
