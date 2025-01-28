package ecommerce.batch.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class ReflectionUtilsTest {

  private static class TesClass {

    private String stringField;
    private int intField;
    public static final String CONSTANT = "constant";
  }

  @Test
  void testGetFieldNames() {
    List<String> fieldNames = ReflectionUtils.getFieldNames(TesClass.class);

    assertThat(fieldNames).hasSize(2)
        .containsExactly("stringField", "intField")
        .doesNotContain("CONSTANT");
  }

}