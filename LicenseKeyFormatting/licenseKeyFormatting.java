/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//import static copartcoding.StringToInteger.convert_String_To_Number;

/**
 *
 * @author Kavya Gupta
 */
public class licenseKeyFormatting {

       public static String licenseKeyFormatting(String s, int k) 
       {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--)
            if (s.charAt(i) != '-')
                sb.append(sb.length() % (k + 1) == k ? '-' : "").append(s.charAt(i));
        return sb.reverse().toString().toUpperCase();
    }
        public static void main(String[] args) {
        
            String S="2-4A0r7-4k";
            
        System.out.println(licenseKeyFormatting(S,4));
        //System.out.println("\"4567\" == "+licenseKeyFormatting("4567"));
        //System.out.println("\"93769\" == "+licenseKeyFormatting("93769"));

    }
}
