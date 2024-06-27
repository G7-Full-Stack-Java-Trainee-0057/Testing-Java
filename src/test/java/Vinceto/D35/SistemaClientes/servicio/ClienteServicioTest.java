package Vinceto.D35.SistemaClientes.servicio;

import Vinceto.D35.SistemaClientes.modelo.Cliente;
import Vinceto.D35.SistemaClientes.modelo.CategoriaEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteServicioTest {

    private ClienteServicio clienteServicio;

    @BeforeEach
    public void setUp() {
        clienteServicio = new ClienteServicio();
    }

    @Test
    public void agregarClienteTest() {
        Cliente cliente = new Cliente("12345678-9", "Juan", "Perez", "5", CategoriaEnum.ACTIVO);
        clienteServicio.agregarCliente(cliente);

        assertEquals(1, clienteServicio.getListaClientes().size());
        assertEquals(cliente, clienteServicio.getListaClientes().get(0));
    }

    @Test
    public void agregarClienteNullTest() {
        clienteServicio.agregarCliente(null);

        assertEquals(1, clienteServicio.getListaClientes().size());
        assertNull(clienteServicio.getListaClientes().get(0));
    }
}
