package com.inva.hipstertest.repository;

import com.inva.hipstertest.domain.LessonType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the LessonType entity.
 */
@SuppressWarnings("unused")
public interface LessonTypeRepository extends JpaRepository<LessonType, Long> {

}
