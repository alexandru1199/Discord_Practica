package commands.functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Word_Finder {
    public static boolean ContainsWord(String fullString, String partWord){
        //using regex \b means if a word is present at the start of word
        String pattern = "\\b"+partWord;
        //compiles regex
        Pattern p= Pattern.compile(pattern);
        //gets the text that matches the text
        Matcher m=p.matcher(fullString);
        //if its found returns true
        return m.find();
    }
}
