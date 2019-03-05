package monterrosa.ricardo.desarrolloweb.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import monterrosa.ricardo.desarrolloweb.Modelo.User;
import monterrosa.ricardo.desarrolloweb.R;

/**
 * Created by Ricardo Monterrosa H on 4/03/2019.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolderUserAdapter>{
    List<User>list;

    public UserAdapter(List<User> list) {
        this.list = list;
    }

    @Override
    public ViewHolderUserAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_user,null);
        return new ViewHolderUserAdapter(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderUserAdapter holder, int position) {
        User user = list.get(position);
        holder.nombre.setText(user.name);
        holder.correo.setText(user.email);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderUserAdapter extends RecyclerView.ViewHolder{
        TextView nombre,correo;

        public ViewHolderUserAdapter(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nameuser);
            correo = itemView.findViewById(R.id.emailuser);
        }
    }
}
