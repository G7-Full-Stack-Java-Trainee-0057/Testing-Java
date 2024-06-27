package Vinceto.D35.SistemaClientes.servicio;
import Vinceto.D35.SistemaClientes.modelo.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClienteServicio {
    private List<Cliente> listaClientes;

    public ClienteServicio() {
        this.listaClientes = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public Cliente buscarCliente(String run) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getRunCliente().equalsIgnoreCase(run)) {
                return cliente;
            }
        }
        return null;
    }

    public Cliente buscarClienteStream(String run) {
        Optional<Cliente> cliente = listaClientes.stream()
                .filter(c -> c.getRunCliente().equalsIgnoreCase(run))
                .findFirst();
        return cliente.orElse(null);
    }

    public void editarCliente(Cliente cliente) {
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getRunCliente().equalsIgnoreCase(cliente.getRunCliente())) {
                listaClientes.set(i, cliente);
                break;
            }
        }
    }

    public void editarClienteStream(Cliente cliente) {
        listaClientes = listaClientes.stream()
                .map(c -> c.getRunCliente().equalsIgnoreCase(cliente.getRunCliente()) ? cliente : c)
                .collect(Collectors.toList());
    }

    public void listarClientes() {
        for (Cliente cliente : listaClientes) {
            System.out.println(cliente);
        }
    }

    public void listarClientes2() {
        listaClientes.forEach(System.out::println);
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }
}
