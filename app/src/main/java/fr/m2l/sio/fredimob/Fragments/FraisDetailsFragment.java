package fr.m2l.sio.fredimob.Fragments;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
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

public class FraisDetailsFragment extends Fragment{

    private TextView tv_motif, tv_trajet, tv_trajetKM, tv_trajetPeage, tv_repas, tv_heberg ;
    private SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frais_details,container,false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        pref = getActivity().getPreferences(0);

        tv_motif.setText(pref.getString(Constants.MOTIF, ""));
        tv_trajet.setText(pref.getString(Constants.TRAJET, ""));
        tv_trajetKM.setText(pref.getString(Constants.TRAJETKM, ""));
        tv_trajetPeage.setText(pref.getString(Constants.TRAJETPEAGE, ""));
        tv_repas.setText(pref.getString(Constants.REPAS, ""));
        tv_heberg.setText(pref.getString(Constants.HEBERG, ""));

    }

    private void initViews(View view){

        tv_motif = (TextView) view.findViewById(R.id.tv_motif);
        tv_trajet = (TextView) view.findViewById(R.id.tv_trajet);
        tv_trajetKM = (TextView) view.findViewById(R.id.tv_trajetKM);
        tv_trajetPeage = (TextView) view.findViewById(R.id.tv_trajetPeage);
        tv_repas = (TextView) view.findViewById(R.id.tv_repas);
        tv_heberg = (TextView) view.findViewById(R.id.tv_heberg);
    }




}