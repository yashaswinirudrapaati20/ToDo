
package com.Yashaswini.MyWebApp.controller;

import com.Yashaswini.MyWebApp.service.PasswordResetService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ForgotPasswordController {

    private final PasswordResetService passwordResetService;

    public ForgotPasswordController(
            PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(
            @RequestParam String email,
            Model model) {

        passwordResetService.generateOtp(email);

        model.addAttribute("email", email);

        model.addAttribute("message",
                "If an account exists with that email, "
                        + "an OTP has been sent.");

        return "verify-otp";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(
            @RequestParam String email,
            @RequestParam String otp,
            HttpSession session,
            Model model) {

        boolean valid =
                passwordResetService.verifyOtp(email, otp);

        if (valid) {
            session.setAttribute("verifiedResetEmail", email);

            return "reset-password";
        }

        model.addAttribute("email", email);
        model.addAttribute("message",
                "Invalid or expired OTP. Please try again.");

        return "verify-otp";
    }

    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            HttpSession session,
            Model model) {

        String email = (String) session.getAttribute("verifiedResetEmail");

        if (email == null) {
            return "redirect:/forgot-password";
        }

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("message", "Passwords do not match.");
            return "reset-password";
        }

        if (newPassword.length() < 8) {
            model.addAttribute("message",
                    "Password must contain at least 8 characters.");
            return "reset-password";
        }

        try {
            passwordResetService.updatePassword(email, newPassword);

            session.removeAttribute("verifiedResetEmail");

            return "redirect:/login?resetSuccess";

        } catch (RuntimeException e) {
            session.removeAttribute("verifiedResetEmail");
            model.addAttribute("message",
                    "Your password reset session expired. Please request a new OTP.");

            return "forgot-password";
        }
    }

}
