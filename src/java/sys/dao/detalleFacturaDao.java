package sys.dao;

import java.util.List;
import org.hibernate.Session;
import sys.model.Detallefactura;


/**
 *
 * @author Hernan
 */
public interface detalleFacturaDao {
      //Guardar registro de la factura campos
    public boolean guardarVentaDetalleFactura(Session session,Detallefactura detallefactura) throws Exception;
    public List<Detallefactura> listarDetalle();
    
    public void EliminarDetalle(Detallefactura detallefactura);
    
}
