package com.example.franciscoandrade.weatherapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by franciscoandrade on 12/11/17.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolderWeather> {

    List<Data> listData;

    public WeatherAdapter(List<Data> listData) {
        this.listData = listData;
    }

    @Override
    public ViewHolderWeather onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);


        return new ViewHolderWeather(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderWeather holder, int position) {


        DateFormat ddf3= DateFormat.getTimeInstance(DateFormat.SHORT, Locale.ENGLISH);
        ddf3.setTimeZone(TimeZone.getTimeZone("EST"));

        Date date2;
        long unixSeconds2;

        unixSeconds2= listData.get(position).getTime();
        date2= new Date(unixSeconds2*1000L);


        String time=ddf3.format(date2);


//        for (int i=0; i< hourly.getData().length; i++){
//
//            unixSeconds2 =response.body().getHourly().getData()[i].getTime();
//            date2= new Date(unixSeconds2*1000L);
//
//            String format4= ddf3.format(date2);
//
//            result2+=hourly.getData()[i].getTemperature()+"\u00b0C+ ---"+format4+"\n";
//
//            listData.add(response.body().getHourly().getData()[i]);
//
//        }





        holder.timeRV.setText("Time: "+time);
        holder.tempRV.setText("Temperature: "+String.valueOf(listData.get(position).getTemperature())+"\u00b0C");
        holder.feelsTempRV.setText("Feels Like: "+String.valueOf(listData.get(position).getApparentTemperature())+"\u00b0C");
        holder.summaryRV.setText(listData.get(position).getSummary()+"");
        holder.iconRV.setText("Icon: "+listData.get(position).getIcon());


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolderWeather extends RecyclerView.ViewHolder {

        TextView timeRV, tempRV, feelsTempRV, summaryRV, iconRV;



        public ViewHolderWeather(View itemView) {
            super(itemView);

            timeRV=(TextView)itemView.findViewById(R.id.timeRV);
            tempRV=(TextView)itemView.findViewById(R.id.tempRV);
            feelsTempRV=(TextView)itemView.findViewById(R.id.feelsTempRV);
            summaryRV=(TextView)itemView.findViewById(R.id.summaryRV);
            iconRV=(TextView)itemView.findViewById(R.id.iconRV);



        }
    }
}
