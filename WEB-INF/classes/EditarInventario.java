import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EditarInventario extends HttpServlet {

    String filePath;
    String existencias;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("Iniciando EditarInventario...");
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
        String cantidad = request.getParameter("NuevaCantidad").trim();
        String producto = request.getParameter("Producto");
        String[][] data = LeerDatos.CSVfile(filePath);
        
            if (data == null) {
                out.println("Error: No se pudo leer los datos del archivo.");
                return;
            }
        
            for (int i = 0; i < data.length; i++) {
                if (data[i] == null) {
                    continue;
                }
                
                if (data[i][2].equals(producto)) {
                        data[i][1] = cantidad.trim();
                break;
                }
                
            }
        
        Writetxt(filePath,data);
        out.println("<HTML>");
        out.println("<H2> El inventario se ha modificado correctamente</H2>");
        out.println("<H3> Nuevo inventario</H3>");
        out.println(ImprimirTabla.TablaHTML(data));
        out.println("<BR><A HREF='index.html'>Volver al men&uacute;</A>");
        out.println("</HTML>");
        
        }
        public static void Writetxt(String filePath, String[][] data) {
        System.out.println("<p>Writing data to file: " + filePath+"</p>");
        
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