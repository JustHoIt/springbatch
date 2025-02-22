package ecommerce.batch.jobconfig.product.report;

import ecommerce.batch.domain.product.report.CategoryReport;
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
public class CategoryReportFlowConfiguration {

  @Bean
  public Flow categoryReportFlow(Step categoryReportStep) {
    return new FlowBuilder<SimpleFlow>("categoryReportFlow")
        .start(categoryReportStep)
        .build();
  }

  @Bean
  public Step categoryReportStep(JobRepository jobRepository,
      PlatformTransactionManager transactionManager,
      ItemReader<CategoryReport> categoryReportReader,
      ItemWriter<CategoryReport> categoryReportWriter,
      StepExecutionListener listener) {
    return new StepBuilder("categoryReportStep", jobRepository)
        .<CategoryReport, CategoryReport>chunk(10, transactionManager)
        .allowStartIfComplete(true)
        .reader(categoryReportReader)
        .writer(categoryReportWriter)
        .listener(listener)
        .build();
  }

  @Bean
  public JpaCursorItemReader<CategoryReport> categoryReportReader(
      EntityManagerFactory entityManagerFactory) {
    return new JpaCursorItemReaderBuilder<CategoryReport>()
        .entityManagerFactory(entityManagerFactory)
        .name("categoryReportReader")
        .queryString("select new CategoryReport(p.category,"
            + "       count(p),"
            + "       avg(p.salesPrice),"
            + "       max(p.salesPrice),"
            + "       min(p.salesPrice),"
            + "       sum(p.stockQuantity),"
            + "       sum(p.salesPrice * p.stockQuantity)) "
            + "from Product p "
            + "group by p.category")
        .build();
  }

  @Bean
  public JpaItemWriter<CategoryReport> categoryReportWriter(
      EntityManagerFactory entityManagerFactory) {
    return new JpaItemWriterBuilder<CategoryReport>()
        .entityManagerFactory(entityManagerFactory)
        .usePersist(true)
        .build();
  }


}
