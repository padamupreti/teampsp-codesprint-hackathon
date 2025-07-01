package com.progress.sprinthacking.Repo;

import com.progress.sprinthacking.Entity.PartnerOrg;
import com.progress.sprinthacking.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartnerOrgRepo extends JpaRepository<PartnerOrg, Long> {
    Optional<PartnerOrg> findByUser(User user);
}
