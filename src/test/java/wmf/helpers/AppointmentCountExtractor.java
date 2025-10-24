package wmf.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppointmentCountExtractor {
    public static int getAllAppointments(String input) {
        Pattern pattern = Pattern.compile("(\\d+)\\s*\\(");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        throw new IllegalArgumentException("All Appointments not found");
    }

    public static int getAppointmentsToday(String input) {
        Pattern pattern = Pattern.compile("\\((\\d+)\\)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        throw new IllegalArgumentException("Appointments Today not found");
    }
}
