package sys.dao;

import java.util.List;
import org.hibernate.Session;
import sys.model.Producto;

/**
 *
 * @author Hernan
 */
public interface productoDao {
    public List<Producto> listarProductos();
    public void crearProducto(Producto producto);
    public void actualizarProducto(Producto producto);
    public void borrarProducto(Producto producto); 
    
    //MEtodo utilizado en la Factura
    public Producto obtenerProductoCodBarra(Session session, String codBarra )throws Exception;
}
