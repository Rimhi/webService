package monterrosa.ricardo.desarrolloweb.Controlador;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;

import monterrosa.ricardo.desarrolloweb.Services.LongTimeoutAndTryRetryPolicy;

/**
 * Created by Ricardo Monterrosa H on 4/03/2019.
 */

public class Notes {

    private String name;

    public Notes(String name) {
        this.name = name;
    }

    public Notes() {
    }

    public StringRequest getRequest(Response.Listener<String> responseListener, Response.ErrorListener errorListener,String id){
        final HashMap<String,String>credenciales = new HashMap<>();
        credenciales.put("name",name);

        String url ="https://pacific-gorge-20783.herokuapp.com/api/notas/"+id;

        StringRequest request = new StringRequest(Request.Method.GET,url,responseListener,errorListener);

        request.setRetryPolicy(new LongTimeoutAndTryRetryPolicy(LongTimeoutAndTryRetryPolicy.RETRIES_PHONE_ISP));

        return request;

    }
}
