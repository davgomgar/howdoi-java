package es.dgg.howdoi.stackoverflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by david on 28/07/15.
 */
public class StackOverflowLink {

    private final Logger logger = LoggerFactory.getLogger(StackOverflowLink.class);

    private String url;
    private long id;

    public StackOverflowLink(String url) {
        if (isNotValidUrl(url)) {
            throw new IllegalArgumentException("Please, enter a valid HTTP URI");
        }
        logger.debug("Creating link for URL '(}': ", url);
        this.url = url;
        //Note that URLs are in the form http(s)://stackverflow.com/questions/{id}
        //that's the reason to extract the fourth value in the split below
        String[] parts = url.split("\\/");
        id = Long.parseLong(parts[4]);
    }

    private boolean isNotValidUrl(String url) {
        return !(url.startsWith("http://") || url.startsWith("https://"));
    }

    public String getUrl() {
        return url;
    }

    public long getId() {
        return id;
    }
}
