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

public class addActivity extends AppCompatActivity {

    private EditText txtDescripcion, txtValor, txtDescuento, txtFecha, txtCliente;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initComponents();
        agregar();

    }

    private void initComponents() {
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);
        txtValor = (EditText) findViewById(R.id.txtValor);
        txtDescuento = (EditText) findViewById(R.id.txtDescuento);
        txtFecha = (EditText) findViewById(R.id.txtFecha);
        txtCliente = (EditText) findViewById(R.id.txtCliente);
        btnAgregar = (Button) findViewById(R.id.btnAdd);
    }

    public void GuardarDatos() {
        String descrip = txtDescripcion.getText().toString();
        double valor = Double.parseDouble(txtValor.getText().toString());
        String descuento = txtDescuento.getText().toString();
        String fecha = txtFecha.getText().toString();
        String cliente = txtCliente.getText().toString();

        DataHelper dataHelper = new DataHelper(this, "VENTAS", null, 1);
        SQLiteDatabase db = dataHelper.getWritableDatabase();
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put("Descripcion", descrip);
            values.put("Valor", valor);
            values.put("Descuento", descuento);
            values.put("Fecha", fecha);
            values.put("Nombre_Cliente", cliente);
            long i = db.insert("Boutique", null, values);
            if (i > 0) {
                Toast.makeText(getApplicationContext(), "Se agrego con exito la informacion", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void agregar() {

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuardarDatos();
                txtValor.setText("");
                txtDescripcion.setText("");
                txtCliente.setText("");
                txtFecha.setText("");
                txtDescuento.setText("");
            }
        });
    }
}


