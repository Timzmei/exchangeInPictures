package timzmei.exchangeInPictures.controller;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import timzmei.exchangeInPictures.service.GiphyService;

@ContextConfiguration(classes = {Controller.class})
@ExtendWith(SpringExtension.class)
public class ControllerTest {
    @Autowired
    private Controller controller;

    @MockBean
    private GiphyService giphyService;

    @Test
    public void testGetRate() throws Exception {
        when(this.giphyService.getGifResponse(anyString())).thenReturn("AAAAAAAA".getBytes("UTF-8"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/showChange/{currency_name}",
                "Currency name");
        MockMvcBuilders.standaloneSetup(this.controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("image/gif"))
                .andExpect(MockMvcResultMatchers.content().string("AAAAAAAA"));
    }
}

