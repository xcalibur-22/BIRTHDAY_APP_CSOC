package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class rituAdapter extends ArrayAdapter<Birthday> implements Filterable{
    ArrayList<Birthday> data;
    ArrayList<Birthday> backup;

    public rituAdapter(Context context, ArrayList<Birthday> userArrayList){
        super(context,R.layout.ritu_layout,userArrayList);
        this.data=userArrayList;
        backup=new ArrayList<>(userArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Birthday birthday=getItem(position);
        if(convertView == null){  convertView= LayoutInflater.from(getContext()).inflate(R.layout.ritu_layout,parent,false);}
        TextView name=convertView.findViewById(R.id.name);
        TextView bday=convertView.findViewById(R.id.birthday);
        name.setText(birthday.getName());
        bday.setText("Birthday: "+birthday.getDate()+"/"+birthday.getMonth()+"/"+birthday.getYear());
        return (convertView);
    }

    public Filter getFilter() {
        return filter;
    }
    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            Log.d("1search",charSequence.toString());
            ArrayList<Birthday> filteredData=new ArrayList<>();
            if(charSequence.toString().isEmpty()){
                filteredData.addAll(backup);
            }
            else{
                for(Birthday obj:backup){
                    if(obj.getName().toLowerCase(Locale.ROOT).contains(charSequence)){
                        filteredData.add(obj);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filteredData;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            data.clear();
            data.addAll((ArrayList<Birthday>)filterResults.values);
            notifyDataSetChanged();

        }
    };

}
