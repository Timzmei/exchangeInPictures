package timzmei.exchangeInPictures.service;

import static org.junit.jupiter.api.Assertions.assertNull;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import timzmei.exchangeInPictures.ServiceTestConfiguration;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayName("Giphy service class test:")
public class GiphyServiceTest {



    @Rule
    public WireMockRule wireMockRule = new WireMockRule();

    @Test
    public void getImageTest(){

    }


}

