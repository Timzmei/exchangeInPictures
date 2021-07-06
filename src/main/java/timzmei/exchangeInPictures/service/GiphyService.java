package timzmei.exchangeInPictures.service;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

@Service
public class GiphyService {

    public byte[] grabGIF(String gifId) {

        String gifURL = "https://i.giphy.com/" + gifId + ".gif";

        try (InputStream inputStream = new BufferedInputStream((new URL(gifURL)).openStream())) {
            return IOUtils.toByteArray(inputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
