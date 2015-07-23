package es.dgg.howdoi;

import java.util.Optional;

/**
 * Created by david on 23/07/15.
 */
public class HowDoI {

    private final String query;
    private final QueryStringGenerator generator;

    public HowDoI(String query, QueryStringGenerator generator) {
        this.query = query;
        this.generator = generator;
    }

    public Optional<String> fetchResults() {
        Optional<String> result = Optional.empty();

        return result;
    }
}
