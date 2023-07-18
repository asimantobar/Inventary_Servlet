import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CodigoProd extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String filePath;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("Iniciando CodigoProd...");
        filePath = config.getServletContext().getRealPath("lista.txt");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Abrir el archivo CSV y leer los datos
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        Map<String, String> data = new HashMap<>();
        while ((line = br.readLine()) != null) {
            String[] fields = line.split(",");
            data.put(fields[2], fields[0]);
        }
        br.close();

        // Obtener el codigo del producto buscado del formulario
        String codigo = request.getParameter("ProdCod");

        // Buscar el nombre del producto correspondiente al codigo en la base de datos
        String prod = data.get(codigo);

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Codigo de Productos</title>");
        out.println("</head>");
        out.println("<body>");
        if (prod != null) {
            out.println("<H1 style='text-align:center;'>El nombre del producto con c&oacute;digo " + codigo + " es: " + prod + "</H1>");
        } else {
            out.println("<H1 style='text-align:center;'>No se encontr&oacute; el producto con c&oacute;digo " + codigo + "</H1>");
        }
        out.println("<center><BR><A HREF='index.html'>Volver al men&uacute;</A></center>");
		out.println("</body>");
        out.println("</html>");
    }
}
