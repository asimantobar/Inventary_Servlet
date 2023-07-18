import java.io.*;
import java.util.*;

class LeerDatos {  
    private static final int ROWS =9;
    private static final int COLS =4;
    
    public static String[][] CSVfile(String filePath)throws FileNotFoundException {
        int row=0;
        String[][] data = new String[ROWS][COLS]; 
        
        try{
            BufferedReader reader= new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine())!= null){
                String[] parts = line.split(",");
                for(int col=0;col<COLS; col++){
                    data[row][col]=parts[col];
                }
                row++;
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        System.out.println(Arrays.deepToString(data));
        return data;
    }
}

/*import java.io.*;
import java.util.*;

class LeerDatos {  
    private static final int ROWS =9;
    private static final int COLS =4;
    
    public static String[][] CSVfile(String filePath)throws FileNotFoundException {
        
        String[][] data = new String[ROWS][COLS]; 
        File doc = new File(filePath);
        Scanner sc = new Scanner(doc);
        
        while(sc.hasNextLine()){
            
            for (int i=0; i<data.length;i++){
                String[] line = sc.nextLine().trim().split(","+" ");
                for (int j=0;j<line.length;j++){
                    data[i][j]=line[j];
                }
                
            }
        }
        sc.close();
        
        for(int i=0; i<ROWS; i++){
            for(int j=0; j<COLS; j++){
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        
        //System.out.println(Arrays.deepToString(data));
        return data;
    }
}*/



/*import java.io.*;
import java.util.*;

public class LeerDatos {  

    public static String[][] CSVfile(String filePath) {
        String line;
        String[][] data = null; 
        
        try {
            // Leer datos de documento csv
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder sb = new StringBuilder();
            line = reader.readLine();
            
            while (line != null){
                sb.append(line).append("\n");
                line = reader.readLine(); // leer siguiente linea
            }
            reader.close();

            // Ordenar datos en filas y columnas
            String[] rows = sb.toString().split("\n");
            data = new String[rows.length][];
            
            String[] headers = rows[0].split(",");
			for (int i = 1; i < rows.length; i++) {
				String[] values = rows[i].split(",");
				data[i - 1] = new HashMap<String, String>();
				for (int j = 0; j < headers.length; j++) {
					data[i - 1].put(headers[j], values[j]);
				}
			}
            
        } catch (IOException e) {
            System.out.println("Error accediendo a la base de datos");
        }
        System.out.println("funciona");

        return data;
    }
}
*/




