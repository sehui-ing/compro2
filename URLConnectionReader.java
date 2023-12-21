package compro2.ex;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

@SuppressWarnings("deprecation")
public class URLConnectionReader {
    public static void main(String[] args) throws IOException {
        URL site = new URL("https://www.naver.com/");
        URLConnection url = site.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(url.getInputStream()));

        String inLine;
        while ((inLine = in.readLine()) != null)
            System.out.println(inLine);
        in.close();
    }
}
