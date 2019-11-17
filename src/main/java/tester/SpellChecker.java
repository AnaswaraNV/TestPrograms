package tester;

import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class SpellChecker {

    static String host = "https://api.cognitive.microsoft.com";
    static String path = "/bing/v5.0/spellcheck";

    // NOTE: Replace this example key with a valid subscription key.
    static String key = "4e58151e6d5048baa9c449e0929807e7";

    static String mkt = "en-US";
    static String mode = "Proof";
    static String text = "Hollo, wrld!";

    public static void check () throws Exception {
        String params = "?mkt=" + mkt + "&mode=" + mode;
//        String params = "?text=" + text;
        URL url = new URL(host + path + params);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", "" + text.length() + 5);
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", key);
        connection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes("text=" + text);
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        in.close();
    }

    public static void wikiCheck() {
        JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());
        // comment in to use statistical ngram data:
        //langTool.activateLanguageModelRules(new File("/data/google-ngram-data"));
        List<RuleMatch> matches = null;
        try {
            String word = "CLOSED LST";
            matches = langTool.check(word.toLowerCase());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (RuleMatch match : matches) {
            //for spelling mistake only
            if(match.getMessage().contains("spelling mistake")) {
                System.out.println("Potential error at characters " +
                        match.getFromPos() + "-" + match.getToPos() + ": " +
                        match.getMessage());
                System.out.println(">>>>>>.Suggested correction(s): " +
                        match.getSuggestedReplacements());
            }
        }
    }

    public static void main(String[] args) {
        try {
            //check ();
            wikiCheck();
        }
        catch (Exception e) {
            System.out.println (e);
        }
    }
}
