package IBM.Prueba;

import IBM.Prueba.Models.ProveedoresModel;
import IBM.Prueba.Repositories.ProveedoresRepository;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PruebaApplication implements CommandLineRunner {

    @Autowired
    ProveedoresRepository proveedoresRepository;

    public static void main(final String[] args) {
        SpringApplication.run(PruebaApplication.class, args);
    }

    private final ArrayList<String> resultados = new ArrayList<>(); //esta variable global sirve para la grabacion del fichero final

    @Override
    public void run(String... args) throws Exception {

        grabarDatosprueba(); //Comenzamos grabando varios registros de ejemplo

        int clienteArg = obtenerArgumentos(args); //obtenemos los argumentos de la ejecucion

        if (resultados.isEmpty()) {
            List<ProveedoresModel> proveedores = proveedoresRepository.obtenerProveedores(clienteArg); //obtenemos los proveedores por id_cliente
            listaProveedores(proveedores);
        }
        grabarFichero(); //procedemos a generar el fichero final

    }

    private int obtenerArgumentos(String[] argumentos) {

        int cliente = 0;

        if (argumentos.length > 0) {
            try {
                cliente = Integer.parseInt(argumentos[0]);
            } catch (NumberFormatException nfe) {
            }
        }
        if (cliente == 0) {
            resultados.add("NO SE HAN PASADO PARAMETROS O ESTOS NO SON VALIDOS");
        }

        return cliente;
    }

    private void listaProveedores(List<ProveedoresModel> proveedores) {

        if (!proveedores.isEmpty()) {

            resultados.add("ID_PROV.\tNOMBRE\tID_CLIENTE\tFECHA_ALTA\n");
            proveedores.forEach((ProveedoresModel provs) -> { //recorremos la lista de registros
                String prov = String.format("%s\t%s\t%s\t%s\n", provs.getId_proveedor(),
                        provs.getNombre(), provs.getId_cliente(),
                        new SimpleDateFormat("dd/MM/yyyy").format(provs.getFecha_alta()));
                resultados.add(prov);
            });
        } else {
            resultados.add("NO EXISTEN RESULTADOS PARA EL PROVEEDOR INDICADO");
        }
    }

    private void grabarFichero() {

        try (FileWriter writer = new FileWriter("Proveedores.txt")) {
            for (String linea : resultados) { //recorremos la variable para grabar el resultado de las operaciones
                writer.write(linea);
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(PruebaApplication.class.getName()).log(Level.SEVERE, "ERROR AL GRABAR DATOS EN EL FICHERO", ex);
        }
    }

    private void grabarDatosprueba() throws ParseException {

        proveedoresRepository.deleteAll();  //antes de grabar los datos de prueba limpiamos la tabla
        proveedoresRepository.save(new ProveedoresModel("Coca-Cola", new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2001"), 5));
        proveedoresRepository.save(new ProveedoresModel("Pepsi", new SimpleDateFormat("dd/MM/yyyy").parse("02/02/2002"), 5));
        proveedoresRepository.save(new ProveedoresModel("Redbull", new SimpleDateFormat("dd/MM/yyyy").parse("03/03/2003"), 6));
    }

}
