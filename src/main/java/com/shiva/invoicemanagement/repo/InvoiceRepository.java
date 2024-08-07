package com.shiva.invoicemanagement.repo;

import com.shiva.invoicemanagement.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByCustomerId(Long customerId);
//    @Query("SELECT new com.shiva.invoicemanagement.dto.MonthlySalesDTO(MONTH(i.date), SUM(i.totalAmount)) FROM Invoice i GROUP BY MONTH(i.date)")
//    List<MonthlySalesDTO> getTotalSalesByMonth();
//
//    @Query("SELECT new com.shiva.invoicemanagement.dto.YearlySalesDTO(YEAR(i.date), SUM(i.totalAmount)) FROM Invoice i GROUP BY YEAR(i.date)")
//    List<YearlySalesDTO> getTotalSalesByYear();
}
