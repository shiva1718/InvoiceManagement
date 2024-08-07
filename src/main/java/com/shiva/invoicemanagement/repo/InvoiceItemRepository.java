package com.shiva.invoicemanagement.repo;

import com.shiva.invoicemanagement.entities.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {

}
