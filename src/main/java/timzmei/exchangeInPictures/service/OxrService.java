package timzmei.exchangeInPictures.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Map;

@Service
public class OxrService {

    @Value("${setCurrency}")
    private String setCurrency;

    public double analysisRate(Map<String, Object> responseEntity) {
        Map<String, Double> rates = (Map<String, Double>) responseEntity.get("rates");
        return rates.get(setCurrency);
    }

    public String getYesterdayDate() {
        LocalDate now = LocalDate.now();
        return now.minusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
