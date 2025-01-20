package ecommerce.batch.jobconfig.product.download;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import ecommerce.batch.domain.product.Product;
import ecommerce.batch.jobconfig.BaseBatchIntergrationTest;
import ecommerce.batch.service.product.ProductService;
import ecommerce.batch.util.DateTimeUtils;
import ecommerce.batch.util.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.TestPropertySource;


@TestPropertySource(properties = {"spring.batch.job.name=productDownloadJob"})
class ProductDownloadJobConfigurationTest extends BaseBatchIntergrationTest {

  @Value("classpath:/data/products_downloaded_expected.csv")
  private Resource expectedResource;

  private File outputfile;

  @Autowired
  private ProductService productService;

  @Test
  void testJob(@Autowired Job productDownloadJob) throws Exception {
    saveProduct();
    outputfile = FileUtils.createTempFile("product_downloaded", ".csv");

    JobParameters jobParameters = jobParameters();
    jobLauncherTestUtils.setJob(productDownloadJob);

    JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

    assertAll(
        () -> assertThat(Files.readString(Path.of(outputfile.getPath()))).isEqualTo(
            Files.readString(Path.of(expectedResource.getFile().getPath()))),
        () -> assertJobCompleted(jobExecution));

  }

  private void saveProduct() {
    productService.save(Product.of("1", 52L, "서적",
        "셔츠", LocalDate.of(2020,04,10),
        LocalDate.of(2024,05,07),
        "AVAILABLE", "아모레퍼시픽", "롯데제과", 450095, 582,
        DateTimeUtils.toLocalDateTime("2024-09-26 14:14:15.304"),
        DateTimeUtils.toLocalDateTime("2024-12-26 14:14:15.304")));

    productService.save(Product.of("2", 83L, "가전",
        "축구공", LocalDate.of(2022,6,1),
        LocalDate.of(2024,7,9),
        "DISCONTINUED", "LG", "맥도날드코리아", 194828, 326,
        DateTimeUtils.toLocalDateTime("2025-01-03 16:16:16.304"),
        DateTimeUtils.toLocalDateTime("2025-01-20 16:16:16.304")));


  }


  private JobParameters jobParameters() throws IOException {
    return new JobParametersBuilder()
        .addJobParameter("outputFilePath",
            new JobParameter<>(outputfile.getPath(), String.class, false))
        .toJobParameters();
  }


}