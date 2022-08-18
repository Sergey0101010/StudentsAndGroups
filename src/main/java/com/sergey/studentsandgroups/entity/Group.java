package com.sergey.studentsandgroups.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "university_groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private String groupNumber;

    @Column(name = "created_at")
    private LocalDate creationDate;

    @OneToMany(mappedBy = "group")
    private List<Student> students;
}
