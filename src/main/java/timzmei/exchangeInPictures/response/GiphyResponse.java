package timzmei.exchangeInPictures.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GiphyResponse {

    private Map<String, Object> data;
    private Map<String, Object> meta;

}
