package Vinceto.D35.SistemaClientes.servicio;

import Vinceto.D35.SistemaClientes.modelo.Cliente;
import Vinceto.D35.SistemaClientes.modelo.CategoriaEnum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArchivoServicio {

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

            // Utilizamos try-with-resources para asegurar el cierre automático del BufferedReader
            try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 5) {
                        Cliente cliente = new Cliente(
                                data[0].trim(), // runCliente
                                data[1].trim(), // nombreCliente
                                data[2].trim(), // apellidoCliente
                                data[3].trim(), // aniosCliente
                                CategoriaEnum.valueOf(data[4].trim().toUpperCase()) // nombreCategoria
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
}