package mundo.laboutique;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class BuscarActivity extends AppCompatActivity {

    private Button btnBuscar;
    private EditText txtDescrip;
    private ListView listaBuscar;
    private Cursor cursor;
    private DataHelper dataHelper;
    private SparseArray<Group>grupos;
    private SQLiteDatabase db;
    private MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initComponents();
        boton();

        grupos= new SparseArray<Group>();

    }

    private void initComponents(){
        txtDescrip = (EditText)findViewById(R.id.txtDescripBuscar);
        btnBuscar = (Button)findViewById(R.id.btnBuscar);

        dataHelper = new DataHelper(this,"VENTAS",null,1);
        db = dataHelper.getReadableDatabase();
    }

    private void boton(){
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtDescrip.getText().toString().equals("")) {
                    cargar();
                    txtDescrip.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Debe ingresar la descripcion" +
                            " del producto primero", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
     public void cargar(){
         if(db != null){
             String descrip = txtDescrip.getText().toString();
             cursor = db.rawQuery("SELECT Descripcion, Valor, Descuento, Fecha, " +
                     "Nombre_Cliente FROM Boutique " +
                     "where Descripcion ='" + descrip + "'", null);
            int cantidad = cursor.getCount();
            int i=0;
            String[] arreglo = new String[cantidad];
            if(cursor.moveToFirst()){
                do{
                    String lineal = cursor.getString(0)+"\n"+cursor.getDouble(1)+"\n"+
                            cursor.getString(2)+"\n"+cursor.getString(3)+"\n"+cursor.getString(4);

                    arreglo[i] = lineal;
                    i++;
                }while (cursor.moveToNext());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arreglo);
            listaBuscar = (ListView) findViewById(R.id.listaBuscar);
            listaBuscar.setAdapter(adapter);
        }else{
            Toast.makeText(getApplicationContext(),"No se encontro el producto",Toast.LENGTH_SHORT).show();
        }
    }
}
