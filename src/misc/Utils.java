package misc;

import java.util.ArrayList;
import java.util.Date;
import java.io.File;
import java.text.SimpleDateFormat;

/**
 * Utils
 */
public class Utils {

    public static Date convertDate(String string) throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date formattedDate = dateFormat.parse(string);
        return formattedDate;
    }
    public static String dateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }

    public static File openFile(String path){
        File infile = null;
        try {
            infile = new File(path);
            if((!infile.exists()) || (!infile.canRead())){
                throw new Exception("Erro de I/O");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return infile;
    }

    
    public static float commaFloatFromString(String source){
        String formattedString = source.replace(",", ".");
        float result = Float.parseFloat(formattedString);
        return result;
    }

    public static String commaFloatFormat(float source){
        String result = String.valueOf(source);
        result.replace(".", ".");
        return result;
    }

    public static <T> void printObjArray(ArrayList<T> array){
        for(T obj: array){
            System.out.println(obj.toString());
        }
    }
}