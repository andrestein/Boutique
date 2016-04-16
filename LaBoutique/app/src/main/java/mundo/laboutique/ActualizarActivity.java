package mundo.laboutique;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActualizarActivity extends AppCompatActivity {

    private Button btnActualizarActu;
    private EditText txtDescripcionActu,txtValorActualizar,txtDescuentoActualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initComponents();
        boton();
    }

    private void initComponents(){
        btnActualizarActu = (Button)findViewById(R.id.btnActualizarActu);
        txtDescripcionActu = (EditText)findViewById(R.id.txtDescripcionActu);
        txtValorActualizar = (EditText)findViewById(R.id.txtValorActualizar);
        txtDescuentoActualizar = (EditText)findViewById(R.id.txtDescuentoActualizar);
    }

    public void actualizar(){
            String descrip = txtDescripcionActu.getText().toString();
            double valor = Double.parseDouble(txtValorActualizar.getText().toString());
            String descuento = txtDescuentoActualizar.getText().toString();

            DataHelper dataHelper = new DataHelper(this, "VENTAS", null, 1);
            SQLiteDatabase db = dataHelper.getWritableDatabase();
            if (db != null) {
                ContentValues values = new ContentValues();
                values.put("Valor", valor);
                values.put("Descuento", descuento);
                db.execSQL("UPDATE Boutique SET Valor=" + valor + ",Descuento='" + descuento + "' WHERE Descripcion ='" + descrip + "'");
                Toast.makeText(getApplicationContext(),"Se actualizaron los datos con exito",Toast.LENGTH_SHORT).show();
            }
    }

    public void boton(){
        btnActualizarActu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();
                txtDescuentoActualizar.setText("");
                txtValorActualizar.setText("");
                txtDescripcionActu.setText("");
            }
        });
    }
}
