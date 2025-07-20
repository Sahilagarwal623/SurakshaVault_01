package com.kucp1127.SurakshaWalt.UserInformation.Repository;

import com.kucp1127.SurakshaWalt.UserInformation.Model.UserInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoModel, String> {
}