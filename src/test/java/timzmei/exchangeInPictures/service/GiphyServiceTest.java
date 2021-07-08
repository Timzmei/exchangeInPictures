package timzmei.exchangeInPictures.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import timzmei.exchangeInPictures.client.GiphyClient;
import timzmei.exchangeInPictures.response.GiphyResponse;

@ContextConfiguration(classes = {GiphyService.class, OxrService.class})
@ExtendWith(SpringExtension.class)
public class GiphyServiceTest {
    @MockBean
    private GiphyClient giphyClient;

    @Autowired
    private GiphyService giphyService;

    @MockBean
    private OxrService oxrService;

    @Test
    public void testGetImage() {
        assertNull(this.giphyService.getImage("42"));
    }

    @Test
    public void testGetGifResponse() {
        when(this.oxrService.analysisRate(anyString())).thenReturn(true);
        HashMap<String, Object> data = new HashMap<String, Object>(1);
        when(this.giphyClient.getGif(anyString(), anyString()))
                .thenReturn(new GiphyResponse(data, new HashMap<String, Object>(1)));
        assertNull(this.giphyService.getGifResponse("Currency Name"));
        verify(this.oxrService).analysisRate(anyString());
        verify(this.giphyClient).getGif(anyString(), anyString());
    }

    @Test
    public void testGetGifResponse2() {
        when(this.oxrService.analysisRate(anyString())).thenReturn(true);
        GiphyResponse giphyResponse = mock(GiphyResponse.class);
        when(giphyResponse.getData()).thenReturn(new HashMap<String, Object>(1));
        when(this.giphyClient.getGif(anyString(), anyString())).thenReturn(giphyResponse);
        assertNull(this.giphyService.getGifResponse("Currency Name"));
        verify(this.oxrService).analysisRate(anyString());
        verify(this.giphyClient).getGif(anyString(), anyString());
        verify(giphyResponse).getData();
    }

    @Test
    public void testGetGifResponse3() {
        when(this.oxrService.analysisRate(anyString())).thenReturn(false);
        GiphyResponse giphyResponse = mock(GiphyResponse.class);
        when(giphyResponse.getData()).thenReturn(new HashMap<String, Object>(1));
        when(this.giphyClient.getGif(anyString(), anyString())).thenReturn(giphyResponse);
        assertNull(this.giphyService.getGifResponse("Currency Name"));
        verify(this.oxrService).analysisRate(anyString());
        verify(this.giphyClient).getGif(anyString(), anyString());
        verify(giphyResponse).getData();
    }
}

