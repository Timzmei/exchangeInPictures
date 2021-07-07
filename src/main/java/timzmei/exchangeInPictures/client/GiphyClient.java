package timzmei.exchangeInPictures.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import timzmei.exchangeInPictures.response.GiphyResponse;

@FeignClient(name = "GiphyApi", url = "${giphy.url}")
public interface GiphyClient {
    @RequestMapping(method = RequestMethod.GET, value = "/random?{key}&tag={tag}&rating=r")
    GiphyResponse getGif(@RequestParam("key") String key, @RequestParam("tag") String tag);

}
