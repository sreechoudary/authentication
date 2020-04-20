/**
 * 
 */
package com.sree.authentication.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.sree.authentication.recaptcha.ValidReCaptcha;

/**
 * @author SreenivasraoMuppavar
 *
 */
public class ForgotPasswordForm {
	
	@Email
    @NotEmpty(message = "Email must not be empty")
    private String email;

    @NotEmpty(message = "I'm not a robot checkbox should be checked")
    @ValidReCaptcha
    private String reCaptchaResponse;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReCaptchaResponse() {
        return reCaptchaResponse;
    }

    public void setReCaptchaResponse(String reCaptchaResponse) {
        this.reCaptchaResponse = reCaptchaResponse;
    }

}
