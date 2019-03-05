package monterrosa.ricardo.desarrolloweb.Modelo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ricardo Monterrosa H on 4/03/2019.
 */

public class Notes {
    @SerializedName("id")
    public String id;

    @SerializedName("user_id")
    public String user_id;

    @SerializedName("grupo_id")
    public String grupo_id;

    @SerializedName("name")
    public String name;

    @SerializedName("descripcion")
    public String descripcion;

    @SerializedName("fecha_final")
    public String fecha_final;

    @SerializedName("estado_id")
    public String estado_id;

    @SerializedName("categoria_id")
    public String categoria_id;

    @SerializedName("comentario_id")
    public String comentario_id;

    @SerializedName("created_at")
    public String created_at;

    @SerializedName("updated_at")
    public String updated_at;




    public Notes(String id, String user_id, String grupo_id, String name, String descripcion, String fecha_final, String estado_id, String categoria_id, String comentario_id, String created_at, String updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.grupo_id = grupo_id;
        this.name = name;
        this.descripcion = descripcion;
        this.fecha_final = fecha_final;
        this.estado_id = estado_id;
        this.categoria_id = categoria_id;
        this.comentario_id = comentario_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
