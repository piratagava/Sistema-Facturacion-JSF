package sys.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.awt.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import sys.dao.vendedorDao;
import sys.imp.vendedorDaoImp;
import sys.model.Vendedor;

/**
 *
 * @author Hernan
 */
@ManagedBean
@SessionScoped
public class loginBean {

  private Vendedor vendedor;
  private String nombres;
  private String password;

  public loginBean() {
    vendedor = new Vendedor();
  }

  public Vendedor getVendedor() {
    return vendedor;
  }

  public void setVendedor(Vendedor vendedor) {
    this.vendedor = vendedor;
  }

  public String getNombres() {
    return nombres;
  }

  public void setNombres(String nombres) {
    this.nombres = nombres;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void login(ActionEvent event) {
    RequestContext context = RequestContext.getCurrentInstance();
    FacesMessage message = null;
    boolean loggedIn = false;
    String ruta = "";

    vendedorDao vDao = new vendedorDaoImp();
    this.vendedor = vDao.login(this.vendedor);

    if (this.vendedor != null) {
      //para recuperar el usuario
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.vendedor.getNombres());

      loggedIn = true;
      message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", this.vendedor.getNombres());
      ruta = "/Facturacion/Views/bienvenido.xhtml";
    } else {
      loggedIn = false;
      message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de acceso", "Usuario o Password es incorrecto ");
      this.vendedor = new Vendedor();
    }

    FacesContext.getCurrentInstance().addMessage(null, message);
    context.addCallbackParam("loggedIn", loggedIn);
    context.addCallbackParam("ruta", ruta);
  }

  //Metodo para cerrar Session
  public String cerrarSesion() {
    this.nombres = null;
    this.password = null;

    HttpSession httpsession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    httpsession.invalidate(); //borra la sesion
    return "/login";
  }
}
