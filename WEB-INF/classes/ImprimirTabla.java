class ImprimirTabla{
    
    public static String TablaHTML(String[][] data){
    String tabla = "<TABLE BORDER=1>";
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                tabla += "<TR>";
                for (int j = 0; j < data[i].length; j++) {
                    tabla += "<TD width=50>" + data[i][j] + "</TD>";
                }
                tabla += "</TR>";
            }    
        }
        tabla += "</TABLE>";
        return tabla;
}
}