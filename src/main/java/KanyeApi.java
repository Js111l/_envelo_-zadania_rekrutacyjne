import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

//3.
//Napisz program, który wykorzysta API Kanye Rest https://kanye.rest/ by każdorazowo zaproponować nową perełkę mądrości od Kanye Westa.
//Program powinien być obsługiwany z poziomu konsoli i obsługiwać komendę "next" by wywołać następny cytat.
//Program nie potrzebuje oprawy graficznej. Zwróć uwagę na poprawną architekturę aplikacji oraz na czystość kodu.
//Dla chętnych, za dodatkowe punkty: dodaj zapisywanie cytatów w pamięci, by upewnić się, że każdy kolejny cytat jest nowy.
//
//zadanie 3
class Main {
    public static void main(String[] args) {
        //You can either type "next" or "loop".
        //"next" generates new next quote, "loop n" generates new n quotes.
        // For example "loop 12" will generate new next 12 quotes, "next" will give next 13 quote.

        int counter = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("*type \"next\" to generate new next Kanye quote or \"loop n\" to generate n quotes*");
            String string = scanner.nextLine();
            if (string.equals("next")) {
                counter++;
                String s = KanyeApi.next();
                System.out.println("quote " + counter + ": " + s);
            } else if (string.startsWith("loop")) {
                Optional<Integer> numberOfQuotes = Optional.ofNullable(helper(string));
                for (int i = 0; i < numberOfQuotes.orElse(0); i++) {
                    counter++;
                    System.out.println("quote " + counter + ": " + KanyeApi.next());

                }
            }

        }
    }
    private static Integer helper(String string) {//helper function to check if input is correct
        try {
            String numb = new StringBuilder(string).
                    replace(0, 4, "").toString().chars().mapToObj(x -> (char) x).
                    filter(x -> Character.isDigit(x)).map(x -> String.valueOf(x)).collect(Collectors.joining());
            //check if each char is a digit and return a string of digits
            return Integer.valueOf(numb);
        } catch (Exception e) {//if some char is not a digit, throw an exception
            System.out.println("*you should provide an Integer*");
        }
        return null;
    }
}

public class KanyeApi {
    private static URL connection() {
        URL url = null;
        {
            try {
                url = new URL("https://api.kanye.rest");// setup a connection to api
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int resp = connection.getResponseCode();
                if (resp != 200) {
                    throw new RuntimeException("NO connection" + resp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return url;
    }
    private static URL url = connection();//in order to avoid setting up connection each time URL Object is required

    private static JsonObject getQuote() {
        JsonReader jsonReader = null;
        try {
            jsonReader = Json.createReader(url.openStream());
            JsonObject jsonObject = jsonReader.readObject();//retrieve quote from api
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Set<String> quoteMemory = new HashSet<>();//stores distinct, previously generated quotes
    public static String next() {
        String quote = getQuote().getString("quote");
        if (!quoteMemory.contains(quote)) {//if quote hasn't been generated before, return it and add to memory
            quoteMemory.add(quote);
            return quote;
        }
        for (int i = 0; i <= quoteMemory.size(); i++) {
            quote = getQuote().getString("quote");//otherwise generate new quote and check if is unique
            if (!quoteMemory.contains(quote)) {
                quoteMemory.add(quote);
                return quote;
            }
        }
        throw new RuntimeException("No more unique quotes :(");
        //you compared all possible quotes from api and they all have been previously
        //generated. It's no longer possible to generate next unique quote.
    }
}

