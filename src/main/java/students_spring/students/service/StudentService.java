package students_spring.students.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import students_spring.students.model.Student;
import students_spring.students.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService implements IStudentService{

    @Autowired
    private StudentRepository studentRepo;

    @Override
    public List<Student> listStudents() {
        List<Student> studentList = studentRepo.findAll();
        return studentList;
    }

    @Override
    public Student findById(Integer idStudent) {
        Student student = studentRepo.findById(idStudent).orElse(null);
        return student;
    }

    @Override
    public void addStudent(Student student) {
        studentRepo.save(student);
    }

    @Override
    public void deleteStudent(Student student) {
        try{
            studentRepo.delete(student);
        }catch(Exception e){
            System.out.println("Error al eliminar el alumno: " + e.getMessage());
        }
    }
}
