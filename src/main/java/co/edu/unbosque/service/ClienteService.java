package co.edu.unbosque.service;

import co.edu.unbosque.dao.ClienteDAO;
import co.edu.unbosque.entity.Cliente;
import java.util.List;

public class ClienteService {

    private ClienteDAO clienteDAO;

    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
    }

    /**
     * Busca un cliente por correo electrónico en la "tabla" de clientes.
     *
     * @param email correo del cliente a buscar.
     * @return el objeto Cliente si se encuentra, o null si no existe.
     */
    public Cliente findByEmail(String email) {
        List<Cliente> clientes = clienteDAO.consultarTodosClientes();
        for (Cliente cliente : clientes) {
            // Se compara sin distinguir mayúsculas para mayor robustez.
            if (cliente.getEmail().equalsIgnoreCase(email)) {
                return cliente;
            }
        }
        return null;
    }
    public List<Cliente> findAll() {
        return clienteDAO.consultarTodosClientes();
    }

    /**
     * Guarda un nuevo cliente en la "tabla" (BaseDatos).
     *
     * @param cliente el cliente a guardar.
     * @return true si se guardó correctamente, false en caso contrario.
     */
    public boolean save(Cliente cliente) {
        return clienteDAO.insertarCliente(cliente);
    }
}
