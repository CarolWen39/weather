package com.example.university.pojo.dto;


import com.example.university.pojo.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StudentResponseDTO {
    private List<StudentDTO> studentList;

    public StudentResponseDTO(List<StudentDTO> studentList) {
        this.studentList = studentList;
    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class StudentDTO {
        private String id;
        private String name;

        public StudentDTO(Student s) {
            this.id = s.getId();
            this.name = s.getName();
        }
    }
}
