package br.com.androidpro.eventoapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ItemVideoAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final String[] ArrayAulas = getResources().getStringArray(R.array.array_aulas);


        ListView lista = (ListView) findViewById(R.id.lista);

        adaptador = new ItemVideoAdapter(this, new ArrayList<ItemVideo>());

        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, DetalheActivity.class);
                Bundle bundle = new Bundle();
                ItemVideo aula = (ItemVideo) parent.getItemAtPosition(position);
                intent.putExtra("AULA", aula);

                intent.putExtras(bundle);
                startActivity(intent);
                // teste commit

            }
        });


        new EventTask().execute();


    }

    class EventTask extends AsyncTask<Void, Void, List<ItemVideo>> {

        @Override
        protected List<ItemVideo> doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;


            try {
                URL url = new URL("http://private-d88f6f-semanadevandroid.apiary-mock.com/listar");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String linha;
                StringBuffer buffer = new StringBuffer();

                while ((linha = reader.readLine()) != null) {
                    buffer.append(linha);
                    buffer.append("\n");
                }


                return JsonUtil.fromJson(buffer.toString());


            } catch (Exception e) {
                e.printStackTrace();
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }

                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }


            return null;
        }


        protected void onPostExecute(List<ItemVideo> itemVideos) {
            adaptador.clear();
            adaptador.addAll(itemVideos);
            adaptador.notifyDataSetChanged();
        }
    }

}