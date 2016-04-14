package mundo.laboutique;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MostrarActivity extends AppCompatActivity {

    private ListView lista;
    private DataHelper dataHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cargar();
    }

    public void cargar(){
        dataHelper = new DataHelper(this,"VENTAS",null,1);
        SQLiteDatabase db=dataHelper.getReadableDatabase();
        if(db != null){
            Cursor c = db.rawQuery("Select * from Boutique",null);
            int cantidad = c.getCount();
            int i=0;
            String[] arreglo = new String[cantidad];
            if(c.moveToFirst()){
                do{
                    String lineal = c.getInt(0)+"\n"+c.getString(1)+"\n"+c.getDouble(2)+"\n"+
                            c.getString(3)+"\n"+c.getString(4)+"\n"+c.getString(5);

                    arreglo[i] = lineal;
                    i++;
                }while (c.moveToNext());
            }
            ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arreglo);
            lista = (ListView) findViewById(R.id.lista);
            lista.setAdapter(adapter);
        }else{
            Toast.makeText(getApplicationContext(),"No hay datos de las ventas",Toast.LENGTH_SHORT).show();
        }
    }

}
