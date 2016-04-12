package mundo.boutique;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by LENOVO on 12/04/2016.
 */
public class DataBaseManager {

    private static final String TABLE_NAME="ventas";
    private static final String CN_ID="Id";
    private static final String CN_DESCRIPCION="descripcion";
    private static final String CN_VALOR="valor";
    private static final String CN_DESCUENTO="descuento";
    private static final String CN_FECHA="fecha";
    private static final String CN_NOMBRE_CLIENTE="nombre_cliente";

    private DbHelper helper;
    private SQLiteDatabase db;

    public static final String CREATE_TABLE= " create table "+TABLE_NAME+" ("
            +CN_ID+" integer primary key autoincrement, "
            +CN_DESCRIPCION+" text , "
            +CN_VALOR+" double not null default 0, "
            +CN_DESCUENTO+" text not null default 'No' "
            +CN_FECHA+" text, "
            +CN_NOMBRE_CLIENTE+" text)";

    public DataBaseManager(Context context){
        helper = new DbHelper(context);
        db= helper.getWritableDatabase();
    }

    public ContentValues generarValores(String descrip,double valor,
                                        String descuento,String fecha,String nombreCliente){
        ContentValues values = new ContentValues();
        values.put(CN_DESCRIPCION,descrip);
        values.put(CN_VALOR,valor);
        values.put(CN_DESCUENTO,descuento);
        values.put(CN_FECHA,fecha);
        values.put(CN_NOMBRE_CLIENTE,nombreCliente);

        return values;
    }

    public void insertar(int id,String descrip,double valor,
                    String descuento,String fecha,String nombreCliente){
        db.insert(TABLE_NAME, null, generarValores(descrip, valor, descuento, fecha, nombreCliente));
    }

    public void eliminar(String descripcion){
        db.delete(TABLE_NAME, CN_ID + " =? ", new String[]{descripcion});
    }

    public void editar(String descrip,double valor,
                       String descuento,String fecha,String nombreCliente){
        db.update(TABLE_NAME, generarValores(descrip, valor, descuento, fecha, nombreCliente), CN_DESCUENTO + " =? ", new String[]{descrip});
    }

    public void buscar(String descrip){
        db.execSQL(" Select * From " + TABLE_NAME + " where " + CN_DESCRIPCION + " =" + descrip);
    }

    public Cursor cargarCursor(){
        String[] columnas = {CN_ID,CN_DESCRIPCION,CN_VALOR,CN_DESCUENTO,CN_FECHA,CN_NOMBRE_CLIENTE};
        return db.query(TABLE_NAME,columnas,null,null,null,null,null);
    }
}
