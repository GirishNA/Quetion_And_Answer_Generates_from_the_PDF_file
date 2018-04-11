package distractorgeneration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDate {

	public static String currentDate() {
		DateFormat formate = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
		return formate.format(new Date());
	}
}
