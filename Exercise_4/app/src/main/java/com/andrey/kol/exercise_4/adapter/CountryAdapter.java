package com.andrey.kol.exercise_4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.andrey.kol.exercise_4.R;
import com.andrey.kol.exercise_4.model.Country;

import java.util.List;

/**
 * Created by feliss on 11/5/16.
 */

public class CountryAdapter extends BaseAdapter {
    private Context context;
    private List<Country> list;

    public CountryAdapter(Context context, List<Country> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Country getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        View rowView = convertView;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.row_country, null, true);
            holder = new ViewHolder();
            holder.textViewYear = (TextView) rowView.findViewById(R.id.txtYear_CountryRow);
            holder.textViewName = (TextView) rowView.findViewById(R.id.txtName_CountryRow);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Country item = getItem(position);
        holder.textViewYear.setText(item.getYear().toString());
        holder.textViewName.setText(item.getName());
        return rowView;
    }

    static class ViewHolder {
        TextView textViewYear;
        TextView textViewName;
    }
}
