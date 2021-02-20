/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.dao;

import java.util.List;
import org.hibernate.Session;
import sys.model.Factura;

public interface facturaDao {
    public Factura obtenerUltimoRegistro(Session session) throws  Exception;
    //Averiguar si la tabla factura tiene registros
    public Long obtenerTotalRegistrosEnFactura(Session session);
    
    //Guardar registro de la factura campos
    public boolean guardarVentaFactura(Session session,Factura factura) throws Exception;
    
    public List<Factura> listarFactura();
    
    public void EliminarFactura(Factura factura);
    
}
