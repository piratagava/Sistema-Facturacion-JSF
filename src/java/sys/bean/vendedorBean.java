/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.bean;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sys.dao.vendedorDao;
import sys.imp.vendedorDaoImp;
import sys.model.Vendedor;

/**
 *
 * @author Hernan
 */
@ManagedBean
@ViewScoped
public class vendedorBean {

  private List<Vendedor> listaVendedores;
  private Vendedor vendedor;

  public vendedorBean() {
    vendedor = new Vendedor();
  }

  /**
   * @param listaVendedores the listaVendedores to set
   */
  public void setListaVendedores(List<Vendedor> listaVendedores) {
    this.listaVendedores = listaVendedores;
  }

  /**
   * @return the vendedor
   */
  public Vendedor getVendedor() {
    return vendedor;
  }

  /**
   * @param vendedor the vendedor to set
   */
  public void setVendedor(Vendedor vendedor) {
    this.vendedor = vendedor;
  }

  /**
   * @return the listaVendedores
   */
  public List<Vendedor> getListaVendedores() {
    vendedorDao vDao = new vendedorDaoImp();
    listaVendedores = vDao.listarVendedor();
    return listaVendedores;
  }

  public void PreparaNuevoVendedor() {
    vendedor = new Vendedor();
  }

  public void insertar() {
    StringBuilder cadenaConstruct = new StringBuilder();
    try {
      //El método getInstance () llama el algoritmo SHA-512
      MessageDigest conversion = MessageDigest.getInstance("SHA-512");
      conversion.update(getVendedor().getPasswd().getBytes(StandardCharsets.UTF_8));
      byte[] almacenaConversion = conversion.digest();
      for (int i = 0; i < almacenaConversion.length; i++) {
        cadenaConstruct.append(Integer.toString((almacenaConversion[i] & 0xff) + 0x100, 16).substring(1));
      }
      //String a=cadenaConstruct.substring(almacenaConversion.length);
      System.out.println("Palabra enncriptada es " + cadenaConstruct);
      this.vendedor.setPasswd(cadenaConstruct.toString());

      vendedorDao cDao = new vendedorDaoImp();
      cDao.crearVendedor(vendedor);
      vendedor = new Vendedor();
    } catch (NoSuchAlgorithmException ex) {
      System.err.println(ex);
    }

  }

  public void actualizar() {StringBuilder cadenaConstruct = new StringBuilder();
    try {
      //El método getInstance () llama el algoritmo SHA-512
      MessageDigest conversion = MessageDigest.getInstance("SHA-512");
      conversion.update(getVendedor().getPasswd().getBytes(StandardCharsets.UTF_8));
      byte[] almacenaConversion = conversion.digest();
      for (int i = 0; i < almacenaConversion.length; i++) {
        cadenaConstruct.append(Integer.toString((almacenaConversion[i] & 0xff) + 0x100, 16).substring(1));
      }
      //String a=cadenaConstruct.substring(almacenaConversion.length);
      System.out.println("Palabra enncriptada es " + cadenaConstruct);
      this.vendedor.setPasswd(cadenaConstruct.toString());

      vendedorDao cDao = new vendedorDaoImp();
      cDao.actualizarVendedor(vendedor);
      vendedor = new Vendedor();
    } catch (NoSuchAlgorithmException ex) {
      System.err.println(ex);
    }

  }

  public void borrar() {
    vendedorDao cDao = new vendedorDaoImp();
    cDao.borrarVendedor(vendedor);
    vendedor = new Vendedor();
  }
}
