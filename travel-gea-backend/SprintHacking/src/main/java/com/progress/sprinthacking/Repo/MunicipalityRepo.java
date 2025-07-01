package com.progress.sprinthacking.Repo;

import com.progress.sprinthacking.Entity.Municipality;
import com.progress.sprinthacking.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MunicipalityRepo  extends JpaRepository<Municipality, Long> {
    Optional<Municipality> findByUser(User user);
}
