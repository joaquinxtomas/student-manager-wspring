package students_spring.students.service;

import students_spring.students.model.Student;

import java.util.List;

public interface IStudentService {
    public List<Student> listStudents();
    public Student findById(Integer idStudent);
    public void addStudent(Student student);
    public void deleteStudent(Student student);


}
