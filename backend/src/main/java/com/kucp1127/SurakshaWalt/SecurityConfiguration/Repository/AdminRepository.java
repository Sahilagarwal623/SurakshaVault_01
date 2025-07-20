package com.kucp1127.SurakshaWalt.SecurityConfiguration.Repository;


import com.kucp1127.SurakshaWalt.SecurityConfiguration.Model.AdminRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminRegistration , String> {
}
