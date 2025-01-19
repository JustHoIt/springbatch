package ecommerce.batch.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public static LocalDate toLocalDate(String date) {

    return LocalDate.parse(date, formatter);
  }

}
