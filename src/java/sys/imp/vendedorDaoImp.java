/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.imp;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sys.clasesAuxiliares.encriptarPassword;
import sys.dao.vendedorDao;
import sys.model.Vendedor;
import sys.util.HibernateUtil;

/**
 *
 * @author Hernan
 */
public class vendedorDaoImp implements vendedorDao {

  @Override
  public List<Vendedor> listarVendedor() {
    List<Vendedor> lista = null;
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction t = session.beginTransaction();
    String hql = "FROM Vendedor";

    try {
      lista = session.createQuery(hql).list();
      t.commit();
      session.close();
    } catch (Exception e) {
      t.rollback();
    }
    return lista;
  }

  @Override
  public void crearVendedor(Vendedor vendedor) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.save(vendedor);
      session.getTransaction().commit();
    } catch (HibernateException e) {
      System.out.println(e.getMessage());
      session.getTransaction().rollback();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  @Override
  public void actualizarVendedor(Vendedor vendedor) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.update(vendedor);
      session.getTransaction().commit();
    } catch (HibernateException e) {
      System.out.println(e.getMessage());
      session.getTransaction().rollback();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  @Override
  public void borrarVendedor(Vendedor vendedor) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.delete(vendedor);
      session.getTransaction().commit();
    } catch (HibernateException e) {
      System.out.println(e.getMessage());
      session.getTransaction().rollback();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  @Override
  public Vendedor obtenerDatosPorUsuario(Vendedor vendedor) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    String hql = "FROM Vendedor WHERE nombres=:nombres";
    Query q = session.createQuery(hql);
    q.setParameter("nombres", vendedor.getNombres());
    return (Vendedor) q.uniqueResult();
  }

  @Override
  public Vendedor login(Vendedor vendedor) {
    Vendedor user = this.obtenerDatosPorUsuario(vendedor);
    if (user != null) {
      System.out.println("El nombre es " + user.getNombres());
      System.out.println("El passwd es " + user.getPasswd());
      if (!user.getPasswd().equals(encriptarPassword.sha512(vendedor.getPasswd()))) {
        user = null;
      }
    }
    return user;
  }
}
