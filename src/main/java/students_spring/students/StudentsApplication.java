package students_spring.students;

import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import students_spring.students.model.Student;
import students_spring.students.service.StudentService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class StudentsApplication implements CommandLineRunner {

	@Autowired
	private StudentService studentService;

	private static final Logger logger = LoggerFactory.getLogger(StudentsApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
		logger.info("Iniciando aplicacion");
		SpringApplication.run(StudentsApplication.class, args);
		logger.info("Aplicacion finalizada.");
	}


	@Override
	public void run(String... args) throws Exception {


		logger.info("Ejecutando metodo run de Spring");

		boolean end = false;
		Scanner console = new Scanner(System.in);
		while(!end){
			menu();
			end = options(console);
			logger.info(nl);
		}
	}

	private void menu(){
		logger.info("""
				*** Sistema de estudiantes ***
				1. Listar todos los estudiantes
				2. Buscar estudiante
				3. Agregar estudiante
				4. Modificar estudiante
				5. Eliminar estudiante
				6. Salir
				
				Elige una opcion:""");
	}

	private boolean options(Scanner console){
		int option = Integer.parseInt(console.nextLine());
		boolean end = false;
		switch(option){
			case 1 -> {
				logger.info(nl + "Listado de estudiantes" + nl);
				List<Student> stdList =  studentService.listStudents();
				stdList.forEach(student -> logger.info(student.toString() + nl));
			}
			case 2 -> {
				logger.info("Introduce el id del estudiante a buscar: ");
				int idStudent = Integer.parseInt(console.nextLine());
				Student student = studentService.findById(idStudent);
				if(student != null){
					logger.info("Estudiante encontrado: " + student + nl);
				} else {
					logger.info("Estudiante con id " + idStudent + " no encontrado" + nl);
				}
			}
			case 3-> {
				logger.info("Agregar estudiante: " +nl);
				logger.info("Ingrese el nombre: ");
				String name = console.nextLine();
				logger.info("Ingrese el apellido: ");
				String lastName = console.nextLine();
				logger.info("Telefono: ");
				String phonenumber = console.nextLine();
				logger.info("Email: ");
				String email = console.nextLine();

				Student student = new Student();
				student.setName(name);
				student.setLastName(lastName);
				student.setPhonenumber(phonenumber);
				student.setEmail(email);

				studentService.addStudent(student);

				logger.info("Estudiante agregado correctamente" + nl);
			}
			case 4 -> {
				logger.info("Modificar estudiante: " + nl);
				logger.info("Ingrese el id del estudiante a modificar: ");
				int idStudent = Integer.parseInt(console.nextLine());
				Student student = studentService.findById(idStudent);
				if(student != null){
					logger.info("Ingrese el nuevo nombre: ");
					String name = console.nextLine();
					logger.info("Ingrese el nuevo apellido: ");
					String lastName = console.nextLine();
					logger.info("Telefono: ");
					String phonenumber = console.nextLine();
					logger.info("Email: ");
					String email = console.nextLine();

					student.setName(name);
					student.setLastName(lastName);
					student.setPhonenumber(phonenumber);
					student.setEmail(email);
					studentService.addStudent(student);

					logger.info("Estudiante modificado correctamente." + nl);
				} else {
					logger.info("El estudiante no se ha encontrado en la base de datos." + nl);
				}
			}
			case 5 -> {
				logger.info("Ingrese el ID del estudiante a eliminar: ");
				int idStudent = Integer.parseInt(console.nextLine());
				Student student = studentService.findById(idStudent);
				if(student != null){
					logger.info("Está seguro que desea eliminar el estudiante con id " + idStudent + "? (1 - si; 2- no)" + nl);
					int yn = Integer.parseInt(console.nextLine());
					if(yn == 1){
						studentService.deleteStudent(student);
						logger.info("Estudiante eliminado correctamente. " + nl);
					} else if(yn == 2) {
						logger.info("Eliminacion de estudiante cancelada." + nl);
					}
				}else{
					logger.info("El estudiante no se ha encontrado en la base de datos. " + nl);
				}
			}
			case 6 -> {
				logger.info("Hasta pronto" +nl + nl);
				end = true;
			}
			default -> logger.info("La opcion no es valida.");
		}
		return end;
	}

}
