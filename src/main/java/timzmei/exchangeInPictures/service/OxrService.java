package timzmei.exchangeInPictures.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import timzmei.exchangeInPictures.client.OxrClient;
import timzmei.exchangeInPictures.response.OxrResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class OxrService {
    @Autowired
    private OxrClient oxrClient;

    @Value("${setCurrency}")
    private String setCurrency;

    @Value("${oxr.id}")
    private String oxrId;


    public boolean analysisRate(String currencyName) {
        Double curRates = oxrClient.getCurrentRates(oxrId, currencyName).getRates().get(currencyName);
        Double pstRates = oxrClient.getPastRates(getYesterdayDate(), oxrId, currencyName).getRates().get(currencyName);

        return (curRates - pstRates) >= 0;
    }

    private String getYesterdayDate() {
        LocalDate now = LocalDate.now();
        return now.minusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

}
