package mundo.laboutique;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

public class InfoActivity extends AppCompatActivity {

    private EditText txtValorTotal,txtValorPromedio,txtMejorCliente;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initComponents();
    }

    private void initComponents(){
        txtValorTotal = (EditText)findViewById(R.id.txtValorTota);
        txtValorPromedio = (EditText)findViewById(R.id.txtValorPromedio);
        txtMejorCliente = (EditText) findViewById(R.id.txtMejorCliente);

        DataHelper dataHelper = new DataHelper(this,"VENTAS",null,1);
        SQLiteDatabase db = dataHelper.getReadableDatabase();

        cursor = db.rawQuery("Select Valor from Boutique",null);
        if(db != null){
            double i = 0;
            if(cursor.moveToFirst()){
                do{
                    i= i + cursor.getDouble(0);
                }while(cursor.moveToNext());
            }
            txtValorTotal.setText(""+i);
        }
        cursor = db.rawQuery("Select Valor from Boutique",null);
        if(db != null){
            double i = 0;
            if(cursor.moveToFirst()){
                do{
                    i= i + cursor.getDouble(0);
                }while(cursor.moveToNext());
            }
            txtValorPromedio.setText(""+(i/cursor.getCount()));
        }

        cursor = db.rawQuery("Select Nombre_Cliente from Boutique",null);
        if(db != null){
            int i = 0;
            String nombreMejor = "";
            if(cursor.moveToFirst()){
                do{
                    String nombre;
                    nombre = cursor.getString(0);
                    Cursor cursor2 = db.rawQuery("Select Nombre_Cliente from Boutique Where Nombre_Cliente ='"+nombre+"'",null);
                    if(cursor2.moveToFirst()){
                        do{
                            if (i<cursor2.getCount()){
                                i=cursor2.getCount();
                                nombreMejor =cursor2.getString(0);
                            }
                        }while (cursor2.moveToNext());
                    }

                }while(cursor.moveToNext());
            }
            txtMejorCliente.setText(nombreMejor);
        }
    }

}
