package com.andrey.kol.exercise_4.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrey.kol.exercise_4.R;
import com.andrey.kol.exercise_4.model.Country;

import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountryViewHolder> {
    private Context context;
    private List<Country> list;
    private ItemActionListener itemActionListener;

    public CountriesAdapter(Context context, List<Country> list, ItemActionListener listener) {
        this.context = context;
        this.list = list;
        this.itemActionListener = listener;
    }

    public Country getItem(int i) {
        return list.get(i);
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public CountriesAdapter.CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_country, null, true);
        return new CountryViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(CountriesAdapter.CountryViewHolder holder, int position) {
        holder.textViewYear.setText(String.valueOf(list.get(position).getYear()));
        holder.textViewName.setText(list.get(position).getName());
        final Country item = list.get(position);
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemActionListener.onDelete(item);
            }
        });
        holder.imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                itemActionListener.onEdit(item);
            }
        });

    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder {

        TextView textViewYear;
        TextView textViewName;
        ImageView imageViewEdit;
        ImageView imageViewDelete;
        CardView cv;

        public CountryViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.recyclerView_MainActivity);
            textViewYear = (TextView) itemView.findViewById(R.id.txtYear_CountryRow);
            textViewName = (TextView) itemView.findViewById(R.id.txtName_CountryRow);
            imageViewEdit = (ImageView) itemView.findViewById(R.id.imageViewEdit);
            imageViewDelete = (ImageView) itemView.findViewById(R.id.imageViewDelete);
        }
    }

    public interface ItemActionListener {
        void onEdit (Country item);
        void onDelete (Country item);
    }
}
