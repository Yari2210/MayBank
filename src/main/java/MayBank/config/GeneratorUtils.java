package MayBank.config;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
public class GeneratorUtils {

	public static String GenerateId(String prefix, Date date, int numdigit) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");

		Random rand = new Random();
		
		String dateFormat = "";
		
		if (date != null)
			dateFormat = sdf.format(date);

		String[] arr = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i",
				"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };

		String randomkey = "";
		for (int i = 0; i < numdigit; i++) {

			int random = rand.nextInt(36);
			randomkey += arr[random];
		}

		return prefix + dateFormat + randomkey;
	}

}
