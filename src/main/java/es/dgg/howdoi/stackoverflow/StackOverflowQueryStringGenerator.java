package es.dgg.howdoi.stackoverflow;

import es.dgg.howdoi.QueryStringGenerator;
import org.apache.commons.lang.StringUtils;

/**
 * Created by david on 30/7/15.
 */
public class StackOverflowQueryStringGenerator implements QueryStringGenerator {

    private static final String QUERY_STRING =
            "https://api.stackexchange.com/2.2/questions/${questionIds}/answers?site=stackoverflow&pagesize=5&order=desc&sort=votes&filter=!9YdnSM68i";

    @Override
    public String generateQueryString(final String ids) {
        if (StringUtils.isBlank(ids)) throw new IllegalArgumentException("Please, enter some question ids");
        return QUERY_STRING.replace("${questionIds}", ids);
    }


}
