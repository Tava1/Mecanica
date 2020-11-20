package mecanica.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Gustavo Santos
 */
public class ObterData {
    public static Date ObterDataHora() {
        Date dataHora = new Date();

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
            String dataHoraFormatada = dateFormat.format(dataHora);
            dataHora = dateFormat.parse(dataHoraFormatada);
        } 
        catch (Exception e) {
            return dataHora = null;
        }
        
        return dataHora;
    }
}
