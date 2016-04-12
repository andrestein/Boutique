package mundo.boutique;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LENOVO on 12/04/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NOMBRE="Boutique.sqlite";
    private static final int DB_SCHEME_VERSION= 1;

    public DbHelper(Context context){
        super(context,DB_NOMBRE,null,DB_SCHEME_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
