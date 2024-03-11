package com.schoolmanagement.com.schoolmanagement.dao;

import com.schoolmanagement.com.schoolmanagement.entity.SchoolSubject;
import com.schoolmanagement.com.schoolmanagement.entity.Teacher;
import com.schoolmanagement.com.schoolmanagement.entity.TeacherSubjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface TeacherSubjectRepository extends JpaRepository<TeacherSubjects, Integer> {
    // Ejemplo de método para encontrar las materias de un profesor
    List<TeacherSubjects> findByTeacher(Teacher teacher);

    // Ejemplo de método para encontrar los profesores de una materia
    List<TeacherSubjects> findBySchoolSubject(SchoolSubject schoolSubject);

    List<TeacherSubjects> findByTeacherId(int teacherId);

    @Query("SELECT ts FROM TeacherSubjects ts WHERE ts.teacher.id = :teacherId AND ts.schoolSubject.id = :subjectId")
    TeacherSubjects findByTeacherIdAndSubjectId(@Param("teacherId") int teacherId, @Param("subjectId") int subjectId);

    @Query("SELECT ts FROM TeacherSubjects ts WHERE ts.teacher.id = :teacherId AND ts.isActive = true")
    List<TeacherSubjects> findActiveByTeacherId(@Param("teacherId") int teacherId);

}
