package fr.m2l.sio.fredimob.Controller.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.m2l.sio.fredimob.Controller.Connection.LoadJSONTask;
import fr.m2l.sio.fredimob.R;
import fr.m2l.sio.fredimob.model.Frais;

public class FraisDetailsFragment extends Fragment implements LoadJSONTask.Listener, AdapterView.OnItemClickListener{

    private ListView mListView;
    private TextView Trajet, Km, Peage, Repas, Heberg, Motif, Cout, CreatedAt, Validate;
    private SharedPreferences pref;
    public static final String URL = "http://10.0.3.109/fredimobyle/frais.php";

    private List<HashMap<String, String>> mAndroidMapList = new ArrayList<>();


    private static final String KEY_TRAJET = "trajet";
    private static final String KEY_KM = "km";
    private static final String KEY_PEAGE = "peage";
    private static final String KEY_REPAS = "repas";
    private static final String KEY_HEBERG = "heberg";
    private static final String KEY_MOTIF = "motif";
    private static final String KEY_COUT = "cout";
    private static final String KEY_CREATEDAT ="createdAt";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frais_details, container, false);
        initViews(view);
        return view;


    }


    private void initViews(View view) {

        Trajet = (TextView) view.findViewById(R.id.Trajet);
        Km = (TextView) view.findViewById(R.id.Km);
        Peage = (TextView) view.findViewById(R.id.Peage);
        Repas = (TextView) view.findViewById(R.id.Repas);
        Heberg = (TextView) view.findViewById(R.id.Heberg);
        Motif = (TextView) view.findViewById(R.id.Motif);
        Cout = (TextView) view.findViewById(R.id.Cout);
        CreatedAt = (TextView) view.findViewById(R.id.createdAt);



        mListView = (ListView) view.findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);
        
        new LoadJSONTask(this).execute(URL);
    }

    @Override
    public void onLoaded(List<Frais> fraisList) {

        for (Frais frais : fraisList) {

            HashMap<String, String> map = new HashMap<>();
            map.put(KEY_TRAJET, frais.getTrajet());
            map.put(KEY_KM, frais.getKm() + " KM");
            map.put(KEY_PEAGE, frais.getPeage() + " €");
            map.put(KEY_REPAS, frais.getRepas() + " €");
            map.put(KEY_HEBERG, frais.getHeberg() + " €");
            map.put(KEY_MOTIF, frais.getMotif());
            map.put(KEY_COUT, frais.getCout() + " €");
            map.put(KEY_CREATEDAT, frais.getCreatedAt());


            mAndroidMapList.add(map);
        }

        loadListView();
    }

    @Override
    public void onError() {

        Snackbar.make(getView(), "Error !", Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Snackbar.make(getView(), mAndroidMapList.get(i).get(KEY_TRAJET), Snackbar.LENGTH_LONG).show();
    }


    private void loadListView() {

        ListAdapter adapter = new SimpleAdapter(getActivity(), mAndroidMapList, R.layout.list_frais,
                new String[] { KEY_TRAJET, KEY_KM, KEY_PEAGE, KEY_REPAS, KEY_HEBERG, KEY_MOTIF, KEY_COUT, KEY_CREATEDAT },
                new int[] { R.id.trajet,R.id.km, R.id.peage, R.id.repas, R.id.heberg, R.id.motif, R.id.cout, R.id.createdAt });

        mListView.setAdapter(adapter);

    }
}
