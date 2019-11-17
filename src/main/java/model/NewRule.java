package model;

import org.languagetool.AnalyzedSentence;
import org.languagetool.AnalyzedToken;
import org.languagetool.AnalyzedTokenReadings;
import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewRule {

    public static void newRuleCreation() {
        JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());
        Rule rule = new Rule() {
            @Override
            public String getId() {
                return "DEMO_RULE"; // a unique id that doesn't change over time
            }

            @Override
            public String getDescription() {
                return "A demo rule that just prints the text analysis";  // shown in the configuration dialog
            }

            // This is the method with the error detection logic that you need to implement:
            @Override
            public RuleMatch[] match(AnalyzedSentence sentence) throws IOException {
                List<RuleMatch> ruleMatches = new ArrayList<>();

                // Let's get all the tokens (i.e. words) of this sentence, but not the spaces:
                AnalyzedTokenReadings[] tokens = sentence.getTokensWithoutWhitespace();

                // No let's iterate over those - note that the first token will
                // be a special token that indicates the start of a sentence:
                for (AnalyzedTokenReadings token : tokens) {

                    System.out.println("Token: " + token.getToken());  // the original word from the input text

                    // A word can have more than one reading, e.g. 'dance' can be a verb or a noun,
                    // so we iterate over the readings:
                    for (AnalyzedToken analyzedToken : token.getReadings()) {
                        System.out.println("  Lemma: " + analyzedToken.getLemma());
                        System.out.println("  POS: " + analyzedToken.getPOSTag());
                    }

                    // You can add your own logic here to find errors. Here, we just consider
                    // the word "demo" an error and create a rule match that LanguageTool will
                    // then show to the user:
                    if (token.getToken().equals("demo")) {
                        RuleMatch ruleMatch = new RuleMatch(this, sentence, token.getStartPos(), token.getEndPos(), "The demo rule thinks this looks wrong");
                        ruleMatch.setSuggestedReplacement("blablah");  // the user will see this as a suggested correction
                        ruleMatches.add(ruleMatch);
                    }
                }

                return toRuleMatchArray(ruleMatches);
            }
        };
        langTool.addRule(rule);
        List<RuleMatch> matches = null;
        try {
            String word = "demo";
            matches = langTool.check(word.toLowerCase());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (matches != null) {
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

    }

    public static void newRuleCheck() {
        JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());
        // comment in to use statistical ngram data:
        //langTool.activateLanguageModelRules(new File("/data/google-ngram-data"));
        List<RuleMatch> matches = null;
        try {
            String word = "demo";
            matches = langTool.check(word.toLowerCase());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (matches != null) {
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
    }

    public static void main(String[] args) {
        try {
            newRuleCreation();
          //  newRuleCheck();
        }
        catch (Exception e) {
            System.out.println (e);
        }
    }

}
