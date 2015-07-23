package es.dgg.howdoi;

import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;


public class QueryStringGeneratorTest {

    @Test
    public void throws_exception_if_empty_query_string() {
        QueryStringGenerator generator = new QueryStringGenerator();
        assertThatThrownBy(() -> generator.generateQueryString(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Query search can't be blank or empty")
                .hasNoCause();
    }

    @Test
    public void replaces_placeholder_with_query_string() {
        QueryStringGenerator generator = new QueryStringGenerator();
        assertThat(generator.generateQueryString("testing"))
                            .isEqualToIgnoringCase("https://www.google.com/search?q=site:stackoverflow.com%20testing");
    }

    @Test
    public void escapes_all_whitespaces() {
        QueryStringGenerator generator = new QueryStringGenerator();
        assertThat(generator.generateQueryString("this is a test")).doesNotContain(" ");
    }

}