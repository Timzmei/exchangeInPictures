package timzmei.exchangeInPictures.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "GiphyApi", url = "${giphy.url}")
public interface GiphyClient {
    @RequestMapping(method = RequestMethod.GET, value = "/random?{key}&tag=rich&rating=r")
    ResponseEntity<Map> getRichGif(@RequestParam("key") String key);

    @RequestMapping(method = RequestMethod.GET, value = "/random?{key}&tag=broke&rating=r")
    ResponseEntity<Map> getBrokeGif(@RequestParam("key") String key);
}
