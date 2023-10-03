package shedule;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Utils {
    public static String getTrueDateFormat(LocalDateTime ldt){

        DateTimeFormatter formmat1 = DateTimeFormatter.ofPattern("EEEE\ndd.MM.yyyy");
        String formatter = formmat1.format(ldt);
        formatter = formatter.substring(0, 1).toUpperCase() + formatter.substring(1);

        return formatter;
    }
    public static String[] initWeek()
    {
        Calendar calendar = Calendar.getInstance();
        String[] week_dates = new String[7];
        String[] week_lesson_dates = new String[3];

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE\ndd.MM.yyyy");
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        for (int i = 0; i < 7; i++)
        {
            week_dates[i] = dateFormat.format(calendar.getTime());
            week_dates[i] = week_dates[i].substring(0, 1).toUpperCase() + week_dates[i].substring(1);
            calendar.add(Calendar.DATE, 1);
        }

        week_lesson_dates[0] = week_dates[0];
        week_lesson_dates[1] = week_dates[2];
        week_lesson_dates[2] = week_dates[4];

        return week_lesson_dates;
    }
}
