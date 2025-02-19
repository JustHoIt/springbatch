package ecommerce.batch.jobconfig.product.report;

import ecommerce.batch.domain.product.report.ManufacturerReport;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ManufacturerReportFlowConfiguration {

  @Bean
  public Flow manufacturerReportFlow(Step manufacturerReportStep) {
    return new FlowBuilder<SimpleFlow>("manufacturerReportFlow")
        .start(manufacturerReportStep)
        .build();
  }

  @Bean
  public Step manufacturerReportStep(JobRepository jobRepository,
      PlatformTransactionManager transactionManager,
      ItemReader<ManufacturerReport> manufacturerReportReader,
      ItemWriter<ManufacturerReport> manufacturerReportWriter,
      StepExecutionListener listener) {
    return new StepBuilder("manufacturerReportStep", jobRepository)
        .<ManufacturerReport, ManufacturerReport>chunk(10, transactionManager)
        .allowStartIfComplete(true)
        .reader(manufacturerReportReader)
        .writer(manufacturerReportWriter)
        .listener(listener)
        .build();
  }

  @Bean
  public JpaCursorItemReader<ManufacturerReport> manufacturerReportReader(
      EntityManagerFactory entityManagerFactory) {
    return new JpaCursorItemReaderBuilder<ManufacturerReport>()
        .entityManagerFactory(entityManagerFactory)
        .name("manufacturerReportReader")
        .queryString("select new ManufacturerReport(p.manufacturer,"
            + "       count(p),"
            + "       avg(p.salesPrice),"
            + "       sum(p.salesPrice * p.stockQuantity)) "
            + "from Product p "
            + "group by p.manufacturer")
        .build();
  }

  @Bean
  public JpaItemWriter<ManufacturerReport> manufacturerReportWriter(
      EntityManagerFactory entityManagerFactory) {
    return new JpaItemWriterBuilder<ManufacturerReport>()
        .entityManagerFactory(entityManagerFactory)
        .usePersist(true)
        .build();
  }


}
