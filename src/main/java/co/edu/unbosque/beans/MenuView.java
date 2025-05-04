package co.edu.unbosque.beans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.entity.Cliente;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("menuView")
@SessionScoped
public class MenuView implements Serializable {

    public String nuevaReserva() {
        
        return "/reservas/nuevaReserva.xhtml?faces-redirect=true";
        
          
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "principal.xhtml?faces-redirect=true";
    }

}
