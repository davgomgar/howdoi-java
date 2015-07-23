package es.dgg.howdoi;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class HowDoITest {

    private HowDoI howdoi;

    @Test
    public void testFetchResults() throws Exception {
        howdoi = new HowDoI("inexistent results", new QueryStringGenerator());
        assertThat(howdoi.fetchResults()).isEmpty();
    }


}