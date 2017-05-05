package fr.m2l.sio.fredimob.Controller.Connection;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {

    @POST("FrediMobyle/indexm2l.php")

    Call<ServerResponse> operation(@Body ServerRequest request);


}