package monterrosa.ricardo.desarrolloweb;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import monterrosa.ricardo.desarrolloweb.Adaptadores.NotasAdapter;

import monterrosa.ricardo.desarrolloweb.Modelo.Notes;
import monterrosa.ricardo.desarrolloweb.Services.AppController;
import monterrosa.ricardo.desarrolloweb.Services.DefaultExclusionStrategy;

public class MainActivity extends AppCompatActivity{
    TextView id;
    RecyclerView notas;
    NotasAdapter notasAdapter;
    String URL ="https://pacific-gorge-20783.herokuapp.com/api/notas";
    List<Notes>list = new ArrayList<>();
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = findViewById(R.id.idnotas);
        notas = findViewById(R.id.notas);
        progressDialog = new ProgressDialog(this);

        //notas.notifyAll();
        //getNotas();
        cargardatos();

    }
    public void cargardatos(){
        Log.e("entro","metodo");
        progressDialog.setMessage("Cargando");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL,
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e("entro","try");
                            JSONArray array  = new JSONArray(response);
                            for(int i =0;i<array.length();i++){
                                JSONObject o = array.getJSONObject(i);
                                Notes notes = new Notes(o.getString("id"),o.getString("user_id"),o.getString("grupo_id"),
                                        o.getString("name"),o.getString("descripcion"),o.getString("fecha_final"),o.getString("estado_id"),
                                        o.getString("categoria_id"),o.getString("comentario_id"),o.getString("created_at"),o.getString("updated_at"));
                                list.add(notes);
                                Log.e("iteracion",i+"");

                            }
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            notas.setLayoutManager(linearLayoutManager);
                            notasAdapter = new NotasAdapter(list,getApplicationContext());
                            notas.setAdapter(notasAdapter);
                            notasAdapter.notifyDataSetChanged();
                            progressDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<Notes>search = items(list,newText);
                notasAdapter.setFilter(search);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private List<Notes>items(List<Notes>item,String query){
        query.toLowerCase();
        final List<Notes> filter = new ArrayList<>();

        for (Notes model : item){
            final String text = model.name.toLowerCase();

            if (text.startsWith(query)){
                filter.add(model);
            }
        }
        return filter;
    }

}
