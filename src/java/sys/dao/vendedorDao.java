/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.dao;

import java.util.List;
import sys.model.Vendedor;

/**
 *
 * @author Hernan
 */
public interface vendedorDao {
    public List<Vendedor> listarVendedor();
    public void crearVendedor(Vendedor vendedor);
    public void actualizarVendedor(Vendedor vendedor);
    public void borrarVendedor(Vendedor vendedor);        
    //public Cliente obtenerClientePorCodigo(Session session, Integer codCliente) throws Exception;
    public Vendedor obtenerDatosPorUsuario(Vendedor vendedor);
    public Vendedor login(Vendedor vendedor);
}
