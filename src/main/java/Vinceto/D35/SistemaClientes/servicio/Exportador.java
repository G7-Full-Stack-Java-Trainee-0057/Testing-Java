package Vinceto.D35.SistemaClientes.servicio;
import Vinceto.D35.SistemaClientes.modelo.Cliente;
import java.util.List;

public abstract class Exportador {
    public abstract void exportar(String fileName, List<Cliente> listaClientes);
}
