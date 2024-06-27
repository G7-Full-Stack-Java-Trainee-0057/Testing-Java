package Vinceto.D35.SistemaClientes.servicio;
import Vinceto.D35.SistemaClientes.modelo.Cliente;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ExportadorCsv extends Exportador {
    private static final String BASE_PATH = "src/main/java/Vinceto/D35/SistemaClientes/";

    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {
        String filePath = BASE_PATH + fileName;

        Path path = Paths.get(filePath).getParent();

        try {
            if (path != null && !Files.exists(path)) {
                Files.createDirectories(path);
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                for (Cliente cliente : listaClientes) {
                    writer.println(cliente.getRunCliente() + "," +
                            cliente.getNombreCliente() + "," +
                            cliente.getApellidoCliente() + "," +
                            cliente.getAniosCliente() + "," +
                            cliente.getNombreCategoria());
                }
                System.out.println("Datos exportados correctamente a: " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Error al exportar a CSV: " + e.getMessage());
        }
    }
}