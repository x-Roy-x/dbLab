package com.roi.Repository;

import com.roi.domain.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodRepository extends JpaRepository<Good, Long> {

}
