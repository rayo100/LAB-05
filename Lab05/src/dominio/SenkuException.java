package dominio;

public class SenkuException extends Exception{

    public static final String MOVIMIENTO_INVALIDO = "El movimiento es invalido";
    public static final String COLOR_INVALIDO = "No se puede cambiar al color que eligio";
    public static final String TAMANO_INVALIDO = "El Tamaño es invalido";
    public SenkuException(String message){
        super(message);
    }
}
