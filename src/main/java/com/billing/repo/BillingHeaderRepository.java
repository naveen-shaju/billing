package com.billing.repo;

import com.billing.entity.BillingHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingHeaderRepository extends JpaRepository<BillingHeader,Long> {
}
