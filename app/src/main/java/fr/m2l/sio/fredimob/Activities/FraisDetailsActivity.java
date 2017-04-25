package fr.m2l.sio.fredimob.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.m2l.sio.fredimob.Interface.LoadJSONTask;
import fr.m2l.sio.fredimob.R;
import fr.m2l.sio.fredimob.model.AndroidVersion;

public class FraisDetailsActivity extends AppCompatActivity implements LoadJSONTask.Listener, AdapterView.OnItemClickListener {

    private ListView mListView;
    private TextView Id, Trajet, Km, Peage, Repas, Heberg, Motif, Cout;
    public static final String URL = "http://10.0.3.109/m2lmobile/frais.php";

    private List<HashMap<String, String>> mAndroidMapList = new ArrayList<>();


    private static final String KEY_ID = "id";
    private static final String KEY_TRAJET = "trajet";
    private static final String KEY_KM = "km";
    private static final String KEY_PEAGE = "peage";
    private static final String KEY_REPAS = "repas";
    private static final String KEY_HEBERG = "heberg";
    private static final String KEY_MOTIF = "motif";
    private static final String KEY_COUT = "cout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frais_details);

        Id = (TextView) findViewById(R.id.Id);
        Trajet = (TextView) findViewById(R.id.Trajet);
        Km = (TextView) findViewById(R.id.Km);
        Peage = (TextView) findViewById(R.id.Peage);
        Repas = (TextView) findViewById(R.id.Trajet);
        Heberg = (TextView) findViewById(R.id.Trajet);
        Motif = (TextView) findViewById(R.id.Motif);
        Cout = (TextView) findViewById(R.id.Cout);

        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);
        new LoadJSONTask(this).execute(URL);
    }

    @Override
    public void onLoaded(List<AndroidVersion> androidList) {

        for (AndroidVersion android : androidList) {

            HashMap<String, String> map = new HashMap<>();
            map.put(KEY_TRAJET, android.getTrajet());
            map.put(KEY_KM, android.getKm());
            map.put(KEY_PEAGE, android.getPeage());
            map.put(KEY_REPAS, android.getRepas());
            map.put(KEY_HEBERG, android.getHeberg());
            map.put(KEY_MOTIF, android.getMotif());
            map.put(KEY_COUT, android.getCout());

            mAndroidMapList.add(map);
        }

        loadListView();
    }

    @Override
    public void onError() {

        Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(this, mAndroidMapList.get(i).get(KEY_TRAJET),Toast.LENGTH_LONG).show();
    }

    private void loadListView() {

        ListAdapter adapter = new SimpleAdapter(FraisDetailsActivity.this, mAndroidMapList, R.layout.list_frais,
                new String[] { KEY_TRAJET, KEY_KM, KEY_PEAGE, KEY_REPAS, KEY_HEBERG, KEY_MOTIF, KEY_TRAJET },
                new int[] { R.id.trajet,R.id.km, R.id.peage, R.id.repas, R.id.heberg, R.id.motif, R.id.cout });

        mListView.setAdapter(adapter);

    }
}

