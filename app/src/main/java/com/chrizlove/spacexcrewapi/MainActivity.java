package com.chrizlove.spacexcrewapi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<CrewModel> crewList;
    RecyclerView crewRecyclerView;
    Button delete, refresh;
    private CrewViewModel crewViewModel;
    CrewAdapter crewAdapter;
    TextView defaultMsg;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crewRecyclerView = findViewById(R.id.crewRecyclerView);
        defaultMsg = findViewById(R.id.default_msg);
         crewList = new ArrayList<>();
        crewViewModel = new ViewModelProvider(this).get(CrewViewModel.class);
        delete = findViewById(R.id.delete_button);
        refresh = findViewById(R.id.refresh_button);

        //deleting all the data
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crewViewModel.delete();
                defaultMsg.setVisibility(View.VISIBLE);
            }
        });

        //refreshing the data
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetch();
            }
        });

        //initializing a empty recyclerview
        List<CrewModel> empty = new ArrayList<>();
        setUpRecyclerView(empty);

        //observing data for change and updating appropriately
        crewViewModel.getAllCrew().observe(this, crews -> {
            if(crews.size()==0)
            {
                defaultMsg.setVisibility(View.VISIBLE);
            }
            else{
                defaultMsg.setVisibility(View.INVISIBLE);
            }
           crewAdapter.updateCrew(crews);
        });
    }

    private void fetch() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spacexdata.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SpaceXAPI apicall = retrofit.create(SpaceXAPI.class);
        Call<List<CrewModel>> call = apicall.getCrew();
        call.enqueue(new Callback<List<CrewModel>>() {
            @Override
            public void onResponse(Call<List<CrewModel>> call, Response<List<CrewModel>> response) {
                defaultMsg.setVisibility(View.INVISIBLE);
                crewList = response.body();
                crewViewModel.delete();
                for(CrewModel crewModel: crewList)
                {
                    crewViewModel.insert(crewModel);
                }
                crewViewModel.getAllCrew().observe(MainActivity.this, crews -> {
                    setUpRecyclerView(crews);
                });
            }

            @Override
            public void onFailure(Call<List<CrewModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Some Error Occured",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpRecyclerView(List<CrewModel> crewList) {
        crewAdapter = new CrewAdapter(crewList);
         layoutManager = new LinearLayoutManager(this);
        crewRecyclerView.setLayoutManager(layoutManager);
        crewRecyclerView.setAdapter(crewAdapter);
    }
}