package unicauca.movil.httpclient;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import unicauca.movil.httpclient.models.Usuario;
import unicauca.movil.httpclient.net.HttpAsyncTask;

public class MainActivity extends AppCompatActivity implements HttpAsyncTask.HttpListener {

    TextView des, tem, hum, pres;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        des = (TextView) findViewById(R.id.des);
        tem = (TextView) findViewById(R.id.temp);
        hum = (TextView) findViewById(R.id.hum);
        pres = (TextView) findViewById(R.id.pres);


        dialog =  new ProgressDialog(this);
        dialog.setMessage("Cargando ...");

        HttpAsyncTask task = new HttpAsyncTask(HttpAsyncTask.GET, this);

        String url = getString(R.string.url);
        task.execute(url);
        dialog.show();
    }

    @Override
    public void onHttpResponse(String response) {
        dialog.dismiss();
        /*
        //GSON
        Gson gson =  new Gson();

        //CONVERTIR OBJETO A JSON
        Usuario usr = new Usuario();
        usr.setNombre("Dario");
        usr.setCelular("301");
        usr.setId(1);

        String usrJson =  gson.toJson(usr);

        //CONVERTIR JSON A OBJETO
        Usuario user = gson.fromJson("{...}", Usuario.class);

        //CONVERTIR JSON A LISTA DE OBJETOS
        Type type =  new TypeToken< List<Usuario> >(){}.getType();
        List<Usuario> usuarios = gson.fromJson("[ ... ]", type);

        //CREAR JSON SIN OBJETO
        JsonObject j = new JsonObject();
        j.addProperty("nombre", "Dario");
        j.addProperty("celular", "301");
        String jS = j.toString();
        */
        try {
            JSONObject json = new JSONObject(response);
            JSONObject query = json.getJSONObject("query");
            JSONObject results = query.getJSONObject("results");
            JSONObject channel = results.getJSONObject("channel");
            JSONObject atmosphere = channel.getJSONObject("atmosphere");

            String humS = atmosphere.getString("humidity");
            String presS = atmosphere.getString("pressure");

            JSONObject item = channel.getJSONObject("item");
            JSONObject condition = item.getJSONObject("condition");

            String desS = condition.getString("text");
            String tempS = condition.getString("temp");

            des.setText(desS);
            tem.setText(tempS);
            hum.setText(humS);
            pres.setText(presS);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
