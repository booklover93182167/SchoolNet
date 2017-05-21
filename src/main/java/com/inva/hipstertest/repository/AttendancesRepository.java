package com.inva.hipstertest.repository;

import com.inva.hipstertest.domain.Attendances;
import com.inva.hipstertest.service.dto.PupilAttendanceDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.NamedNativeQuery;
import java.util.List;

/**
 * Spring Data JPA repository for the Attendances entity.
 */
@SuppressWarnings("unused")

@NamedNativeQuery(
    name = "getAllByTwoWeeks",
    query = "select ppl.id as pupil_id, scd.lesson_id, atd.grade, scd.jhi_date as date " +
        "from pupil as ppl left join jhi_user as usr on  ppl.user_id  = usr.id " +
        "left outer join attendances as atd on ppl.id = atd.pupil_id " +
        "left outer join schedule as scd on scd.id = atd.schedule_id " +
        "where ppl.enabled = 1 and timestampdiff(day, scd.jhi_date,curdate()) <=: numberDays" +
        "and scd.form_id =: formId",
    resultSetMapping = "pupilAttendanceDTOMapping")
public interface AttendancesRepository extends JpaRepository<Attendances, Long> {

    @Query(name = "getAllByTwoWeeks")
    List<PupilAttendanceDTO> getAllByTwoWeeks(@Param("namerDays") Integer numberDays, @Param("formId") Long formId);


}
