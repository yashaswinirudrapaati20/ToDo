
package com.Yashaswini.MyWebApp.repository;

import com.Yashaswini.MyWebApp.entity.PasswordResetOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PasswordResetOtpRepository
        extends JpaRepository<PasswordResetOtp, Integer> {

    Optional<PasswordResetOtp> findByEmail(String email);

    void deleteByEmail(String email);
}
