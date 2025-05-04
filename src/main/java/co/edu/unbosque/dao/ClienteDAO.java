package co.edu.unbosque.dao;

import co.edu.unbosque.entity.Cliente;
import co.edu.unbosque.entity.Reserva;
import co.edu.unbosque.persistence.BaseDatos;
import java.util.ArrayList;

public class ClienteDAO {
	
	public ClienteDAO() {
		
	}
	
	public boolean insertarCliente(Cliente clienteNuevo) {
		return BaseDatos.tablaCliente.add(clienteNuevo);
	}
	
	public boolean eliminarCliente(int clienteBorrar) {
	    for (int i = 0; i < BaseDatos.tablaCliente.size(); i++) {
	        Cliente est = BaseDatos.tablaCliente.get(i);
	        if (est.getId() == clienteBorrar) {
	            BaseDatos.tablaCliente.remove(i);
	            return true;
	        }
	    }
	    return false;
	}
	public ArrayList<Cliente> consultarTodosClientes(){
		return BaseDatos.tablaCliente;
	}
	  public boolean actualizarCliente(Cliente cliente) {
		    for (int i = 0; i < BaseDatos.tablaCliente.size(); i++) {
		      if (BaseDatos.tablaCliente.get(i).getId() == cliente.getId()) {
		        BaseDatos.tablaCliente.set(i, cliente);
		        return true;
		      }
		    }
		    return false;
		  }

}
