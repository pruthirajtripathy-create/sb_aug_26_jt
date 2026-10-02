package com.example.small_project;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.small_project.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
