package exam.ism.core;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class MyDate {
    public static LocalDate getDate(){
        Date insDate = new Date();
        ZoneId timeZone = ZoneId.systemDefault();
        LocalDate date = insDate.toInstant().atZone(timeZone).toLocalDate();
        return date;
    }
    
    public static boolean isInPeriod(){
        boolean valid=true;
        int val=MyDate.getDate().getMonthValue();
        if (val<9){
            valid=false;
        }
        return valid;
        
    }
}
