package ecommerce.batch.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DateTimeUtilsTest {

  @Test
  void testToLocalDate() {
    String date = "2024-09-21";

    LocalDate result = DateTimeUtils.toLocalDate(date);

    assertThat(result).isEqualTo(LocalDate.of(2024, 9, 21));
  }

  @Test
  void testToLocalDateTime() {
    String date = "2025-01-03 16:16:16.304";

    LocalDateTime result = DateTimeUtils.toLocalDateTime(date);

    assertThat(result).isEqualTo(LocalDateTime.of(2025, 1, 3, 16, 16, 16, 304000000));
  }


  @Test
  void testToString() {
    LocalDateTime dateTime = LocalDateTime.of(2025, 1, 3, 16, 16, 16, 304000000);

    String result = DateTimeUtils.toString(dateTime);

    assertThat(result).isEqualTo("2025-01-03 16:16:16.304");
  }
}