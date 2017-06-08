package com.inva.hipstertest.repository;

import com.inva.hipstertest.domain.UserExtra;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Vasyl on 07.06.2017.
 */
@SuppressWarnings("unused")
public interface UserExtraRepository extends JpaRepository<UserExtra, Long> {

}
