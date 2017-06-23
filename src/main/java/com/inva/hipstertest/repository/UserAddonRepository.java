package com.inva.hipstertest.repository;

import com.inva.hipstertest.domain.Parent;
import com.inva.hipstertest.domain.Pupil;
import com.inva.hipstertest.domain.UserAddon;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UserAddon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserAddonRepository extends JpaRepository<UserAddon,Long> {
    @Query("select user from UserAddon user where user.user.login = ?#{principal.username}")
    UserAddon findByCurrentUser();


}
