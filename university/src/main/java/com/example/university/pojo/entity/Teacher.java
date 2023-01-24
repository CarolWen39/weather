package com.example.university.pojo.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "teacher")
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private String id;

    @Column
    private String name;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Teacher_Student> teacherStudents;

    public Teacher(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Teacher(String name) {
        this.name = name;
    }

}
