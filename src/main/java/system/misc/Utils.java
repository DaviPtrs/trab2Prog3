package system.misc;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
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
                throw new system.exceptions.IOException();
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return infile;
    }

    
    public static float commaFloatFromString(String source){
        String formattedString = source.replace(",", ".");
        float result = Float.parseFloat(formattedString.trim());
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

    public static ArrayList<Long> stringToLongArray(String source){
        ArrayList<Long> result = new ArrayList<Long>();
        String[] splited = source.split(",");
        for (String string : splited) {
            result.add(Long.parseLong(string.trim()));
        }
        return result;
    }

    public static HashMap<String, String> flagParser(String[] args) throws IOException{
        HashMap<String, String> result = new HashMap<String, String>();
        String argument = "";
        String content;
        boolean hasContentFlag = false;

        for(String str: args){
            if(hasContentFlag){
                if(str.charAt(0) == '-'){
                    throw new IOException();
                }else{
                    content = str;
                    hasContentFlag = false;
                    result.put(argument, content);
                }
            }else{
                if(str.charAt(0) == '-'){
                    if(str.contains("--")){
                        argument = str.replace("--", "").trim();
                        hasContentFlag = false;
                        content = "1";
                        result.put(argument, content);
                    }else{
                        argument = str.replace("-", "").trim();
                        hasContentFlag = true;
                    }
                }
            }
        }


        return result;
    }
}