package mundo.laboutique;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.widget.ExpandableListView;

public class MostrarActivity extends AppCompatActivity {

    //private ListView lista;
    private DataHelper dataHelper;
    private ExpandableListView lista;
    private Cursor c;

    SparseArray<Group> groups = new SparseArray<Group>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataHelper = new DataHelper(this,"VENTAS",null,1);
        SQLiteDatabase db=dataHelper.getReadableDatabase();
        c = db.rawQuery("SELECT Descripcion, Valor, Descuento, Fecha, Nombre_Cliente FROM Boutique",null);
        createData();
        lista = (ExpandableListView) findViewById(R.id.lista);
        MyAdapter adapter = new MyAdapter(this,groups);
        lista.setAdapter(adapter);

    }

    public void createData() {
        int i=0;
        if(c.moveToFirst()){
            do{
                Group group = new Group(c.getString(0),c.getDouble(1),c.getString(2),c.getString(3),c.getString(4));
                groups.append(i, group);
                i++;
            }while (c.moveToNext());
        }


        }

    //metod para mostrar en un listView normal
    /*public void cargar(){
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
    */
}
