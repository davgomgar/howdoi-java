package es.dgg.howdoi.google;

import es.dgg.howdoi.QueryStringGenerator;
import org.apache.commons.lang.StringUtils;

/**
 * Created by david on 23/07/15.
 */
public class GoogleQueryStringGenerator implements QueryStringGenerator {

    private static final String QUERY_STRING = "https://www.google.com/search?q=site:stackoverflow.com%20{query}";
    private static final String WHITESPACE = " ";
    private static final String ENCODED_WHITESPACE = "%20";

    @Override
    public String generateQueryString(final String query) {
        if (StringUtils.isBlank(query)) throw new IllegalArgumentException("Query search can't be blank or empty");
        return sanitizeGeneratedQuery(QUERY_STRING.replace("{query}", query));
    }

    private String sanitizeGeneratedQuery(final String generatedQuery) {
        return generatedQuery.replaceAll(WHITESPACE, ENCODED_WHITESPACE);
    }

}
