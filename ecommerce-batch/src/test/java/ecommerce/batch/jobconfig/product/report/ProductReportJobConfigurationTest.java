package ecommerce.batch.jobconfig.product.report;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import ecommerce.batch.domain.product.Product;
import ecommerce.batch.domain.product.ProductStatus;
import ecommerce.batch.jobconfig.BaseBatchIntergrationTest;
import ecommerce.batch.service.product.ProductService;
import ecommerce.batch.service.product.report.ProductReportService;
import ecommerce.batch.util.DateTimeUtils;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = {"spring.batch.job.name=productReportJob"})
class ProductReportJobConfigurationTest extends BaseBatchIntergrationTest {

  @Autowired
  private ProductService productService;
  @Autowired
  private ProductReportService productReportService;

  @Test
  void testJob(@Autowired Job productReportJob) throws Exception {
    LocalDate now = LocalDate.now();
    saveProduct();
    jobLauncherTestUtils.setJob(productReportJob);
    JobParameters jobParameters = new JobParametersBuilder().toJobParameters();

    JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

    assertAll(
        () -> assertThat(productReportService.countCategoryReport(now)).isEqualTo(1),
        () -> assertThat(productReportService.countBrandReport(now)).isEqualTo(2),
        () -> assertThat(productReportService.countManufacturerReport(now)).isEqualTo(2),
        () -> assertThat(productReportService.countProductStatusReport(now)).isEqualTo(2),
        () -> assertJobCompleted(jobExecution)
    );
  }

  private void saveProduct() {
    productService.save(Product.of("1", 52L, "서적",
        "셔츠", LocalDate.of(2020, 04, 10),
        LocalDate.of(2024, 05, 07),
        ProductStatus.valueOf("AVAILABLE"), "아모레퍼시픽", "롯데제과", 450095, 582,
        DateTimeUtils.toLocalDateTime("2024-09-26 14:14:15.304"),
        DateTimeUtils.toLocalDateTime("2024-12-26 14:14:15.304")));

    productService.save(Product.of("2", 83L, "가전",
        "축구공", LocalDate.of(2022, 6, 1),
        LocalDate.of(2024, 7, 9),
        ProductStatus.valueOf("DISCONTINUED"), "LG", "맥도날드코리아", 194828, 326,
        DateTimeUtils.toLocalDateTime("2025-01-03 16:16:16.304"),
        DateTimeUtils.toLocalDateTime("2025-01-20 16:16:16.304")));


  }
}