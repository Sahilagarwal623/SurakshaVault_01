package com.kucp1127.SurakshaWalt.SecurityConfiguration.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kucp1127.SurakshaWalt.SecurityConfiguration.Model.PartnerRegistration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<PartnerRegistration, String> {
    @Query("SELECT p.username FROM PartnerRegistration p")
    List<String> findAllUsernames();
}
