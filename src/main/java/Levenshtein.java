import model.MapObject;
import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.language.AmericanEnglish;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to find word similarity of 2 words
 */
public class Levenshtein {

    public static int distance(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }

    public static void main(String [] args) {
        String userInput = "Lst";
        List<String> statusList = Arrays.asList("Qualification" , "Prospecting", "Needs Analysis" , "Closed Lost" ,
                "Closed Won" , "Value Proposition" , "Id. Decision Makers" , "Perception Analysis" ,
                "Proposal/Price Quote", "Negotiation/Review");

//        for (int i = 0; i < data.length; i += 2)
//            System.out.println("distance(" + data[i] + ", " + data[i+1] + ") = " + distance(data[i], data[i+1]));
        for(String status : statusList ) {
            System.out.println("(" + userInput + ", " + status + ") --> " +  distance(userInput.toLowerCase(), status));
        }

        System.out.println("********************************");
        System.out.println();

        List<String> possibleSuggestions = Arrays.asList("Promoting", "Prompting", "Protecting", "Projecting", "Proposing", "Prospecting",
                "Profiting", "Propping", "Probating", "Prorating");

        List<MapObject> sortedList = Arrays.asList(new MapObject(1, "type1"),
                new MapObject(2, "type2"), new MapObject(2, "type3"),
                new MapObject(6, "type4"), new MapObject(7, "type5"));

        int smallest = sortedList.get(0).getDistance();
        List<MapObject> filteredList = sortedList.stream()
                                                .filter(l1 -> (l1.getDistance() - smallest) <= 2)
                                                .collect(Collectors.toList());
        System.out.println("filtered list" + filteredList);

//        for(String possibleWord : possibleSuggestions) {
//            for(String status : statusList) {
//                System.out.println("(" + possibleWord + ", " + status + ") --> " +  distance(possibleWord , status));
//                if(distance(possibleWord , status) == 0) {
//                    System.out.println("***************BRAVO!!!! Word found... " + possibleWord + "************");
//                } else if(distance(possibleWord , status) < 3) {
//                    System.out.println("***************CLOSE MATCH!!!! " + possibleWord + "************");
//                }
//            }
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        }

    }

}