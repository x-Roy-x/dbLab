package com.roi.Repository;

import com.roi.domain.PasswordOfShopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordOfShopperRepository extends JpaRepository<PasswordOfShopper, Long> {

}
