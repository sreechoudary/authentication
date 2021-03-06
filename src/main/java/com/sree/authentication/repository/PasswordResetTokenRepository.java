/**
 * 
 */
package com.sree.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sree.authentication.model.PasswordResetToken;

/**
 * @author SreenivasraoMuppavar
 *
 */
@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

}
