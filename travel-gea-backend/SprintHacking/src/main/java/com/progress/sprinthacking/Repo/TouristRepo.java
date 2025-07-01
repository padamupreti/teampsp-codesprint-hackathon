package com.progress.sprinthacking.Repo;

import com.progress.sprinthacking.Entity.Tourist;
import com.progress.sprinthacking.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TouristRepo extends JpaRepository<Tourist, Long> {
    Optional<Tourist> findByUser(User user);
}
