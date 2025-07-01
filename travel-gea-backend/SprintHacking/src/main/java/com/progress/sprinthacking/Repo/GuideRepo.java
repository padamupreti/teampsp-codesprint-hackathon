package com.progress.sprinthacking.Repo;

import com.progress.sprinthacking.Entity.Guide;
import com.progress.sprinthacking.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideRepo extends JpaRepository<Guide, Long> {
    User findUserById(Long id);
}
