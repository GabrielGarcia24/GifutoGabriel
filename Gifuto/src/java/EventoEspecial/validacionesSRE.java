/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventoEspecial;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class validacionesSRE {

    static public boolean isDigit(String input) { // metodo para caracteres
        if (input == null || input == "") {
            return false;
        } else {

            input = input.toLowerCase();
            char[] testChars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                'r', 's', 't', 'u', 'w', 'x', 'y', 'z', '~', '!', '@', '$', '%', '^', '&', '*', '(', ')', ',', '.',
                '<', '>', '?', '/', '"', '|', ' ', '-', '+'};
            for (int i = 0; i < testChars.length; i++) {
                if (input.indexOf(testChars[i]) != -1) {

                    return false;

                }
            }
            return true;
        }
    }

    static boolean isAlphabetic(String input) {
        if (input == null || input == "") {
            return false;
        } else {
            char[] testChars = { '~', '!', '@', '$', '%', '^', '&', '*',
                '(', ')', ',', '.', '<', '>', '?', '/', '"', '|'};
            for (int i = 0; i < testChars.length; i++) {
                if (input.indexOf(testChars[i]) != -1) {
                    return false;
                }
            }
            return true;
        }
    }

    static boolean compararFechasConDate(String fecha1) {
        try {

            Date dNo = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dNo);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            Date ok = calendar.getTime();
            SimpleDateFormat ftt = new SimpleDateFormat("yyyy-MM-dd");
            String fechaMinima = ftt.format(ok);

            SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fechaDate1 = formateador.parse(fecha1);
            java.util.Date fechAhora = formateador.parse(fechaMinima);

            if (fechaDate1.after(fechAhora) == true) {
                return true;
            } else {
                return false;
            }

        } catch (ParseException ex) {
            Logger.getLogger(validacionesSRE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
