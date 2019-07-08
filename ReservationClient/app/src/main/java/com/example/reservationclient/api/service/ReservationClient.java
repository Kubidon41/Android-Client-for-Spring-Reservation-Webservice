package com.example.reservationclient.api.service;

import com.example.reservationclient.api.model.Reservation;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ReservationClient {

    @POST("reservation")
    Call<Reservation> createReservation(@Body Reservation reservation);
}
