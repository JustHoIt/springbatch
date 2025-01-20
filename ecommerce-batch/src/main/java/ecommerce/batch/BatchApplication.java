package ecommerce.batch;

import io.prometheus.client.exporter.PushGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
public class BatchApplication {

  public static void main(String[] args) {
    SpringApplication.run(BatchApplication.class, args);
  }

  @Bean
  public PushGateway pushGateway(
      @Value("${prometheus.pushgateway.url:localhost:9091}") String url
  ) {
    return new PushGateway(url);

  }

  @Bean
  public TaskExecutor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(128); // 기본으로 유지할 스레드 수
    executor.setMaxPoolSize(128); // 최대 스레드 수
    executor.setQueueCapacity(256); // 작업 queue 용량
    executor.setAllowCoreThreadTimeOut(true); // 타임아웃시 종료
    executor.setWaitForTasksToCompleteOnShutdown(true); // 종료시 하위 스레드 종료
    executor.setAwaitTerminationMillis(10);

    return executor;
  }
}
