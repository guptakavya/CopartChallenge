/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import copartcoding.*;

/**
 *
 * @author Kavya Gupta
 */
public class StringToInteger {

    /**
     * @param args the command line arguments
     */
    public static int convert_String_To_Number(String numStr){
         
        char chr[] = numStr.toCharArray();
        int total = 0;

    //getting the ascii value for zero 
    int zero = (int)'0';
        for(char c:chr){
            int temp = (int)c;
            total = (total*10)+(temp-zero);
        }
        return total;
    }
     

    public static void main(String[] args) {
        
        System.out.println("\"1234\" == "+convert_String_To_Number("1234"));
        System.out.println("\"4567\" == "+convert_String_To_Number("4567"));
        System.out.println("\"93769\" == "+convert_String_To_Number("93769"));

    }
    
}
