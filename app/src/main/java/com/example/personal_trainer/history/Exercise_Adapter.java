package com.example.personal_trainer.history;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personal_trainer.R;

import java.util.List;

public class Exercise_Adapter extends RecyclerView.Adapter<Exercise_Adapter.ViewHolder>{

    private List<Exercise> exerciseList;
    private Context context;

    public Exercise_Adapter(List<Exercise> exerciseList, Context context) {
        this.exerciseList = exerciseList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_ejercicios,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtNombre.setText(exerciseList.get(position).getNombreEjercicio());
        holder.txtDuracion.setText(exerciseList.get(position).getDuracion()+" minutos");
        holder.txtCalorias.setText(exerciseList.get(position).getCalorias()+" calorías");
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(holder.getPosition());
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {

        return exerciseList.size();
    }

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private TextView txtNombre;
        private TextView txtDuracion;
        private TextView txtCalorias;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.nombre);
            txtDuracion = itemView.findViewById(R.id.duracion);
            txtCalorias = itemView.findViewById(R.id.calorias);

            itemView.setOnCreateContextMenuListener(this);

        }
        /*

        @Override
        public void onViewRecycled(ViewHolder holder) {
            holder.itemView.setOnLongClickListener(null);
            super.onViewRecycled(holder);
        }
        */

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v,
                                        ContextMenu.ContextMenuInfo menuInfo) {
            /*
            menu.add(Menu.NONE, R.id.ctx_menu_remove_backup,
                    Menu.NONE, R.string.remove_backup);
            menu.add(Menu.NONE, R.id.ctx_menu_restore_backup,
                    Menu.NONE, R.string.restore_backup);
                    */

        }
    }
}
