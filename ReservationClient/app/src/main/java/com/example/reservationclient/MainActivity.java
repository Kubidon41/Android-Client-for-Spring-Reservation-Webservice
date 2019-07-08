package com.example.reservationclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reservationclient.api.model.Reservation;
import com.example.reservationclient.api.service.ReservationClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText id = (EditText) findViewById(R.id.input_id);
        final EditText name = (EditText) findViewById(R.id.input_name);
        final EditText description = (EditText) findViewById(R.id.input_description);

        Button createReservationButton = (Button) findViewById(R.id.btn_submit);
        createReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reservation reservation = new Reservation(
                        Integer.parseInt(id.getText().toString()),
                        name.getText().toString(),
                        description.getText().toString()
                );
                sendNetworkRequest(reservation);
            }
        });
    }

    private void sendNetworkRequest(Reservation reservation) {
        //create retrofit instance
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/").addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        //get client & call object for the response
        ReservationClient client = retrofit.create(ReservationClient.class);
        Call<Reservation> call = client.createReservation(reservation);

        call.enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                Toast.makeText(MainActivity.this, "Yeah", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Reservation> call, Throwable t) {
                Toast.makeText(MainActivity.this, "something went wrong :(", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
