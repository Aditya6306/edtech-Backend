package com.learnify_backend.entity;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;    
import lombok.Setter;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gender;

    private LocalDate dateOfBirth;

    @Column(length = 1000)
    private String about;

    private String phoneNumber;

    @OneToOne(mappedBy = "profile")
    @JsonBackReference
    private User user;
}
