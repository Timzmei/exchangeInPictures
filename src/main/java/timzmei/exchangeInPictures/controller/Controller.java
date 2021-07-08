package timzmei.exchangeInPictures.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import timzmei.exchangeInPictures.service.GiphyService;

@RestController
public class Controller {

    @Autowired
    private GiphyService giphyService;


    @GetMapping(path = "/showChange/{currency_name}", produces = MediaType.IMAGE_GIF_VALUE)
    public @ResponseBody byte[] getRate(@PathVariable("currency_name") String currencyName) {
        return giphyService.getGifResponse(currencyName);
    }
}
