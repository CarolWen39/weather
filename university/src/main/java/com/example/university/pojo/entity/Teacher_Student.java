package com.example.university.pojo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "teacher_student")
@AllArgsConstructor
@NoArgsConstructor
public class Teacher_Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne(targetEntity = Teacher.class, fetch = FetchType.LAZY)
    @JoinColumn(name="t_id")
    private Teacher teacher;


    @ManyToOne(targetEntity = Student.class, fetch = FetchType.LAZY)
    @JoinColumn(name="s_id")
    private Student student;

    public Teacher_Student(Teacher teacher, Student student) {
        this.teacher = teacher;
        this.student = student;
    }

}
