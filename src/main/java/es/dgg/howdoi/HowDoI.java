package es.dgg.howdoi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import es.dgg.howdoi.google.LinkResultParser;
import es.dgg.howdoi.google.ScrapePage;
import es.dgg.howdoi.stackoverflow.StackOverflowLink;
import es.dgg.howdoi.stackoverflow.StackOverflowCollection;
import es.dgg.howdoi.stackoverflow.StackOverflowQueryStringGenerator;
import es.dgg.howdoi.stackoverflow.StackOverflowQuestion;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by david on 23/07/15.
 */
public class HowDoI {

    private final String query;
    private final QueryStringGenerator generator;
    private static String GOOGLE_RESULTS_FILTER = ".r > a";;
    private Gson gson;

    public HowDoI(String query, QueryStringGenerator generator) {
        this.query = query;
        this.generator = generator;
        gson = new Gson();
    }

    public Optional<String> getAnswer() throws IOException {
        List<StackOverflowLink> results = fetchGoogleResults();

        if (!results.isEmpty()) {

            Optional<StackOverflowQuestion> answer = fetchStackOverflowResults(results);
            if (answer.isPresent()) {

                return Optional.of(extractSolutionFromSelectedAnswer(answer.get().getBody()));
            }
        }
        return  Optional.empty();
    }

    private List<StackOverflowLink> fetchGoogleResults() throws IOException {
        String generatedQuery = generator.generateQueryString(query);
        ScrapePage fetchGoogleResults = new ScrapePage(generatedQuery);
        Elements googleResults = fetchGoogleResults.getResults(GOOGLE_RESULTS_FILTER);
        LinkResultParser googleParser = new LinkResultParser(googleResults);

        return googleParser.parse();
    }

    private String extractSolutionFromSelectedAnswer(String html) {
        Document document = Jsoup.parse(html);
        Elements codeSections = document.select("code");
        Optional<Element> selectedCodeSection =
                codeSections.stream().sorted((e1, e2) -> e2.text().length() - e1.text().length()).findFirst();
        if (selectedCodeSection.isPresent()) {
            return selectedCodeSection.get().text();
        }
        return StringUtils.EMPTY;
    }

    private Optional<StackOverflowQuestion> fetchStackOverflowResults(List<StackOverflowLink> collection) {
        String stackIds = collection
                .stream()
                .map(link -> String.valueOf(link.getId()))
                .collect(Collectors.joining(";"));
        QueryStringGenerator queryStringGenerator = new StackOverflowQueryStringGenerator();
        String stackOverflowQuery = queryStringGenerator.generateQueryString(stackIds);
        List<StackOverflowQuestion> questions = null;
        try {
            questions = getStackOverflowResults(stackOverflowQuery);
        } catch (IOException e) {
            return Optional.empty();
        }

        return questions.stream()
                .filter(question ->  StringUtils.isNotEmpty(question.getBody()))
                .findFirst();


    }

    private List<StackOverflowQuestion> getStackOverflowResults(String stackOverflowQuery) throws IOException {
        CloseableHttpResponse response = queryStackOverflowForResults(stackOverflowQuery);
        StackOverflowCollection collection = generateCollectionFromResponse(response);

        return collection.getItems();
    }

    private CloseableHttpResponse queryStackOverflowForResults(String stackOverflowQuery) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet request = new HttpGet(stackOverflowQuery);
        return httpclient.execute(request);
    }

    private StackOverflowCollection generateCollectionFromResponse(CloseableHttpResponse response) throws IOException {
        String jsonResponse = EntityUtils.toString(response.getEntity());
        Type collectionType = new TypeToken<StackOverflowCollection>(){}.getType();
        return gson.fromJson(jsonResponse, collectionType);
    }


}
