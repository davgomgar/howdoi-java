package es.dgg.howdoi;

import es.dgg.howdoi.google.GoogleQueryStringGenerator;

import java.io.IOException;
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
        String sanitizedQuery = query.replaceAll("\\?", "");
        Optional<String> instructions = null;
        try {
            GoogleQueryStringGenerator generator = new GoogleQueryStringGenerator();
            HowDoI howdoi = new HowDoI(sanitizedQuery, generator);
            instructions = howdoi.getAnswer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Instructions = " + instructions.orElse("Sorry, no results found"));
    }
}
