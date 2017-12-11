package com.example.franciscoandrade.weatherapp;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView textTV;
    //TextView  listTV;
    TextView summaryHour;
    List<Data> listData=new ArrayList<>();


    RecyclerView recyclerView;
    WeatherAdapter weatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textTV= (TextView) findViewById(R.id.textTV);
//        //listTV= (TextView) findViewById(R.id.listTV);
//
        summaryHour= (TextView)findViewById(R.id.summaryHour);
//
//
//
        Peticion peticion=new Peticion();



        peticion.execute();


        recyclerView= (RecyclerView) findViewById(R.id.recyclerViewContainer);



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms

                Log.d("RESULTS", "AFTER THREAD ====="+listData.size());
                weatherAdapter= new WeatherAdapter(listData);

                recyclerView.setAdapter(weatherAdapter);

            }
        }, 1000);




//                Thread thread= new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//
//                List<Data> listDataRV= new ArrayList<>();
//                Log.d("RESULTS", "AFTER THREAD ====="+listData.size());
//
////                for(int i=0; i < listData.size();i++){
////
////                    listDataRV.add(listDataRV.get(i));
////                }
//
//                weatherAdapter= new WeatherAdapter(listData);
//
//
//
//            }
//        });
//
//        thread.start();

        //Log.d("RESULTS", "FINISHED THREAD ====="+listDataRV.size());

//
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

//
//
//
//
//




    }


    public  class Peticion extends AsyncTask<Void, String, Void>{

        private String result="";
        private String result2="";

        private String summaryHourText="";



        @Override
        protected Void doInBackground(Void... voids) {
            final String  url="https://api.darksky.net/forecast/1b839889901b88e5b5e02c421ab7d3ef/";

            Retrofit retrofit= new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ServiceAPI service= retrofit.create(ServiceAPI.class);

            Call<GetCurrently> response= service.getResponseGet();


            response.enqueue(new Callback<GetCurrently>() {
                @Override
                public void onResponse(Call<GetCurrently> call, Response<GetCurrently> response) {

                    long unixSeconds =response.body().getCurrently().getTime();
                    Date date= new Date(unixSeconds*1000L);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");

                    sdf.setTimeZone(TimeZone.getTimeZone("EST"));
                    String formattedDate = sdf.format(date);


                    //Shows The time 12:24:05 PM
//                    DateFormat ddf = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.ENGLISH);
//                    ddf.setTimeZone(TimeZone.getTimeZone("EST"));
//                    String format2= ddf.format(date);


                    DateFormat ddf2= DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ENGLISH);

                    ddf2.setTimeZone(TimeZone.getTimeZone("EST"));
                    String format3= ddf2.format(date);



//                    Log.d("RESULTS =====", "onResponse: "+ response.body().getCurrently().getTemperature()+"\u00b0C");
//                    Log.d("RESULTS =====", String.format("onResponse: " + formattedDate));
//                    //Log.d("RESULTS =====", String.format("onResponse: " + format2));
//                    Log.d("RESULTS =====", String.format("onResponse: " + format3));
//                    Log.d("RESULTS =====", "onResponse: Summary: "+ response.body().getCurrently().getSummary());




                    Currently currently= response.body().getCurrently();

                    result= "Time: "+format3+"\n"+
                            "Summary: "+currently.getSummary()+"\n"+
                            "Icon: "+currently.getIcon()+"\n"+
                            "Temperature: "+currently.getTemperature()+"\u00b0C\n"+
                            "Current Temperature: "+currently.getApparentTemperature()+"\u00b0C\n"+
                            "Wind Speed: "+currently.getWindSpeed()+"\n"+
                            "Visibility: "+currently.getVisibility()+"\n";

                    Hourly hourly= response.body().getHourly();

                    //result2= String.valueOf(hourly.getSummary());

                    //Log.d("RESULTS =====", "onResponse: "+ response.body().getHourly().getData()[0].getTemperature());

                    DateFormat ddf3= DateFormat.getTimeInstance(DateFormat.SHORT, Locale.ENGLISH);
                    ddf3.setTimeZone(TimeZone.getTimeZone("EST"));

                    Date date2;
                    long unixSeconds2;


                    for (int i=0; i< hourly.getData().length; i++){

                        unixSeconds2 =response.body().getHourly().getData()[i].getTime();
                        date2= new Date(unixSeconds2*1000L);

                        String format4= ddf3.format(date2);

                      //result2+=hourly.getData()[i].getTemperature()+"\u00b0C+ ---"+format4+"\n";

                      listData.add(response.body().getHourly().getData()[i]);

                    }


                    summaryHourText= response.body().getHourly().getSummary();

                        //publishProgress(result, result2);
                        publishProgress(result, summaryHourText);


                }

                @Override
                public void onFailure(Call<GetCurrently> call, Throwable t) {
                    Log.d("RESULTS =====", "FAIL+++++++");

                }
            });



            return null;
        }


        @Override
        protected void onProgressUpdate(String... values) {

            textTV.setText(values[0]);
            //listTV.setText(values[1]);

//            Log.d("RESULTS ***", ""+values[0]);
//            Log.d("RESULTS ***", ""+result);


            summaryHour.setText(values[1]);

        }

        @Override
        protected void onPostExecute(Void aVoid) {

           // Log.d("RESULTS =====", ""+listData.size());



        }
    }

}
