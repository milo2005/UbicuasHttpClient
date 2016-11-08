package unicauca.movil.httpclient.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Dario Chamorro on 8/11/2016.
 */

public class HttpConnection {

    public String get(String url) throws IOException {
        return request("GET", url, null);
    }

    public String post(String url, String json) throws IOException {
        return request("POST", url, json);
    }

    public String put(String url, String json) throws IOException {
        return request("PUT", url, json);
    }

    public String delete(String url) throws IOException {
        return request("DELTE", url, null);
    }

    private String request(String method, String url, String json) throws IOException {
        URL u = new URL(url);
        HttpURLConnection con = (HttpURLConnection) u.openConnection();
        con.setRequestMethod(method);
        con.setDoInput(true);
        con.addRequestProperty("Content-Type","application/json");

        if(json != null)
            con.setDoOutput(true);

        con.connect();

        if(json !=null){
            OutputStream outputStream = con.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            writer.write(json);
            writer.flush();
            writer.close();
        }
        InputStream inputStream =  con.getInputStream();
        return streamToString(inputStream);
    }

    private String streamToString(InputStream inputStream) throws IOException {
        InputStreamReader reader = new InputStreamReader(inputStream);
        ByteArrayOutputStream out =  new ByteArrayOutputStream();
        int ch = 0;
        while( (ch = reader.read()) != -1){
            out.write(ch);
        }

        inputStream.close();
        reader.close();

        return out.toString();
    }

}
