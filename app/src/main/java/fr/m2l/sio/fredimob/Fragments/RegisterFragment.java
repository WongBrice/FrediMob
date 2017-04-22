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
import android.widget.Spinner;
import android.widget.TextView;

import fr.m2l.sio.fredimob.Classes.Constants;
import fr.m2l.sio.fredimob.Classes.ServerRequest;
import fr.m2l.sio.fredimob.Classes.ServerResponse;
import fr.m2l.sio.fredimob.Classes.User;
import fr.m2l.sio.fredimob.Interface.RequestInterface;
import fr.m2l.sio.fredimob.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterFragment extends Fragment implements View.OnClickListener{

    private AppCompatButton btn_register;
    private EditText et_firstName, et_lastName, et_birthdate, et_address, et_city, et_postalCode, et_phone, et_licence, et_email,et_password;
    private Spinner sp_ligue, sp_type;
    private TextView tv_login;
    private ProgressBar progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){

        btn_register = (AppCompatButton)view.findViewById(R.id.btn_register);
        tv_login = (TextView)view.findViewById(R.id.tv_login);
        et_firstName = (EditText)view.findViewById(R.id.et_firstName);
        et_lastName = (EditText)view.findViewById(R.id.et_lastName);
        et_birthdate = (EditText)view.findViewById(R.id.et_birthdate);
        et_address = (EditText)view.findViewById(R.id.et_address);
        et_city = (EditText)view.findViewById(R.id.et_city);
        et_postalCode = (EditText)view.findViewById(R.id.et_postalCode);
        et_phone = (EditText)view.findViewById(R.id.et_phone);
        et_licence = (EditText)view.findViewById(R.id.et_licence);
        sp_ligue = (Spinner)view.findViewById(R.id.sp_ligue);
        sp_type = (Spinner)view.findViewById(R.id.sp_type);
        et_email = (EditText)view.findViewById(R.id.et_email);
        et_password = (EditText)view.findViewById(R.id.et_password);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        btn_register.setOnClickListener(this);
        tv_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_login:
                goToLogin();
                break;

            case R.id.btn_register:

                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                if(!email.isEmpty() && !password.isEmpty()) {

                    progress.setVisibility(View.VISIBLE);
                    registerProcess(email, password);

                } else {

                    Snackbar.make(getView(), "Veuillez remplir tous les champs", Snackbar.LENGTH_LONG).show();
                }
                break;

        }

    }

    private void registerProcess(String email,String password){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.REGISTER_OPERATION);
        request.setUser(user);
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

    private void goToLogin(){

        Fragment login = new LoginFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,login);
        ft.commit();
    }
}