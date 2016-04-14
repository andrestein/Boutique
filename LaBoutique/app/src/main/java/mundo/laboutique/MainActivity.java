package mundo.laboutique;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnMostrar,btnEliminar,btnBuscar,btnActualizar,btnAgregar,btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initComponets();
        botones();
    }

    public void botones(){
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MostrarActivity.class);
                startActivity(intent);
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),addActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initComponets(){
        btnMostrar = (Button)findViewById(R.id.btnMostrar);
        btnEliminar = (Button)findViewById(R.id.btnEliminar);
        btnBuscar = (Button)findViewById(R.id.btnBuscar);
        btnActualizar = (Button)findViewById(R.id.btnActualizar);
        btnAgregar = (Button)findViewById(R.id.btnAgregar);
        btnInfo = (Button)findViewById(R.id.btnInfo);

    }
}
