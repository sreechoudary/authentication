/**
 * 
 */
package com.sree.authentication.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sree.authentication.web.dto.ForgotPasswordForm;

/**
 * @author SreenivasraoMuppavar
 *
 */
@Controller
@RequestMapping("/forgot-password")
public class ForgotPasswordController {
	
	@ModelAttribute("forgotPasswordForm")
    public ForgotPasswordForm forgotPasswordForm() {
        return new ForgotPasswordForm();
    }

    @GetMapping
    public String showForgotPassword(Model model) {
        return "forgot-password";
    }

    @PostMapping
    public String handleForgotPassword(@ModelAttribute("forgotPasswordForm") @Valid ForgotPasswordForm form, BindingResult result){

        if (result.hasErrors()){
            return "forgot-password";
        }

        return "redirect:/forgot-password?success";
    }

}
