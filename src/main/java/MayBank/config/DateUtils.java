package MayBank.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils extends BaseObject {

    private static Logger log = LoggerFactory.getLogger(DateUtils.class);
    private static final long serialVersionUID = -8818380189450844555L;

    public static final String DEFAULT_FORMAT = "yyyy-MM-dd";
    public static final String FORMAT_DATEMONTH = "ddMMM";
    public static final String FORMAT_HOURMINUTE = "HHmm";

    public static String toDateString(Date date) {
        return toDateString(DEFAULT_FORMAT, date);
    }

    public static String toDateString(String dateFormat, Date date) {
        return toDateString(new SimpleDateFormat(dateFormat), date);
    }

    public static String toDateString(DateFormat dateFormat, Date date) {
        return dateFormat.format(date);
    }

    public static Date toDate(String dateString) throws ParseException {
        return toDate(DEFAULT_FORMAT, dateString);
    }

    public static Date toDate(String dateFormat, String dateString) throws ParseException {
        return toDate(new SimpleDateFormat(dateFormat), dateString);
    }

    public static Date toDate(DateFormat dateFormat, String dateString) throws ParseException {
        return dateFormat.parse(dateString);
    }

    public static Calendar getCalendar(TimeZone zone) {
        return Calendar.getInstance(zone);
    }

    public static Date now(TimeZone zone) {
        return getCalendar(zone).getTime();
    }

    public static Date today(TimeZone zone) {
        return getCalendar(zone).getTime();
    }

    public static int differences(Date date, int type) {
        return differences(new Date(), date, type);
    }

    public static int differences(Date dateOne, Date dateTwo, int type) {
        Calendar calendarOne = Calendar.getInstance();
        calendarOne.setTime(dateOne);

        Calendar calendarTwo = Calendar.getInstance();
        calendarTwo.setTime(dateTwo);

        long delta = calendarOne.getTimeInMillis()
                - calendarTwo.getTimeInMillis();
        int differences = (int) delta / (86400 * 1000); // secondInDay *
        // millisInSecond
        switch (type) {
            case Calendar.MONTH:
                differences = differences / 30;
                break;
            case Calendar.YEAR:
                differences = differences / 365;
                break;
            default:
                differences = differences / 30;
                break;
        }
        return differences;
    }

    public static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    public static Date now() {
        return getCalendar().getTime();
    }

    public static Date today() {
        return now();
    }

    public static Date add(int add) {
        return add(today(), add);
    }

    public static Date add(Date date, int add) {
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, add);
        return calendar.getTime();
    }

    public static Date add(Date date, int type, int add) {
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        calendar.add(type, add);
        return calendar.getTime();
    }

    public static String toDDMMM(Date date) {
        return new SimpleDateFormat(FORMAT_DATEMONTH).format(date);
    }

    public static String toHHmm(Date date) {
        return new SimpleDateFormat(FORMAT_HOURMINUTE).format(date);
    }

    public static String convertDateStringToDateString(String dateFormatFrom,
                                                       String dateFormatResult,
                                                       String date) {
        String dateResult = null;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormatFrom);
        try {
            Date dateConv = sdf.parse(date);
            sdf = new SimpleDateFormat(dateFormatResult);
            dateResult = sdf.format(dateConv);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return dateResult;
    }

    public static long startTime() {
        return System.currentTimeMillis();
    }

    public static long executionTime(long startTime) {
        return System.currentTimeMillis() - startTime;
    }

}
