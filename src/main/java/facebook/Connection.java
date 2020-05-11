package facebook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Connection {
    // practicing getting a response from a site
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.google.com");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        // checking for connection
        System.out.println(url.getHost());

        // checking http response (hooray 200!)
        int status = con.getResponseCode();
        System.out.println("status = " + status);

        BufferedReader input = new BufferedReader(
                new InputStreamReader(con.getInputStream())
        );

        // printing out the http doc
        Scanner sc = new Scanner(input);
        while(sc.hasNextLine()){
            String nextLine = sc.nextLine();
            String regexStr = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}";

//            from; https://stackoverflow.com/questions/47713276/how-to-slice-particular-values-from-a-string-using-regular-expression-in-javascr
            Pattern regex = Pattern.compile(regexStr);
            Matcher matcher = regex.matcher(nextLine);

            List<String> urls = new ArrayList<>();

            System.out.println("matcher find? " + matcher.find());
            while(matcher.find()){
                urls.add(matcher.group());
            }

            System.out.println(urls);

//            String lookingFor = ".com";
//            // simple search for other .com urls
//            if(nextLine.contains(lookingFor)){
//                System.out.println("contains a .com");
//                System.out.println(nextLine);
//            }
        }

        con.disconnect();
    }

}
