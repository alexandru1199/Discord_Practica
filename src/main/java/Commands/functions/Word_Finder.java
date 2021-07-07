package commands.functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Word_Finder {
    public static boolean ContainsWord(String fullString, String partWord){
        String pattern = "\\b"+partWord+"\\b";
        Pattern p= Pattern.compile(pattern);
        Matcher m=p.matcher(fullString);
        return m.find();
    }
}
