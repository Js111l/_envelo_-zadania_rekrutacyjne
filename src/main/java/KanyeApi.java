import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

//3.
//Napisz program, który wykorzysta API Kanye Rest https://kanye.rest/ by każdorazowo zaproponować nową perełkę mądrości od Kanye Westa.
//Program powinien być obsługiwany z poziomu konsoli i obsługiwać komendę "next" by wywołać następny cytat.
//Program nie potrzebuje oprawy graficznej. Zwróć uwagę na poprawną architekturę aplikacji oraz na czystość kodu.
//Dla chętnych, za dodatkowe punkty: dodaj zapisywanie cytatów w pamięci, by upewnić się, że każdy kolejny cytat jest nowy.
//
//zadanie 3
class Main {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i <= 123; i++) {
            String res = KanyeApi.next();
            System.out.println(res + " " + i);
            set.add(res);
        }
        System.out.println(set.size() == 122);
        System.out.println(set.size());
    }
}

public class KanyeApi extends Thread{
    private static Set<String> set = new HashSet<>();
    private static Set<String> quoteMemory = new HashSet<>();
    public static String next() {
        if (quoteMemory.size() == 122) {
            throw new RuntimeException("No more unique quotes :(");
        }
        String quote = getQuote().getString("quote");
        if (!quoteMemory.contains(quote)) {
            quoteMemory.add(quote);
            return quote;
        }
        return next();
    }
    private static URL connection() {
        URL url = null;
        {
            try {
                url = new URL("https://api.kanye.rest");
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
    private static URL url = connection();
    private static JsonObject getQuote() {
        JsonReader jsonReader = null;
        try {
            jsonReader = Json.createReader(url.openStream());
            JsonObject jsonObject = jsonReader.readObject();
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
