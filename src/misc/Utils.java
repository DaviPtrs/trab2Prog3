package misc;

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
                throw new Exception("Arquivo \"" + infile.getName() + "\" nao existe e/ou nao pode ser lido!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return infile;
    }
}