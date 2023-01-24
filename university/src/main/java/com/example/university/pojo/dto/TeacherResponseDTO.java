package com.example.university.pojo.dto;


import com.example.university.pojo.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TeacherResponseDTO {
    private List<TeacherDTO> teacherList;

    public TeacherResponseDTO(List<TeacherDTO> teacherList) {
        this.teacherList = teacherList;
    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class TeacherDTO {
        private String id;
        private String name;

        public TeacherDTO(Teacher t) {
            this.id = t.getId();
            this.name = t.getName();
        }
    }
}
