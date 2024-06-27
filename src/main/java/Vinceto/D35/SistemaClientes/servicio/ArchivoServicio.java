package Vinceto.D35.SistemaClientes.servicio;

import Vinceto.D35.SistemaClientes.modelo.Cliente;
import Vinceto.D35.SistemaClientes.modelo.CategoriaEnum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArchivoServicio extends Exportador {

    private static final String BASE_PATH = "src/main/java/Vinceto/D35/SistemaClientes/";

    public List<Cliente> cargarDatos(String fileName, ClienteServicio clienteServicio) {
        List<Cliente> listaClientes = new ArrayList<>();
        Path filePath = Paths.get(BASE_PATH, fileName);

        System.out.println("Intentando cargar datos desde: " + filePath);
        System.out.println();
        try {
            if (!Files.exists(filePath)) {
                System.err.println("El archivo no existe: " + filePath);
                System.out.println();
                return listaClientes; // Retorna lista vacía si el archivo no existe
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 5) {
                        Cliente cliente = new Cliente(
                                data[0].trim(),
                                data[1].trim(),
                                data[2].trim(),
                                data[3].trim(),
                                CategoriaEnum.valueOf(data[4].trim().toUpperCase())
                        );
                        listaClientes.add(cliente);
                        clienteServicio.agregarCliente(cliente); // Agregar cliente al servicio
                    } else {
                        System.err.println("Datos inválidos en la línea: " + line);
                    }
                }
                System.out.println("Datos cargados correctamente desde: " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar datos desde CSV: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error al convertir datos: " + e.getMessage());
        }
        return listaClientes;
    }

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