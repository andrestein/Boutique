package mundo.laboutique;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LENOVO on 14/04/2016.
 */
public class DataHelper extends SQLiteOpenHelper {

    public static final String CREATE_TABLE="CREATE TABLE 'Boutique' ('Id' INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL " +
            ", 'Descripcion' TEXT, 'Valor' DOUBLE, 'Descuento' TEXT NOT NULL  DEFAULT No, 'Fecha' TEXT, 'Nombre_Cliente' TEXT)";

    public DataHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
