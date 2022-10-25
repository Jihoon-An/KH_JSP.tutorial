package commons;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    public static Timestamp toTimestamp(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date temp_date =  sdf.parse(dateString);
            return new Timestamp(temp_date.getTime());

        }
        catch (ParseException e1){
            e1.printStackTrace();
            return null;
        }
    }

    public static String toStringDateTime(Timestamp timestamp) {
        String TimeFrm;
        TimeFrm = new SimpleDateFormat("yyyy년 MM월dd일 hh시 mm분").format(timestamp);
        return TimeFrm;
    }

    public static String toStringDate(Timestamp time){
        String date;
        Timestamp now = new Timestamp(System.currentTimeMillis());
        long diffTime = (now.getTime()- time.getTime())/60000;
        if(diffTime < 60){
            date = String.valueOf(diffTime) + "분 전";
        }
        else if(diffTime < (60*24)){
            date = String.valueOf(diffTime/60) + "시간 전";
        }
        else {
            date = new SimpleDateFormat("yyyy년 MM월dd일").format(time);
        }
        return date;
    }
}
