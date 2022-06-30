package com.dzo.fetchdatafromapiusingretrofit;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    String url="https://jsonplaceholder.typicode.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.tv1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myInterface minterface = retrofit.create(myInterface.class);
        Call<List<myModel>> cal = minterface.fetchData();

        cal.enqueue(new Callback<List<myModel>>() {
            @Override
            public void onResponse(Call<List<myModel>> call, Response<List<myModel>> response) {


                List<myModel> data=response.body();

                for(int i=0; i<data.size();i++) {
                    tv1.append(" id -> :"+data.get(i).getId()+ "\n User id -> " + data.get(i).getUserId()+" \n Title -> : "+data.get(i).getTitle()+"\n\n\n");
            }
            }

            @Override
            public void onFailure(Call<List<myModel>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Something went Wrong", Toast.LENGTH_SHORT).show();

            }
        });


    }
}