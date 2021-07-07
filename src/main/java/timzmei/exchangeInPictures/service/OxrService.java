package timzmei.exchangeInPictures.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        OxrResponse oxrResponse = oxrClient.getCurrentRates(oxrId, currencyName);

        System.out.println(oxrResponse.getBase());

        Map<String, Double> map = oxrResponse.getRates();

        Double curRates = oxrResponse.getRates().get(setCurrency);
        Double pstRates = oxrClient.getPastRates(getYesterdayDate(), oxrId, currencyName).getRates().get(setCurrency);

        System.out.println(map.toString());
        System.out.println(currencyName + " " + map.get(currencyName));
        System.out.println(map.get("GBP"));
        System.out.println(curRates + " " + pstRates + " " + (curRates >= pstRates));

        return (curRates >= pstRates);
    }

    private String getYesterdayDate() {
        LocalDate now = LocalDate.now();
        return now.minusDays(1).format(DateTimeFormatter.ISO_DATE);
    }

}
