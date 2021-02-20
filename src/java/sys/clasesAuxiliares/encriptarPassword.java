package sys.clasesAuxiliares;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class encriptarPassword {
    public static String sha512(String cadena){
        StringBuilder sb = new StringBuilder();        
        try {
            MessageDigest md= MessageDigest.getInstance("SHA-512");
            md.update(cadena.getBytes(StandardCharsets.UTF_8));
            byte[] mb= md.digest();
            
            for(int i=0; i<mb.length;i++){
                sb.append(Integer.toString((mb[i] & 0xff)+ 0x100, 16).substring(1));                
            }            
            System.out.println("Palabra enncriptada es "+sb);
        } catch (NoSuchAlgorithmException ex) {
            /*.....*/
        }
        return sb.toString();
    }
}