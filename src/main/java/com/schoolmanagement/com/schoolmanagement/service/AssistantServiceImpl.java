package com.schoolmanagement.com.schoolmanagement.service;

import com.schoolmanagement.com.schoolmanagement.dao.AssistantRepository;
import com.schoolmanagement.com.schoolmanagement.dao.CourseRepository;
import com.schoolmanagement.com.schoolmanagement.entity.Assistant;
import com.schoolmanagement.com.schoolmanagement.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssistantServiceImpl implements AssistantService{
    @Autowired
    AssistantRepository assistantRepository;

    @Autowired
    CourseRepository courseRepository;

    @Override

    public List<Assistant> findAll() {
        return assistantRepository.findAll();
        }

        @Override

        public Assistant findById(int id) {
            Optional<Assistant> result = assistantRepository.findById(id);
            return result.orElse(null);
        }

        @Override

        public void save(Assistant assistant) {
            assistantRepository.save(assistant);
        }

        @Override

        public void deleteById(int id) {
            assistantRepository.deleteById(id);
        }

    @Override
    public void assignCourses(int assistantId, List<Integer> courseIds) {
        Optional<Assistant> assistantOptional = assistantRepository.findById(assistantId);
        if (assistantOptional.isPresent()) {
            Assistant assistant = assistantOptional.get();
            List<Course> courses = courseRepository.findAllById(courseIds);
            // Aquí asumimos que tienes un método para establecer los cursos en tu entidad Assistant
            assistant.setCourses(courses);
            assistantRepository.save(assistant);
        }
    }

    @Override
    public Optional<Assistant> findByUsername(String username) {
        return assistantRepository.findByUser_Username(username);
    }

    @Override
    public List<Course> findActiveCoursesByAssistantUsername(String username) {
        Optional<Assistant> assistant = findByUsername(username);
        if (assistant.isPresent()) {
            // Aquí implementas la lógica para filtrar y retornar solo los cursos activos
            // Esto podría implicar llamar a un método personalizado del repositorio
            // o filtrar la lista de cursos del asistente para retornar solo los activos.
            return assistant.get().getCourses().stream()
                    .filter(Course::getIsActive) // Asume que Course tiene un método getIsActive
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

}

