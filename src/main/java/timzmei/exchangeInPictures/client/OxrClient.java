package timzmei.exchangeInPictures.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import timzmei.exchangeInPictures.response.OxrResponse;

@FeignClient(name = "oxrClient", url = "${oxr.url}")
public interface OxrClient {

    @RequestMapping(method = RequestMethod.GET, value = "/latest.json?app_id={app_id}&base={base}")
    OxrResponse getCurrentRates(@RequestParam("app_id") String appId, @RequestParam("base") String base);

    @RequestMapping(method = RequestMethod.GET, value = "/historical/{date}.json?app_id={app_id}&base={base}")
    OxrResponse getPastRates(@RequestParam("date") String date, @RequestParam("app_id") String appId, @RequestParam("base") String base);

}
