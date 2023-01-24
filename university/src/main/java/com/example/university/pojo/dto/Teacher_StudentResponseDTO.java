package com.example.university.pojo.dto;


import com.example.university.pojo.entity.Teacher_Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
public class Teacher_StudentResponseDTO {
    private List<Teacher_StudentDTO> teacherStudentList;

    public Teacher_StudentResponseDTO(List<Teacher_StudentDTO> teacherStudentList) {
        this.teacherStudentList = teacherStudentList;
    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class Teacher_StudentDTO {
        private String id;
        private String t_id;
        private String s_id;
        private String t_name;
        private String s_name;


        public Teacher_StudentDTO(Teacher_Student ts) {
            this.id = ts.getId();
            this.t_id = ts.getTeacher().getId();
            this.s_id = ts.getStudent().getId();
            this.t_name = ts.getTeacher().getName();
            this.s_name = ts.getStudent().getName();
        }
    }


}
