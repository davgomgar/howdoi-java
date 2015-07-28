package es.dgg.howdoi;

import es.dgg.howdoi.google.GoogleQueryStringGenerator;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


public class HowDoITest {

    private HowDoI howdoi;

    @Test
    public void returns_valid_result() throws Exception {
        howdoi = new HowDoI("open file java", new GoogleQueryStringGenerator());
        assertThat(howdoi.getAnswer()).isNotEqualTo(Optional.empty());
    }


}