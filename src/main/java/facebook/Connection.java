package facebook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Connection {
    // practicing getting a response from a site
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.google.com");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();
        System.out.println("status = " + status);

        BufferedReader input = new BufferedReader(
                new InputStreamReader(con.getInputStream())
        );

        Scanner sc = new Scanner(input);
        while(sc.hasNextLine()){
            System.out.println(sc.nextLine());
        }

        con.disconnect();
    }

}
