package com.example.small_project;



import java.util.List;

import org.springframework.stereotype.Service;



@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // Create
    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    // Read all
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // Read by ID
    public Student getStudentById(int id) {
        return repository.findById(id).orElse(null);
    }

    // Update
    public Student updateStudent(int id, Student student) {

        Student existingStudent = repository.findById(id).orElse(null);

        if (existingStudent != null) {
            existingStudent.setName(student.getName());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setCourse(student.getCourse());

            return repository.save(existingStudent);
        }

        return null;
    }

    // Delete
    public String deleteStudent(int id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Student deleted successfully";
        }

        return "Student not found";
    }
}
