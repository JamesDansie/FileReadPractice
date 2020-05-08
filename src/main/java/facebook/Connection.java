package facebook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

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
            System.out.println(sc.nextLine());
        }

        con.disconnect();
    }

}
