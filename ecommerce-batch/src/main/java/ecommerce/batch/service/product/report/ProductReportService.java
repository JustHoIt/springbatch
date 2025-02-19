package ecommerce.batch.service.product.report;

import ecommerce.batch.domain.product.report.BrandReportRepository;
import ecommerce.batch.domain.product.report.CategoryReportRepository;
import ecommerce.batch.domain.product.report.ManufacturerReportRepository;
import ecommerce.batch.domain.product.report.ProductStatusReportRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductReportService {

  private final BrandReportRepository brandReportRepository;
  private final CategoryReportRepository categoryReportRepository;
  private final ManufacturerReportRepository manufacturerReportRepository;
  private final ProductStatusReportRepository productStatusReportRepository;


  public Long countCategoryReport(LocalDate now) {
    return categoryReportRepository.countByStatDate(now);
  }

  public Long countManufacturerReport(LocalDate now) {
    return manufacturerReportRepository.countByStatDate(now);
  }

  public Long countProductStatusReport(LocalDate now) {
    return productStatusReportRepository.countByStatDate(now);
  }

  public Long countBrandReport(LocalDate now) {
    return brandReportRepository.countByStatDate(now);
  }

}
