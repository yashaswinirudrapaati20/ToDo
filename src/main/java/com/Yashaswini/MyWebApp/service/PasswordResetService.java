
package com.Yashaswini.MyWebApp.service;

import com.Yashaswini.MyWebApp.entity.PasswordResetOtp;
import com.Yashaswini.MyWebApp.repository.PasswordResetOtpRepository;

import com.Yashaswini.MyWebApp.entity.User;
import com.Yashaswini.MyWebApp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class PasswordResetService {

    private final UserRepository userRepo;
    private final PasswordResetOtpRepository otpRepo;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    private final SecureRandom secureRandom = new SecureRandom();

    public PasswordResetService(
            UserRepository userRepo,
            PasswordResetOtpRepository otpRepo,
            EmailService emailService,
            PasswordEncoder passwordEncoder) {

        this.userRepo = userRepo;
        this.otpRepo = otpRepo;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void generateOtp(String email) {

        User user = userRepo.findByEmail(email).orElse(null);

        // Do not reveal whether an account exists
        if (user == null) {
            return;
        }

        // Generate a secure 6-digit OTP
        String otp = String.format(
                "%06d", secureRandom.nextInt(1_000_000));

        // Hash OTP before saving it
        String otpHash = passwordEncoder.encode(otp);

        // Remove any previous OTP for this email
        otpRepo.deleteByEmail(email);

        // Set OTP expiry to 5 minutes
        PasswordResetOtp resetOtp = new PasswordResetOtp();
        resetOtp.setEmail(email);
        resetOtp.setOtpHash(otpHash);
        resetOtp.setExpiryDate(LocalDateTime.now().plusMinutes(5));
        resetOtp.setVerified(false);
        resetOtp.setAttempts(0);

        // Save OTP in MySQL
        otpRepo.save(resetOtp);

        // Send OTP through Gmail
        String subject = "MyWebApp - Password Reset OTP";

        String body = "Hello " + user.getUsername() + ",\n\n"
                + "Your password reset OTP is: " + otp + "\n\n"
                + "This OTP is valid for 5 minutes.\n"
                + "Do not share this OTP with anyone.\n\n"
                + "If you did not request this, ignore this email.";

        emailService.sendEmail(email, subject, body);
    }

    @Transactional
    public boolean verifyOtp(String email, String otp) {

        PasswordResetOtp resetOtp =
                otpRepo.findByEmail(email).orElse(null);

        // Check whether OTP exists
        if (resetOtp == null) {
            return false;
        }

        // Check whether OTP has already been verified
        if (resetOtp.isVerified()) {
            return false;
        }

        // Check expiry
        if (LocalDateTime.now().isAfter(resetOtp.getExpiryDate())) {
            otpRepo.delete(resetOtp);
            return false;
        }

        // Allow maximum 5 incorrect attempts
        if (resetOtp.getAttempts() >= 5) {
            otpRepo.delete(resetOtp);
            return false;
        }

        // Compare entered OTP with stored hash
        if (!passwordEncoder.matches(otp, resetOtp.getOtpHash())) {

            resetOtp.setAttempts(resetOtp.getAttempts() + 1);
            otpRepo.save(resetOtp);

            return false;
        }

        // OTP is correct
        resetOtp.setVerified(true);
        otpRepo.save(resetOtp);

        return true;
    }

    @Transactional
    public void updatePassword(String email, String newPassword) {

        PasswordResetOtp resetOtp =
                otpRepo.findByEmail(email).orElseThrow(
                        () -> new RuntimeException("OTP verification required")
                );

        if (!resetOtp.isVerified()
                || LocalDateTime.now().isAfter(resetOtp.getExpiryDate())) {
            throw new RuntimeException("OTP verification expired");
        }

        User user = userRepo.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        // Encode new password using BCrypt
        user.setPassword(passwordEncoder.encode(newPassword));

        userRepo.save(user);

        // OTP cannot be reused
        otpRepo.delete(resetOtp);
    }

}
