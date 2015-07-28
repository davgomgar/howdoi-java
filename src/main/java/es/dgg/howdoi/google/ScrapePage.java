package es.dgg.howdoi.google;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import static org.jsoup.Jsoup.connect;

/**
 * Created by david on 28/07/15.
 */
public class ScrapePage {
    private static final String MOZILLA_USER_AGENT = "Mozilla/5.0";
    private static final int TEN_SECONDS = 10000;

    private final String queryString;

    public ScrapePage(String queryString) {
        this.queryString = queryString;
    }


    public Elements getResults(final String selection) throws IOException {
        Document document = connect(queryString).userAgent(MOZILLA_USER_AGENT).timeout(TEN_SECONDS).get();
        return document.select(selection);
    }
}
