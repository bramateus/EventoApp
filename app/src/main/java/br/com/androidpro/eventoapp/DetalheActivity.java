package br.com.androidpro.eventoapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetalheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        Intent intent = getIntent();
        final ItemVideo itemVideo = (ItemVideo) intent.getSerializableExtra("AULA");

        TextView d = (TextView) findViewById(R.id.titulo);
        d.setText(itemVideo.getTitulo());


        TextView data = (TextView) findViewById(R.id.data);
        data.setText(itemVideo.getData());

        Button botaoUrl = (Button) findViewById(R.id.botaoUrl);
        botaoUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(itemVideo.getUrl()));
                startActivity(intent);
            }
        });


        Toast.makeText(this,"Teste",Toast.LENGTH_SHORT).show();
    }
}
