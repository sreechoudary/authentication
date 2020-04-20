package com.sree.authentication.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sree.authentication.recaptcha.ReCaptchaService;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("it")
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRegistrationIT {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ReCaptchaService reCaptchaService;

    @Test
    public void submitRegistrationAccountExists() throws Exception {
    	String validReCaptcha = "valid-re-captcha";
        when(reCaptchaService.validate(validReCaptcha)).thenReturn(true);
        this.mockMvc
                .perform(
                        post("/registration")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", "Memory")
                                .param("lastName", "Not Found")
                                .param("email", "sree.muppavarapu@gmail.com")
                                .param("confirmEmail", "sree.muppavarapu@gmail.com")
                                .param("password", "password")
                                .param("confirmPassword", "password")
                                .param("terms", "on")
                                .param("reCaptchaResponse", validReCaptcha)
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("user", "email"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitRegistrationPasswordNotMatching() throws Exception {
    	String validReCaptcha = "valid-re-captcha";
        when(reCaptchaService.validate(validReCaptcha)).thenReturn(true);
        this.mockMvc
                .perform(
                        post("/registration")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", "Memory")
                                .param("lastName", "Not Found")
                                .param("email", "sree4training@gmail.com")
                                .param("confirmEmail", "sree4training@gmail.com")
                                .param("password", "password")
                                .param("confirmPassword", "invalid")
                                .param("terms", "on")
                                .param("reCaptchaResponse", validReCaptcha)
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasErrors("user"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitRegistrationEmailNotMatching() throws Exception {
    	String validReCaptcha = "valid-re-captcha";
        when(reCaptchaService.validate(validReCaptcha)).thenReturn(true);
        this.mockMvc
                .perform(
                        post("/registration")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", "Memory")
                                .param("lastName", "Not Found")
                                .param("email", "sree.muppavarapu@gmail.com")
                                .param("confirmEmail", "sree4training@gmail.com")
                                .param("password", "password")
                                .param("confirmPassword", "invalid")
                                .param("terms", "on")
                                .param("reCaptchaResponse", validReCaptcha)
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasErrors("user"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitRegistrationSuccess() throws Exception {
    	String validReCaptcha = "valid-re-captcha";
        when(reCaptchaService.validate(validReCaptcha)).thenReturn(true);
        this.mockMvc
                .perform(
                        post("/registration")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", "Memory")
                                .param("lastName", "Not Found")
                                .param("email", "sree4training@gmail.com")
                                .param("confirmEmail", "sree4training@gmail.com")
                                .param("password", "password")
                                .param("confirmPassword", "password")
                                .param("terms", "on")
                                .param("reCaptchaResponse", validReCaptcha)
                )
                .andExpect(redirectedUrl("/registration?success"))
                .andExpect(status().is3xxRedirection());
    }
    
    @Test
    public void submitWithoutReCaptcha() throws Exception {
        this.mockMvc
                .perform(
                        post("/forgot-password")
                                .param("email", "sree4training@gmail.com")
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("forgotPasswordForm", "reCaptchaResponse"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitWithInvalidReCaptcha() throws Exception {
        String invalidReCaptcha = "invalid-re-captcha";
        when(reCaptchaService.validate(invalidReCaptcha)).thenReturn(false);
        this.mockMvc
                .perform(
                        post("/forgot-password")
                                .param("email", "sree4training@gmail.com")
                                .param("reCaptchaResponse", invalidReCaptcha)
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("forgotPasswordForm", "reCaptchaResponse"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitWithValidReCaptcha() throws Exception {
        String validReCaptcha = "valid-re-captcha";
        when(reCaptchaService.validate(validReCaptcha)).thenReturn(true);
        this.mockMvc
                .perform(
                        post("/forgot-password")
                                .param("email", "sree4training@gmail.com")
                                .param("reCaptchaResponse", validReCaptcha)
                )
                .andExpect(model().hasNoErrors())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/forgot-password?success"));
    }

}
