package monterrosa.ricardo.desarrolloweb;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import monterrosa.ricardo.desarrolloweb.Adaptadores.NotasAdapter;
import monterrosa.ricardo.desarrolloweb.Adaptadores.UserAdapter;
import monterrosa.ricardo.desarrolloweb.Modelo.Notes;
import monterrosa.ricardo.desarrolloweb.Modelo.User;
import monterrosa.ricardo.desarrolloweb.Services.AppController;
import monterrosa.ricardo.desarrolloweb.Services.DefaultExclusionStrategy;

public class Main2Activity extends AppCompatActivity implements Response.Listener<String>,Response.ErrorListener{
    UserAdapter userAdapter;
    List<User> list = new ArrayList<>();
    ProgressDialog progressDialog;
    String id;
    TextView nombre,descripcion,fecha_inicio,fecha_fin,fecha_actualizada,estado,categoria,encargado,grupo;
    RecyclerView integrantes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        id = getIntent().getExtras().getString("id");
        nombre = findViewById(R.id.nombreNota);
        descripcion = findViewById(R.id.descripcionNota);
        fecha_inicio = findViewById(R.id.created_at);
        fecha_fin = findViewById(R.id.fechafinal);
        fecha_actualizada = findViewById(R.id.updated_at);
        estado = findViewById(R.id.estadonota);
        categoria = findViewById(R.id.categoria);
        encargado = findViewById(R.id.encargadonota);
        grupo = findViewById(R.id.nombregrupo);
        integrantes = findViewById(R.id.integrantesNota);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando ...");
        progressDialog.setCancelable(false);
        getNotas();
    }

    public void getNotas(){
        monterrosa.ricardo.desarrolloweb.Controlador.Notes notes = new monterrosa.ricardo.desarrolloweb.Controlador.Notes();
        Request<?> request = notes.getRequest(this,this,id);
        AppController.getInstance().addToRequestQueue(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("erorr_response",error.getMessage());
    }

    @Override
    public void onResponse(String response) {
        GsonBuilder builder = new GsonBuilder();
        builder.setExclusionStrategies(new DefaultExclusionStrategy());
        Gson gson = builder.create();

        Notes modelo = gson.fromJson(response, Notes.class);
        nombre.setText(modelo.name);
        descripcion.setText(modelo.descripcion);
        fecha_inicio.setText(modelo.created_at);
        fecha_fin.setText(modelo.fecha_final);
        fecha_actualizada.setText(modelo.updated_at);

        try {
            Toast.makeText(getApplicationContext(),"entro al try",Toast.LENGTH_SHORT).show();
            JSONObject jsonObject = new JSONObject(response);
            JSONObject object = (JSONObject) jsonObject.get("user");
            encargado.setText(object.getString("name"));
            JSONObject grupo = (JSONObject) jsonObject.get("grupo");
            this.grupo.setText(grupo.getString("nombre"));
            JSONObject estado = (JSONObject) jsonObject.get("estado");
            this.estado.setText(estado.getString("estado"));
            JSONObject categoria = (JSONObject) jsonObject.get("categoria");
            this.categoria.setText(categoria.getString("categoria"));

            JSONArray integrantes = (JSONArray) jsonObject.get("users");
            for(int i =0;i<integrantes.length();i++){
                JSONObject o = integrantes.getJSONObject(i);
                User user = new User(o.getString("id"),o.getString("name"),o.getString("email"));
                list.add(user);
            }
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            this.integrantes.setLayoutManager(linearLayoutManager);
            userAdapter = new UserAdapter(list);
            this.integrantes.setAdapter(userAdapter);
            userAdapter.notifyDataSetChanged();
            progressDialog.dismiss();




        } catch (JSONException e) {
            e.printStackTrace();
        }



    }


}
