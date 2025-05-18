package com.example.buszjegy;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buszjegy.DatabaseHelperClass;
import com.example.buszjegy.JegyModelClass;
import com.example.buszjegy.R;

import java.util.List;

public class JegyAdapterClass extends RecyclerView.Adapter<JegyAdapterClass.ViewHolder> {

    List<JegyModelClass> jegy;
    Context context;
    DatabaseHelperClass databaseHelperClass;

    public JegyAdapterClass(List<JegyModelClass> jegy, Context context) {
        this.jegy = jegy;
        this.context = context;
        databaseHelperClass = new DatabaseHelperClass(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.jegy_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final JegyModelClass jegyModelClass = jegy.get(position);

        holder.textViewID.setText(Integer.toString(jegyModelClass.getId()));
        holder.editText_Viszonylat.setText(jegyModelClass.getViszonylat());
        holder.editText_Ar.setText(jegyModelClass.getAr());
        holder.button_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringViszonylat = holder.editText_Viszonylat.getText().toString();
                String stringAr = holder.editText_Ar.getText().toString();

                databaseHelperClass.updateJegy(new JegyModelClass(jegyModelClass.getId(),stringViszonylat,stringAr));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseHelperClass.deleteJegy(jegyModelClass.getId());
                jegy.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return jegy.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        EditText editText_Viszonylat;
        EditText editText_Ar;
        Button button_Edit;
        Button button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            editText_Viszonylat = itemView.findViewById(R.id.edittext_viszonylat);
            editText_Ar = itemView.findViewById(R.id.edittext_ar);
            button_delete = itemView.findViewById(R.id.button_delete);
            button_Edit = itemView.findViewById(R.id.button_edit);

        }
    }
}
