package fr.m2l.sio.fredimob.Controller.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;


import fr.m2l.sio.fredimob.Controller.Connection.Constants;
import fr.m2l.sio.fredimob.model.Frais;
import fr.m2l.sio.fredimob.Controller.Connection.ServerRequest;
import fr.m2l.sio.fredimob.Controller.Connection.ServerResponse;

import fr.m2l.sio.fredimob.Controller.Connection.RequestInterface;
import fr.m2l.sio.fredimob.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FraisFragment extends Fragment implements View.OnClickListener{

    private AppCompatButton btn_register;
    private EditText et_trajet, et_km ,et_peage, et_repas, et_heberg, et_motif, et_cout;
    private ProgressBar progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frais,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){
        btn_register = (AppCompatButton)view.findViewById(R.id.btn_register);

        et_trajet = (EditText) view.findViewById(R.id.et_trajet);
        et_km = (EditText)view.findViewById(R.id.et_km);
        et_peage = (EditText)view.findViewById(R.id.et_peage);
        et_repas = (EditText)view.findViewById(R.id.et_repas);
        et_heberg = (EditText)view.findViewById(R.id.et_heberg);
        et_motif = (EditText) view.findViewById(R.id.et_motif);
        et_cout = (EditText) view.findViewById(R.id.et_cout);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        btn_register.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {

        switch (v.getId()){


            case R.id.btn_register:

                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();



                String trajet = et_trajet.getText().toString();
                String km = et_km.getText().toString();
                String peage = et_peage.getText().toString();
                String repas = et_repas.getText().toString();
                String heberg = et_heberg.getText().toString();
                String motif = et_motif.getText().toString();
                String cout = et_cout.getText().toString();

                if(!trajet.isEmpty() && !km.isEmpty() && !peage.isEmpty() && !repas.isEmpty() && !heberg.isEmpty() && !motif.isEmpty() && !cout.isEmpty()) {

                    progress.setVisibility(View.VISIBLE);
                    registerFrais(trajet, km, peage, repas, heberg, motif, cout);

                } else {

                    Snackbar.make(getView(), "Veuillez remplir tous les champs", Snackbar.LENGTH_LONG).show();
                }
                break;

        }

    }

    private void registerFrais(String trajet, String km, String peage, String repas, String heberg, String motif, String cout){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        Frais frais = new Frais();
        frais.setTrajet(trajet);
        frais.setKm(km);
        frais.setPeage(peage);
        frais.setRepas(repas);
        frais.setHeberg(heberg);
        frais.setMotif(motif);
        frais.setCout(cout);

        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.REGISTERFRAIS_OPERATION);
        request.setFrais(frais);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();
                progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG,"Erreur");
                Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();

            }
        });
    }


}