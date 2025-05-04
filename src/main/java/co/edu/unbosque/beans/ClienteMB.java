package co.edu.unbosque.beans;

import java.io.Serializable;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import co.edu.unbosque.dao.ClienteDAO;
import co.edu.unbosque.entity.Cliente;

@Named("clienteMB")
@SessionScoped
public class ClienteMB implements Serializable {
    private static final long serialVersionUID = 1L;

    // Aquí almacenamos el cliente actualmente autenticado
    private Cliente clienteSeleccionado;

    // Nuevo campo para la foto de perfil
    private UploadedFile file;

    /**
     * Al inicializar el bean, cargamos desde sesión el cliente logueado.
     */
    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        clienteSeleccionado = (Cliente) fc.getExternalContext()
                                  .getSessionMap().get("cliente");
    }

    /**
     * Listener para la subida de la foto (modo 'advanced').
     */
    public void handleFileUpload(FileUploadEvent event) {
        this.file = event.getFile();
        FacesMessage msg = new FacesMessage(
            "Foto cargada",
            event.getFile().getFileName() + " (" + event.getFile().getSize() + " bytes)"
        );
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Actualiza los datos del cliente y refresca la sesión,
     * incluyendo la foto si se subió.
     */
    public String actualizarCliente() {
        // Si se seleccionó una foto, la guardamos en el objeto Cliente
        try {
            if (file != null && file.getSize() > 0) {
                clienteSeleccionado.setFoto(file.getContent());
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                                 "Foto", "No se pudo procesar la foto subida"));
            // seguimos con la actualización de los demás datos
        }

        ClienteDAO dao = new ClienteDAO();
        boolean exito = dao.actualizarCliente(clienteSeleccionado);

        if (exito) {
            // Sobrescribimos el cliente en sesión para reflejar cambios
            FacesContext.getCurrentInstance().getExternalContext()
                   .getSessionMap().put("cliente", clienteSeleccionado);

            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                                 "Perfil", "Datos actualizados correctamente"));
            return "/reservas/listaReservas?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                 "Perfil", "Error al actualizar los datos"));
            return null;
        }
    }

    // Getters y Setters
    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    /**
     * Devuelve la lista de todos los clientes registrados
     */
    public List<Cliente> getListaClientes() {
        ClienteDAO dao = new ClienteDAO();
        return dao.consultarTodosClientes();
    }
}
