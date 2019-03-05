package monterrosa.ricardo.desarrolloweb.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import monterrosa.ricardo.desarrolloweb.Main2Activity;
import monterrosa.ricardo.desarrolloweb.Modelo.Notes;
import monterrosa.ricardo.desarrolloweb.R;
import monterrosa.ricardo.desarrolloweb.Services.AppController;
import monterrosa.ricardo.desarrolloweb.Services.DefaultExclusionStrategy;

/**
 * Created by Ricardo Monterrosa H on 4/03/2019.
 */

public class NotasAdapter extends RecyclerView.Adapter<NotasAdapter.ViewHolderAdapterNotas>{
    List<Notes>items;
    Context context;
    public NotasAdapter(List<Notes>items,Context context) {
        this.items = items;
        this.context = context;
    }

        @Override
        public ViewHolderAdapterNotas onCreateViewHolder (ViewGroup parent,int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_notas, null);
            return new ViewHolderAdapterNotas(view);
        }

        @Override
        public void onBindViewHolder (ViewHolderAdapterNotas holder,int position){
            final Notes notes = items.get(position);
            holder.nombre.setText(notes.name);
            holder.descripcion.setText(notes.descripcion);
            holder.fechafinal.setText(notes.fecha_final);
            holder.vermas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Main2Activity.class);
                    intent.putExtra("id",notes.id+"");
                    context.startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount () {
            return items.size();
        }

        public void setFilter(List<Notes>item){
            items = new ArrayList<>();
            items.addAll(item);
            notifyDataSetChanged();

        }

        public class ViewHolderAdapterNotas extends RecyclerView.ViewHolder {
            TextView nombre,descripcion,fechafinal;
            Button vermas;

            public ViewHolderAdapterNotas(View itemView) {
                super(itemView);
                nombre = itemView.findViewById(R.id.namenota);
                descripcion = itemView.findViewById(R.id.descripcionnota);
                fechafinal = itemView.findViewById(R.id.fechafinalnota);
                vermas = itemView.findViewById(R.id.vermas);
            }
        }


}
