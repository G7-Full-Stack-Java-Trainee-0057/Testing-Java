package Vinceto.D35.SistemaClientes.vista;
import Vinceto.D35.SistemaClientes.modelo.*;
import Vinceto.D35.SistemaClientes.servicio.*;
import Vinceto.D35.SistemaClientes.utilidades.SScan;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private ClienteServicio clienteServicio = new ClienteServicio();
    private ArchivoServicio archivoServicio = new ArchivoServicio();
    private ExportadorCsv exportadorCsv = new ExportadorCsv();
    private ExportadorTxt exportadorTxt = new ExportadorTxt();
    private String fileName = "Clientes";
    private String fileName1 = "DBClientes.csv";
    private SScan scanner = new SScan();

    public void iniciarMenu() {
        List<String> opciones = new ArrayList<>();
        opciones.add("Listar Clientes");
        opciones.add("Agregar Cliente");
        opciones.add("Editar Cliente");
        opciones.add("Cargar Datos");
        opciones.add("Exportar Datos");
        opciones.add("Salir");

        boolean continuar = true;
        while (continuar) {
            Menu.mostrarMenu(opciones);
            String opcion = Menu.seleccionarOpcionMenu();
            switch (opcion.toLowerCase()) {
                case "a":
                    listarClientes();
                    break;
                case "b":
                    agregarCliente();
                    break;
                case "c":
                    editarCliente();
                    break;
                case "d":
                    cargarDatos();
                    break;
                case "e":
                    exportarDatos();
                    break;
                case "f":
                    terminarPrograma();
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    public static void mostrarMenu(List<String> opciones) {
        System.out.println("Menú de opciones:");
        for (int i = 0; i < opciones.size(); i++) {
            System.out.printf("%c. %s\n", 'a' + i, opciones.get(i));
        }
    }

    public static String seleccionarOpcionMenu() {
        SScan sscan = new SScan();
        String opcion = sscan.getString("Elige una opción: ");
        return opcion;
    }

    private void listarClientes() {
        if(clienteServicio.getListaClientes().isEmpty()){
            System.out.println();
            System.out.println("Lista de Clientes Vacia");
            System.out.println();
            return;
        }
        clienteServicio.listarClientes();
    }

    private void agregarCliente() {
        System.out.println();
        System.out.println("-------------Crear Cliente-------------");
        System.out.print("Ingrese el run del cliente: ");
        String run = (String) scanner.escanear("string");
        System.out.print("Ingrese el nombre: ");
        String nombre = (String) scanner.escanear("string");
        System.out.print("Ingrese el apellido: ");
        String apellido = (String) scanner.escanear("string");
        System.out.print("Ingrese sus años: ");
        String anios = (String) scanner.escanear("string");

        Cliente nuevoCliente = new Cliente(run, nombre, apellido, anios, CategoriaEnum.ACTIVO);
        clienteServicio.agregarCliente(nuevoCliente);
        System.out.println("Cliente agregado exitosamente.");
        System.out.println();
    }

    private void editarCliente() {
        System.out.println("-------------Editar Cliente-------------");
        System.out.println("Ingrese RUN del Cliente a editar:");
        String run = (String) scanner.escanear("string");
        Cliente cliente = clienteServicio.buscarCliente(run);
        if (cliente != null) {
            System.out.println("Seleccione qué desea hacer:");
            System.out.println("1.-Cambiar el estado del Cliente");
            System.out.println("2.-Editar los datos ingresados del Cliente");
            int opcion = (int) scanner.escanear("int");
            if (opcion == 1) {
                System.out.println("El estado actual es: " + cliente.getNombreCategoria());
                System.out.println("1.-Si desea cambiar el estado del Cliente a Inactivo");
                System.out.println("2.-Si desea mantener el estado del cliente Activo");
                int estado = (int) scanner.escanear("int");
                if (estado == 1) {
                    cliente.setNombreCategoria(CategoriaEnum.INACTIVO);
                    System.out.println("Estado del cliente cambiado a Inactivo.");
                } else {
                    System.out.println("Estado del cliente mantenido como Activo.");
                }
            } else if (opcion == 2) {
                System.out.println("1.-El RUN del Cliente es: " + cliente.getRunCliente());
                System.out.println("2.-El Nombre del Cliente es: " + cliente.getNombreCliente());
                System.out.println("3.-El Apellido del Cliente es: " + cliente.getApellidoCliente());
                System.out.println("4.-Los años como Cliente son: " + cliente.getAniosCliente());
                System.out.println("Ingrese opción a editar de los datos del cliente:");
                int dato = (int) scanner.escanear("int");
                switch (dato) {
                    case 1:
                        System.out.println("Ingrese nuevo RUN del Cliente:");
                        String nuevoRun = (String) scanner.escanear("string");
                        cliente.setRunCliente(nuevoRun);
                        break;
                    case 2:
                        System.out.println("Ingrese nuevo Nombre del Cliente:");
                        String nuevoNombre = (String) scanner.escanear("string");
                        cliente.setNombreCliente(nuevoNombre);
                        break;
                    case 3:
                        System.out.println("Ingrese nuevo Apellido del Cliente:");
                        String nuevoApellido = (String) scanner.escanear("string");
                        cliente.setApellidoCliente(nuevoApellido);
                        break;
                    case 4:
                        System.out.println("Ingrese nuevos años como Cliente:");
                        String nuevosAnios = (String) scanner.escanear("string");
                        cliente.setAniosCliente(nuevosAnios);
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
                System.out.println("Datos cambiados con éxito.");
            } else {
                System.out.println("Opción inválida.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void cargarDatos() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            System.out.println("---------Cargar Datos en Windows---------------");
        } else {
            System.out.println("---------Cargar Datos en Unix/Linux/MacOS---------------");
        }
        System.out.println("Ingresa la ruta en donde se encuentra el archivo DBClientes.csv:");
        System.out.println("La raiz del proyecto inicia en .../SistemaClientes/[la ruta]");
        String ruta = scanner.getString("Ruta del archivo: "); // Usamos getString() para leer la ruta
        System.out.println("-----------------------------------------------");
        System.out.println();

        List<Cliente> clientesCargados = archivoServicio.cargarDatos(ruta, clienteServicio);
        if (clientesCargados.isEmpty()) {
            System.out.println("No se han cargado datos.");
        }
        System.out.println();
    }

    private void exportarDatos() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            System.out.println("---------Exportar Datos en Windows---------------");
        } else {
            System.out.println("---------Exportar Datos en Unix/Linux/MacOS---------------");
        }
        System.out.println("Seleccione el formato a exportar:");
        System.out.println("1.-Formato csv");
        System.out.println("2.-Formato txt");
        int formato = (int) scanner.escanear("int");
        System.out.println("Ingrese la ruta en donde desea exportar el archivo:");
        System.out.println("La raiz del proyecto inicia en .../SistemaClientes/[la ruta]");
        String ruta = (String) scanner.escanear("string");

        Exportador exportador;
        if (formato == 1) {
            exportador = new ExportadorCsv();
        } else if (formato == 2) {
            exportador = new ExportadorTxt();
        } else {
            System.out.println("Formato inválido.");
            return;
        }

        exportador.exportar(ruta + "/" + fileName + (formato == 1 ? ".csv" : ".txt"), clienteServicio.getListaClientes());
        System.out.println("Datos de clientes exportados correctamente en formato " + (formato == 1 ? "csv" : "txt") + ".");
    }

    private void terminarPrograma() {
        System.out.println("Programa terminado.");
    }
}
