class procesado{
    public static String salidaHTML(String cant, String transaccion, String producto,String fecha){
        String s="<HTML><H1>Se ha procesado el "+transaccion+" de "+cant+" unidades de "+producto+"</H1>";
        s=s+"<p>Actualmente hay xxx unidades de este producto en el almac&eacute;n</p><br>";
        
        
        s=s+"<A HREF='index.html'>Volver al men&uacute;</A><br><A HREF='Pedidos.html'>Volver a la p&aacute;gina anterior</A></HTML>";
        
        
        
        return(s);
    }
}