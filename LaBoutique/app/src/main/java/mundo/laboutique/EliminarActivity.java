package mundo.laboutique;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarActivity extends AppCompatActivity {

    private EditText txtDescripEliminar;
    private Button btnEliminar;
    private DataHelper dataHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initComponents();
        boton();
    }

    private void initComponents(){
        txtDescripEliminar = (EditText) findViewById(R.id.txtDescripcionEliminar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
    }

    private void boton(){
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtDescripEliminar.getText().toString().equals("")){
                    String descrip= txtDescripEliminar.getText().toString();
                    dataHelper = new DataHelper(getApplicationContext(),"VENTAS",null,1);
                    db = dataHelper.getWritableDatabase();
                    db.execSQL("Delete from Boutique where Descripcion='"+descrip+"'");
                    txtDescripEliminar.setText("");
                    Toast.makeText(getApplicationContext(),"Se ha eliminado el producto con exito",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Debe escribir la descripcion del producto",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
