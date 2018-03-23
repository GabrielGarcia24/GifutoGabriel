/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Certificado;

/**
 *
 * @author Gabriel
 */
public class ValidacionesCerti {
    public boolean isDigit(String input) { // metodo para caracteres
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
    
}
