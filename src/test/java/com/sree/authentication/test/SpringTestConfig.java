/**
 * 
 */
package com.sree.authentication.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.sree.authentication.recaptcha.ReCaptchaService;
import static org.mockito.Mockito.mock;

/**
 * @author SreenivasraoMuppavar
 *
 */
@Configuration
public class SpringTestConfig {
	
	@Bean
    ReCaptchaService reCaptchaService() {
        return mock(ReCaptchaService.class);
    }

}
