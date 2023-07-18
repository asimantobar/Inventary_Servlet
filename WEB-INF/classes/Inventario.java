import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class Inventario extends HttpServlet {
    String filePath;
    String existencias;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("Iniciando Inventario...");
        filePath = config.getServletContext().getRealPath("lista.txt");
        try {
        String[][] data = LeerDatos.CSVfile(filePath);
        System.out.println("File: " + filePath);
        } catch (FileNotFoundException e) {
        System.out.println("Error: " + e.getMessage());
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String cantidad = request.getParameter("cantidad").trim();
        //String transaccion = request.getParameter("transaccion");
        String producto = request.getParameter("Producto");
        String salida = salidaHTML(cantidad, /*transaccion,*/ producto);
        String[][] data = LeerDatos.CSVfile(filePath);
        
        
        
        if (data == null) {
            out.println("Error: No se pudo leer los datos del archivo.");
            return;
        }
        existencias = "0";
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                continue;
            }
            
            if (data[i][2].equals(producto)) {
                //if (transaccion.equalsIgnoreCase("Ingreso")) {
                    data[i][1] = Integer.toString(Integer.parseInt(data[i][1].trim()) + Integer.parseInt(cantidad.trim()));
                    existencias = data[i][1];
                //} else {
                    //data[i][1] = Integer.toString(Integer.parseInt(data[i][1].trim()) - Integer.parseInt(cantidad.trim()));
                    //existencias = data[i][1];
                //}
            break;
            }
            existencias = data[i][1];
        }
        for (int k = 0; k < data.length; k++) {
        System.out.println(Arrays.toString(data[k]));
        }
        Writetxt(filePath,data);
        out.println(salida + ImprimirTabla.TablaHTML(data));
    }

    public String salidaHTML(String cant,/*String transaccion,*/ String producto) {
        String s = "<HTML><H1>Se ha procesado el ingreso de " + cant + " unidades de " + producto + "</H1>";
        
        s = s + "<A HREF='index.html'>Volver al men&uacute;</A><br><A HREF='Pedidos.html'>Volver a la p&aacute;gina anterior</A>";
        s = s + "<br><h3>Inventario actual</h3>";
        return s;
    }
    
    public static void Writetxt(String filePath, String[][] data) {
        System.out.println("<p>Writing data to file: " + filePath+"</p>");
        for (int k = 0; k < data.length; k++) {
        System.out.println(Arrays.toString(data[k]));
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    writer.write(data[i][j]);
                    if (j < data[i].length - 1) {
                        writer.write(",");
                        }
                }
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error replacing data in file: " + e.getMessage());
        }
        }
    }
}
    
    