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
            // Navegación implícita: carpeta + nombre sin extensión
            return "clientes/listaClientes?faces-redirect=true";
        }

        // Cliente genérico (predefinido)
        else if ("cliente".equals(username) && "cliente".equals(password)) {
            sessionMap.put("user", "cliente");
            return "reservas/listaReservas?faces-redirect=true";
        }

        // Clientes registrados (correo y clave)
        else {
            List<Cliente> clientes = clienteService.findAll();
            for (Cliente c : clientes) {
                if (c.getEmail().equalsIgnoreCase(username) && c.getClave().equals(password)) {
                    sessionMap.put("user", c.getNombre());
                    sessionMap.put("cliente", c);
                    // Aquí redirigimos al listado de reservas del cliente
                    return "reservas/listaReservas?faces-redirect=true";
                }
            }
            context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Correo o contraseña incorrectos", null));
            return null;
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .invalidateSession();
        // Con slash inicial: navegación absoluta a /principal.xhtml
        return "/principal?faces-redirect=true";
    }

    public String registrarme() {
        // Página para registro de nuevo cliente
        return "registroNuevoCliente?faces-redirect=true";
    }

    // Getters y setters

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
