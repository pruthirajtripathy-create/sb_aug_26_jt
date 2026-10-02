package com.example.one_to_one;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@RequiredArgsConstructor
@SpringBootApplication
public class ManyToOneApplication {
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    public static void main(String[] args){
        SpringApplication.run(ManyToOneApplication.class);
    }
    @Bean
    public CommandLineRunner commandLineRunner(){
        return args -> {
           onewayBinding();
//       Teacher newTeacher= Teacher.builder().teacherName("ankit").build();
//            Subject subject1= Subject.builder().subjectName("Css").teacher(newTeacher).build();
//            Subject subject2= Subject.builder().subjectName("js").teacher(newTeacher).build();
//            Subject subject3= Subject.builder().subjectName("Python").teacher(newTeacher).build();
//            Subject subject4= Subject.builder().subjectName(".net").teacher(newTeacher).build();
//
//            newTeacher.setSubjects(List.of(subject1,subject2,subject3,subject4));
//
//           teacherRepository.save(newTeacher);
//       EXTRACT

            teacherRepository.findById(1).orElseThrow().getSubjects().forEach(sub -> {
                System.out.println(sub.getTeacher().getTeacherName()+"\t->\t"+sub.getSubjectName());
//       UPDATE
                Teacher teacher = teacherRepository.findById(1)
                        .orElseThrow();

                teacher.setTeacherName("kundu bro");

                teacherRepository.save(teacher);
            });
        };
    }
    private void onewayBinding(){

//        SAVE
//        Teacher teacher= Teacher.builder().teacherName("Amiittt kundu").build();
//
//        Subject subject1= Subject.builder().subjectName("C").teacher(teacher).build();
//        Subject subject2= Subject.builder().subjectName("C++").teacher(teacher).build();
//        Subject subject3= Subject.builder().subjectName("Python").teacher(teacher).build();
//        Subject subject4= Subject.builder().subjectName("Java").teacher(teacher).build();
//
//        subjectRepository.saveAll(List.of(subject1,subject2,subject3,subject4));
//        UPDATE
        Subject subject = subjectRepository.findById(1)
                .orElseThrow();

        subject.setSubjectName("JavaScript");

        subjectRepository.save(subject);



//        DELETE

//        EXTRACT
        subjectRepository.findAll().forEach(sub -> {
            System.out.println(sub.getSubjectName()+ "\t->\t" +sub.getTeacher().getTeacherName());
        });

    }
}
