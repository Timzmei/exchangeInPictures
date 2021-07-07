package timzmei.exchangeInPictures.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import timzmei.exchangeInPictures.response.GiphyResponse;

import java.util.Map;

@FeignClient(name = "GiphyApi", url = "${giphy.url}")
public interface GiphyClient {
//    @RequestMapping(method = RequestMethod.GET, value = "/random?{key}&tag=rich&rating=r")
    @RequestMapping(method = RequestMethod.GET, value = "/random?{key}&tag={tag}")
    GiphyResponse getGif(@RequestParam("key") String key, @RequestParam("tag") String tag);

//    @RequestMapping(method = RequestMethod.GET, value = "/random?{key}&tag=broke&rating=r")
    @RequestMapping(method = RequestMethod.GET, value = "/random?{key}&tag=broke")

    GiphyResponse getBrokeGif(@RequestParam("key") String key);
}
