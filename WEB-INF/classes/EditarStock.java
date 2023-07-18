import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EditarStock extends HttpServlet {

    String filePath;
    String existencias;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("Iniciando EditarStock...");
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
        
        String[][] data = LeerDatos.CSVfile(filePath);
        
        if (data == null) {
            out.println("Error: No se pudo leer los datos del archivo.");
            return;
        }
        out.println("<HTML>");
        out.println("<H2> Inventario actual</H2>");
        out.println(ImprimirTabla.TablaHTML(data));
        out.println("<H3> Elige el producto para editar su inventario</H3>");
        out.println("<form action='EditarInventario' method='GET' name='Productos'>");
        out.println("<input TYPE='radio' NAME='Producto' VALUE='Shampoo'> Shampoo <br>");
        out.println("<input TYPE='radio' NAME='Producto' VALUE='Pasta de dientes'> Pasta de dientes <br>");
        out.println("<input TYPE='radio' NAME='Producto' VALUE='Microondas'> Microondas <br>");
        out.println("<input TYPE='radio' NAME='Producto' VALUE='Tostadora'> Tostadora <br>");
        out.println("<input TYPE='radio' NAME='Producto' VALUE='Amoniaco'> Amoniaco <br>");
        out.println("<input TYPE='radio' NAME='Producto' VALUE='Jabon'> Jabon <br>");
        out.println("<input TYPE='radio' NAME='Producto' VALUE='Altavoz'> Altavoz <br>");
        out.println("<input TYPE='radio' NAME='Producto' VALUE='Impresora'> Impresora <br><br>");
        out.println("Introduce la nueva cantidad: <input NAME='NuevaCantidad' size='10'><br>");
        out.println("<input TYPE='SUBMIT' VALUE='Enviar'><br>");
        out.println("<BR><A HREF='index.html'>Volver al men&uacute;</A>");
        out.println("</form>");
        out.println("</HTML>");
        }
}


        
        
        
    
        
        
        
       