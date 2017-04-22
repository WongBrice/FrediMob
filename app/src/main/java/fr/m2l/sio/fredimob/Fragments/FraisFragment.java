package fr.m2l.sio.fredimob.Fragments;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import fr.m2l.sio.fredimob.Classes.Constants;
import fr.m2l.sio.fredimob.Classes.Frais;
import fr.m2l.sio.fredimob.Classes.ServerRequest;
import fr.m2l.sio.fredimob.Classes.ServerResponse;
import fr.m2l.sio.fredimob.Classes.User;
import fr.m2l.sio.fredimob.Interface.RequestInterface;
import fr.m2l.sio.fredimob.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FraisFragment extends Fragment implements View.OnClickListener{

    private AppCompatButton btn_register;
    private EditText et_motif, et_trajet,et_trajetKM,et_trajetPeage, et_repas, et_heberg;
    private ProgressBar progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frais,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){

        btn_register = (AppCompatButton)view.findViewById(R.id.btn_register);

        et_motif = (EditText) view.findViewById(R.id.et_motif);
        et_trajet = (EditText) view.findViewById(R.id.et_trajet);
        et_trajetKM = (EditText)view.findViewById(R.id.et_trajetKM);
        et_trajetPeage = (EditText)view.findViewById(R.id.et_trajetPeage);
        et_repas = (EditText)view.findViewById(R.id.et_repas);
        et_heberg = (EditText)view.findViewById(R.id.et_heberg);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){


            case R.id.btn_register:

                String motif = et_motif.getText().toString();
                String trajet = et_trajet.getText().toString();
                String trajetKM = et_trajetKM.getText().toString();
                String trajetPeage = et_trajetPeage.getText().toString();
                String repas = et_repas.getText().toString();
                String heberg = et_heberg.getText().toString();

                if(!motif.isEmpty() && !trajet.isEmpty() && !trajetKM.isEmpty() && !trajetPeage.isEmpty() && !repas.isEmpty() && !heberg.isEmpty()) {

                    progress.setVisibility(View.VISIBLE);
                    registerFraisProcess(motif, trajet,trajetKM,trajetPeage, repas, heberg);

                } else {

                    Snackbar.make(getView(), "Veuillez remplir tous les champs", Snackbar.LENGTH_LONG).show();
                }
                break;

        }

    }

    private void registerFraisProcess(String motif, String trajet, String trajetKM, String trajetPeage, String repas, String heberg){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        Frais frais = new Frais();
        frais.setMotif(motif);
        frais.setTrajet(trajet);
        frais.setTrajetKM(trajetKM);
        frais.setTrajetPeage(trajetPeage);
        frais.setRepas(repas);
        frais.setHeberg(heberg);

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