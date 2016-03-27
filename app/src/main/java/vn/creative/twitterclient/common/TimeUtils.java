package vn.creative.twitterclient.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by minhtan512 on 3/28/2016.
 */
public class TimeUtils {
    private static final long MINUTE = 60;
    private static final long HOUR = 60 * MINUTE;
    private static final long DAY = 24 * HOUR;
    private static final long WEEK = 7 * DAY;

    public static String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            long gapTime = (System.currentTimeMillis() - dateMillis) / 1000L;
            if (gapTime < MINUTE) {
                relativeDate = "few secs";

            } else if (gapTime < HOUR) {
                relativeDate = gapTime / MINUTE + "min ago";

            } else if (gapTime < DAY) {
                relativeDate = gapTime / HOUR + "h ago";

            } else if (gapTime < WEEK) {
                relativeDate = gapTime / DAY + "d ago";

            } else {
                relativeDate = gapTime / WEEK + "w ago";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
}
