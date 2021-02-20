package sys.bean;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sys.dao.detalleFacturaDao;
import sys.imp.detalleFacturaDaoImp;
import sys.model.Detallefactura;

/**
 *
 * @author Anonymous
 */

@ManagedBean
@ViewScoped
public class DetalleBean {
  /*dEtalle*/
  private List<Detallefactura> listadetalle;
  private Detallefactura detallefactura;
  
  public DetalleBean() {
    detallefactura= new Detallefactura();
  }
  
  public Detallefactura getDetallefactura() {
    return detallefactura;
  }

  public void setDetallefactura(Detallefactura detallefactura) {
    this.detallefactura = detallefactura;
  }
  
  public List<Detallefactura> getListadetalle() {
    detalleFacturaDao dDao= new detalleFacturaDaoImp();
    listadetalle=dDao.listarDetalle();
    return listadetalle;
  }

  public void setListadetalle(List<Detallefactura> listadetalle) {
    this.listadetalle = listadetalle;
  }
  
  public void eliminardetalle() {
    detalleFacturaDao dao= new detalleFacturaDaoImp();
    dao.EliminarDetalle(detallefactura);
    detallefactura= new Detallefactura();
  }
}
