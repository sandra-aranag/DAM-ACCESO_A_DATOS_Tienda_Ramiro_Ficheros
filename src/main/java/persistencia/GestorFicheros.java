package persistencia;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import modelo.Cliente;
import modelo.Producto;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class GestorFicheros {

    public static void exportarClientesTxt (Path ruta, List<Cliente> clientes) throws IOException{
        try(BufferedWriter bw = Files.newBufferedWriter(ruta, StandardCharsets.UTF_8)){

            for (Cliente c : clientes) {
                bw.write(c.getId() +";"+ c.getNombre() +";"+ c.getEmail() +";"+ c.getEmail());
                bw.newLine();
            }

        }
    }


    public static List<Cliente> importarClientesTxt(Path ruta) throws IOException {

        List<Cliente> resultado = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(ruta, StandardCharsets.UTF_8)) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(";", -1);

                if (p.length != 4) {
                    continue;
                }

                try {
                    int id = Integer.parseInt(p[0]);
                    String nombre = p[1];
                    String email = p[2];
                    String telefono = p[3];

                    Cliente cliente = new Cliente(id, nombre, email, telefono);
                    resultado.add(cliente);

                } catch (NumberFormatException e) {
                    System.err.println("Cliente incorrecto: " + linea);
                }


            }
        }

        return resultado;
    }

   /*private static List<String> parseCsv(String linea){
        //para hacer el split y que si va entre comillas, que lo identifique como un único campo

    }

    public static void exportarClientesCSV(){

    } */

    public static void exportarProductosTxt(Path ruta, List<Producto> productos) throws IOException{

        try (BufferedWriter bw = Files.newBufferedWriter(ruta, StandardCharsets.UTF_8)){

            for (Producto p : productos ) {
                bw.write(p.getId() +";"+ p.getNombre() +";+"+ p.getStock() +";"+  p.getPrecio());
                bw.newLine();
            }

        }
    }

    public static List<Producto> importarProductosTxt(Path ruta) throws  IOException{

        List<Producto> resultado = new ArrayList<>();

        try(BufferedReader br = Files.newBufferedReader(ruta, StandardCharsets.UTF_8)){
            String linea;

            while ((linea = br.readLine()) != null){

                String[] p = linea.split(";", -1);

                if (p.length != 4) {
                    continue;
                }

                try{
                    int id = Integer.parseInt(p[0]);
                    String nombre = p[1];
                    int stock = Integer.parseInt(p[2]);
                    double precio = Double.parseDouble(p[3]);

                    Producto producto = new Producto(id, nombre, stock, (int)precio);
                    resultado.add(producto);

                } catch (NumberFormatException e) {
                    System.err.println("Producto incorrecto: " + linea);
                }


            }

        }
        return resultado;

    }



}
