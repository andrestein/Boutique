package mundo.laboutique;

/**
 * Created by LENOVO on 14/04/2016.
 */
import java.util.ArrayList;
import java.util.List;

public class Group {

    public String string;
    public final List<String> children = new ArrayList<String>();

    public Group(String string) {
        this.string = string;
    }

    public Group(String descripcion, double valor,String descuento,String fecha,String cliente){
        this.string = descripcion;
        children.add(""+valor);
        children.add(descuento);
        children.add(fecha);
        children.add(cliente);
    }

}
