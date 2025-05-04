package co.edu.unbosque.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;

import java.util.Base64;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    private String telefono;

    @Column(nullable = false)
    private String clave;

    @Lob
    @Column(name = "foto_perfil")
    private byte[] foto;

    // Constructor sin parámetros requerido por JPA
    public Cliente() {
    }

    // Constructor cómodo
    public Cliente(String nombre, String email, String telefono, String clave) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.clave = clave;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    /**
     * Devuelve la foto codificada en Base64 para mostrar en <h:graphicImage>.
     */
    public String getFotoBase64() {
        if (foto != null) {
            return Base64.getEncoder().encodeToString(foto);
        }
        return null;
    }
}
