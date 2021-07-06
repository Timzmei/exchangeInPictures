package timzmei.exchangeInPictures.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "oxrClient", url = "${oxr.url}")
public interface OxrClient {

    @RequestMapping(method = RequestMethod.GET, value = "/latest.json?app_id={app_id}&base={base}")
    ResponseEntity<Map<String, Object>> getCurrentRates(@RequestParam("app_id") String appId, @RequestParam("base") String base);

    @RequestMapping(method = RequestMethod.GET, value = "/historical/{date}.json?app_id={app_id}&base={base}")
    ResponseEntity<Map<String, Object>> getYesterdayRates(@RequestParam("date") String date, @RequestParam("app_id") String appId, @RequestParam("base") String base);

}
