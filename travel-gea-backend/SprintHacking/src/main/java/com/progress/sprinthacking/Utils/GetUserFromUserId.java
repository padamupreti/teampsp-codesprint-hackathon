package com.progress.sprinthacking.Utils;

import com.progress.sprinthacking.Entity.User;
import com.progress.sprinthacking.Filter.JWTFilter;
import com.progress.sprinthacking.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetUserFromUserId {

    @Autowired
    private UserRepo userRepo;


    public User getCurrentUser() {
        return findUser(JWTFilter.getCurrentUserId());
    }

    public User findUser(Long currentUserId) {
        if (currentUserId != null) {
            return userRepo.findById(currentUserId).orElse(null);
        }
        return null;
    }
}
