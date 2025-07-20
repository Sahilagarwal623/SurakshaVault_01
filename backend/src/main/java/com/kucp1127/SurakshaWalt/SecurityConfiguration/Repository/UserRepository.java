package com.kucp1127.SurakshaWalt.SecurityConfiguration.Repository;


import com.kucp1127.SurakshaWalt.SecurityConfiguration.Model.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserRegistration , String> {

    @Query("SELECT u.username FROM UserRegistration u")
    List<String> findAllUsernames();

}
