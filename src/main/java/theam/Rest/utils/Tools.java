/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theam.Rest.utils;

import java.util.Random;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author equipo
 */
public class Tools {
    
    public static boolean checkNull(Object obj){
        return obj == null;
    }

    public static String generateRandomText(int lenght) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = lenght;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }
    
    public static boolean isAuthorized(String userRole, String authRole){
        return userRole.equals("ADMIN") || userRole.equals(authRole);
    }
}
