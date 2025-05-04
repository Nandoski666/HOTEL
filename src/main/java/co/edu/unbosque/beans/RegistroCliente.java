package co.edu.unbosque.beans;

import java.io.Serializable;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import co.edu.unbosque.entity.Cliente;
import co.edu.unbosque.service.ClienteService;

@Named("registroCliente")
@RequestScoped
public class RegistroCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String email;
    private String password;
    private String nombre;
    private String telefono;

    // Servicio
    private ClienteService clienteService = new ClienteService();

    // Getters y setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
  
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Método para registrar un nuevo cliente (sin hashear la contraseña).
     */
    public String registrar() {
        if(clienteService.findByEmail(email) != null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "El correo ya está registrado", null));
            return null;
        }

        Cliente nuevoCliente = new Cliente(nombre, email, telefono, password);

        if (clienteService.save(nuevoCliente)) {
            return "principal.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en el registro", null));
            return null;
        }
    }
}
