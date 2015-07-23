package es.dgg.howdoi;

import org.apache.commons.lang.StringUtils;

/**
 * Created by david on 23/07/15.
 */
public class QueryStringGenerator {

    private static final String QUERY_STRING = "https://www.google.com/search?q=site:stackoverflow.com%20{query}";

    public String generateQueryString(final String query) {
        if (StringUtils.isBlank(query)) throw new IllegalArgumentException("Query search can't be blank or empty");
        return sanitizeGeneratedQuery(QUERY_STRING.replace("{query}", query));
    }

    public String sanitizeGeneratedQuery(final String generatedQuery) {
        return generatedQuery.replaceAll(" ", "%20");
    }

}
