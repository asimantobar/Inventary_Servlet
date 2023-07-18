import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CantidadesProductos extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String filePath;
    String[][] data;
	

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("Iniciando BuscarProductos...");
        filePath = config.getServletContext().getRealPath("lista.txt");
		System.out.println("File: " + filePath);
    }


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el nombre del producto buscado del formulario
        
		String min = request.getParameter("minimo");
		data = LeerDatos.CSVfile(filePath);
		
		String[] colores = new String[8];
		String[] cantidades = new String[8];
		
		for (int i=0; i<8; i++){
			cantidades[i]=data[i+1][1];
		}
		
		for (int j=0; j<cantidades.length; j++){
			if (Integer.parseInt(cantidades[j]) < Integer.parseInt(min)){
				colores[j]="red";
			}else{
				colores[j]="green";
			}
		}
		int k =0;
		
		String htmlTable = "<table class=\"inventory\" BORDER=1 style='margin: 0 auto;'>";
		for (int i = 0; i < 8; i++) {
			if (i % 2 == 0) { // Fila Par
				htmlTable += "<tr><td style='background-color:white;' colspan='2'>&nbsp;&nbsp;&nbsp;</td></tr>";
			} else { // Fila Impar
				htmlTable += "<tr>";
				
					for (int j = 0; j < 2; j++) {
						htmlTable += "<td style='background-color: " + colores[k] + ";'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+cantidades[k]+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>";
						k=k+1;
					}
				
			}
				htmlTable += "</tr>";
		}
		
		htmlTable += "</table>";
		
		System.out.println(Arrays.deepToString(colores));
		System.out.println(Arrays.deepToString(cantidades));
		
		// Print la tabla de HTML
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Mapa del Almacen</title>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
        out.println("</head>");
        out.println("<body><h1 style='text-align:center;'>Los productos ubicados en las zonas en rojo no tienen suficiente stock.</h1>");
        out.println(<center>htmlTable</center>);
		out.println("<center><BR><A HREF='index.html'>Volver al men&uacute;</A></center>");
        out.println("</body>");
        out.println("</html>");
	}
}









/*     public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el nombre del producto buscado del formulario
        
		String min = request.getParameter("minimo");

        // Abrir el archivo CSV y leer los datos
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line;
		Map<String, String> data = new HashMap<>();
		while ((line = br.readLine()) != null) {
			String[] fields = line.split(",");
			data.put(fields[2], fields[1]);
		}
		br.close();

		// Buscar los códigos correspondientes al producto en la base de datos
		List<String> CantProd = new ArrayList<>();
		for (Map.Entry<String, String> entry : data.entrySet()) {
			if (entry.getKey().equalsIgnoreCase(min)) {
				CantProd.add(entry.getValue());
			}
		}

		// Crear la tabla HTML con los colores adecuados
		String htmlTable = "<table class=\"inventory\" BORDER=1 style='margin: 0 auto;'>";
		for (int i = 0; i < 8; i++) {
			if (i % 2 == 0) { // Fila Par
				htmlTable += "<tr><td style='background-color:white;' colspan='2'>&nbsp;&nbsp;&nbsp;</td></tr>";

			} else { // Fila Impar
				htmlTable += "<tr>";
				for (int j = 0; j < 2; j++) {
					String code = "0" + i + "0" + (j + 1);
					String cellColor = "white";
					if (data.containsKey(code)) {
						int quantity = Integer.parseInt(data.get(code).trim().split(",")[1]);
						if (quantity >= Integer.parseInt(min)) {
							cellColor = "green";
						} else {
							cellColor = "red";
						}
					}
					htmlTable += "<td style='background-color: " + cellColor + ";'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>";
				}
				htmlTable += "</tr>";
			}
		}
		htmlTable += "</table>";

		// Print la tabla de HTML
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Mapa del Almacen</title>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
		out.println("</head>");
		out.println("<body><h1 style='text-align:center;'>El producto que desea encontrar est&aacute; ubicado en la zona verde o roja.</h1>");
		out.println(htmlTable);
		out.println("</body>");
		out.println("</html>");
    }
}  */