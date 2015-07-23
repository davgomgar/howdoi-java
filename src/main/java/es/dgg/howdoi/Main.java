package es.dgg.howdoi;

import java.util.Optional;

/**
 * Created by david on 23/07/15.
 */
public class Main  {
    public static void main(String[] args) {
        if (args != null && args.length != 1){
                throw new IllegalArgumentException("Please, tell me what I have to look for in stack overflow :) ");
        }

        String query = args[0];
        String sanitizedQuery = query.replaceAll("?", "");
        QueryStringGenerator generator = new QueryStringGenerator();
        HowDoI howdoi = new HowDoI(sanitizedQuery, generator);
        Optional<String> instructions = howdoi.fetchResults();
        System.out.println("Instructions = " + instructions.orElse("Sorry, no results found"));
    }
}
