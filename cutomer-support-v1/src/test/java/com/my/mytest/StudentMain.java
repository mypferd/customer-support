package com.my.mytest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StudentMain {

    public static void main(String[] args) throws JsonProcessingException {
        Student student = new Student();
        student.setRealName("Lenerd");
        System.out.println(new ObjectMapper().writeValueAsString(student));
    }

}
