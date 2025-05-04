package co.edu.unbosque.beans;

import java.io.Serializable;
import java.util.List;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import co.edu.unbosque.entity.Cliente;
import co.edu.unbosque.service.ClienteService;

@Named("validarIngreso")
@SessionScoped
public class ValidarIngreso implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    private ClienteService clienteService = new ClienteService();
    
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        var sessionMap = context.getExternalContext().getSessionMap();

        // Admin (predefinido)
        if ("admin".equals(username) && "admin".equals(password)) {
            sessionMap.put("user", "admin");
            return "clientes/listaClientes.xhtml?faces-redirect=true";
        }

        // Cliente genérico (predefinido)
        else if ("cliente".equals(username) && "cliente".equals(password)) {
            sessionMap.put("user", "cliente");
            return "reservas/listaReservas.xhtml?faces-redirect=true";
        }

        // Clientes registrados (correo y clave)
        else {
            List<Cliente> clientes = clienteService.findAll();
            for (Cliente c : clientes) {
                if (c.getEmail().equalsIgnoreCase(username) && c.getClave().equals(password)) {
                    sessionMap.put("user", c.getNombre());
                    sessionMap.put("cliente", c); // ← ESTA LÍNEA es CLAVE
                    return "resebnxhtml?faces-redirect=true";
                }
            }
            context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Correo o contraseña incorrectos", null));
            return null;
        }
    }

    public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().invalidateSession();
        return "/principal.xhtml?faces-redirect=true"; // Asegúrate de usar ruta absoluta con slash
    }


    public String registrarme() {
        return "registroNuevoCliente.xhtml?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
