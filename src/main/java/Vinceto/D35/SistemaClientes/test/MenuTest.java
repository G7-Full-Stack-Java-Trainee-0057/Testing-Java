package Vinceto.D35.SistemaClientes.test;
import Vinceto.D35.SistemaClientes.modelo.CategoriaEnum;
import Vinceto.D35.SistemaClientes.modelo.Cliente;
import Vinceto.D35.SistemaClientes.servicio.ClienteServicio;

public class MenuTest {
    public static void main(String[] args) {
        ClienteServicio clienteServicio = new ClienteServicio();

        Cliente cliente1 = new Cliente("1-9", "John", "Doe", "5", CategoriaEnum.ACTIVO);
        Cliente cliente2 = new Cliente("2-7", "Jane", "Doe", "3", CategoriaEnum.INACTIVO);

        clienteServicio.agregarCliente(cliente1);
        clienteServicio.agregarCliente(cliente2);

        clienteServicio.listarClientes();
    }
}
