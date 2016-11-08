package unicauca.movil.httpclient.net;

import android.os.AsyncTask;

import java.io.IOException;

/**
 * Created by Dario Chamorro on 8/11/2016.
 */

public class HttpAsyncTask extends AsyncTask<String, Integer, String> {

    public static final int GET = 0;
    public static final int POST = 1;
    public static final int PUT = 2;
    public static final int DELETE = 3;

    public interface HttpListener{
        void onHttpResponse(String response);
    }

    int method;
    HttpListener httpListener;

    public HttpAsyncTask(int method, HttpListener httpListener) {
        this.method = method;
        this.httpListener = httpListener;
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpConnection con = new HttpConnection();
        String rta = null;
        try {
            switch (method) {
                case GET:
                    rta = con.get(strings[0]);
                    break;
                case POST:
                    rta = con.post(strings[0], strings[1]);
                    break;
                case PUT:
                    rta = con.put(strings[0], strings[1]);
                    break;
                case DELETE:
                    rta = con.delete(strings[0]);
                    break;
            }
        }catch (IOException e){}
        return rta;
    }

    @Override
    protected void onPostExecute(String s) {
        httpListener.onHttpResponse(s);
    }
}
