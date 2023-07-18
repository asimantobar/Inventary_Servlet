import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class BuscarProducto extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String filePath;
    
    public void init(ServletConfig config) throws ServletException {
    super.init(config);
    System.out.println("Iniciando BuscarProductos...");
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

		// Obtener el nombre del producto buscado del formulario
		String productName = request.getParameter("ProdSearch");

		// Buscar los códigos correspondientes al producto en la base de datos
		List<String> productCodes = new ArrayList<>();
		for (Map.Entry<String, String> entry : data.entrySet()) {
			if (entry.getKey().equalsIgnoreCase(productName)) {
				productCodes.add(entry.getValue());
			}
		}

		// Crear la tabla HTML con los colores adecuados
		String htmlTable = "<table class=\"inventory\" BORDER=1 style='margin: 0 auto;'>";
		for (int i = 0; i < 4; i++) {
			htmlTable += "<tr>";
			for (int j = 0; j < 2; j++) {
				String cellColor = "white";
				for (String productCode : productCodes) {
					if (productCode != null && productCode.equals("0" + (i+1) +"0"+ (j+1))) {
						cellColor = "green";
					}
				}
				htmlTable += "<td style='background-color: " + cellColor + ";'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp</td>";
			}
			htmlTable += "</tr><tr><td style='background-color:white;' colspan =2>&nbsp;&nbsp;&nbsp;</td></tr>";
		}
		htmlTable += "</table>";

		// Output la tabla de HTML
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Mapa del Almacen</title>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
		out.println("</head>");
		out.println("<body><h1 style='text-align:center;'>El producto que desea encontrar est&aacute; ubicado en la zona verde.</h1>");
		out.println(<center>htmlTable</center>);
		out.println("<center><BR><A HREF='index.html'>Volver al men&uacute;</A></center>");
		out.println("</body>");
		out.println("</html>");
	}


}
