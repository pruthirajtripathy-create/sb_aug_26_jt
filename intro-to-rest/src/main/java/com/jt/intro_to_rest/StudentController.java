package com.jt.intro_to_rest;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

// @Controller
// @ResponseBody
@RestController
public class StudentController {
    private ObjectMapper mapper;
    public StudentController(ObjectMapper mapper){
    this.mapper = mapper;
    }

    @RequestMapping(value = "/student",method=RequestMethod.GET)
    // @ResponseBody //It Tells springboot to return a json object insted of a view file 
    
    public Student getStudent(){
        Student student=new Student(101,"Ankit",new String[]{"java","python"});
        System.out.println("/////" + student);
        return student;
    
    }
    @RequestMapping("/student1")
    public void ConvertjavaObjTojson() throws JacksonException{
        Student student = new Student(101,"Ankit",new String[]{"java","python"});

       String json= mapper.writeValueAsString(student);
       System.out.println("java to json"+ json);
    }
     @RequestMapping("/student2")
    public void ConvertjsonObjTojava() throws JacksonException{
      String json="""
              {
      "id": 102,
      "name": "Ankit",
      "courses": ["C","C++"]
      }
              """;
              Student student= mapper.readValue(json, Student.class);
              System.out.println("After converting from json to java object"+ student);

    }
    
    @RequestMapping("/fruits")
    // @ResponseBody
    public List<String> geStrings(){
        return List.of("Apple","Mango","Orange");
    }
}
