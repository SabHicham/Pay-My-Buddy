package com.paymybuddy.repository;

import com.paymybuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
    @Query("UPDATE User u set u.iban=?1 WHERE u.email=?2")
    User updateIbanByEmail(String iban, String email);
}
