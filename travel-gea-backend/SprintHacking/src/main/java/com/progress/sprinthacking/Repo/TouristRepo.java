package com.progress.sprinthacking.Repo;

import com.progress.sprinthacking.Entity.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristRepo extends JpaRepository<Tourist, Long> {
}
