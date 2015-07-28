package es.dgg.howdoi.google;

import es.dgg.howdoi.stackoverflow.StackOverflowLink;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by david on 26/07/15.
 */
public class LinkResultParser {

    private final Elements elements;

    public LinkResultParser(final Elements elements) {
        this.elements = elements;
    }

    public List<StackOverflowLink> parse() {
        List<StackOverflowLink> results = elements
                .stream()
                .filter(this::invalidLink)
                .map(this::parseLink).collect(Collectors.toList());


        return results;
    }

    private boolean invalidLink(Element element) {

        String href = element.attr("href");
        return href.indexOf("http") > -1 && isNotBookLink(href);
    }

    private boolean isNotBookLink(String href) {
        return href.indexOf("/amzn/click") == -1;
    }


    private StackOverflowLink parseLink(Element element) {
        String href = element.attr("href");
        int index = href.indexOf("http");
        return new StackOverflowLink(href.substring(index));
    }

}
