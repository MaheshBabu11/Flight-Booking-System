package com.mycompany.flightbookingsystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String sex;
    private int age;
    private String address;
    private String country;
    private String emailId;
    private long contactNumber;
}
