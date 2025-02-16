package ecommerce.batch.jobconfig.product.upload;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import ecommerce.batch.jobconfig.BaseBatchIntergrationTest;
import ecommerce.batch.service.product.ProductService;
import java.io.IOException;
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

@TestPropertySource(properties = {"spring.batch.job.name=productUploadJob"})
class ProductUploadJobConfigurationTest extends BaseBatchIntergrationTest {


  @Autowired
  private ProductService productService;

  @Value("classpath:/data/test_products_for_upload.csv")
  private Resource input;


  @Test
  void testJob(@Autowired Job productUploadJob) throws Exception {
    JobParameters jobParameters = jobParameters();
    jobLauncherTestUtils.setJob(productUploadJob);

    JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

    assertAll(() -> assertThat(productService.countProducts()).isEqualTo(6),
        () -> assertJobCompleted(jobExecution));
  }


  private JobParameters jobParameters() throws IOException {
    return new JobParametersBuilder()
        .addJobParameter("inputFilePath",
            new JobParameter<>(input.getFile().getPath(), String.class, false))
        .addJobParameter("gridSize", new JobParameter<>(3, Integer.class, false))
        .toJobParameters();
  }


}