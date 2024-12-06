package students_spring.students.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import students_spring.students.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
