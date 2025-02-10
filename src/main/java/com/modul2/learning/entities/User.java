package com.modul2.learning.entities;

import jakarta.persistence.*;

//este util sa punem si proprietatea name, la @Entity, atunci cand vrem sa construim query-uri si in acele query-uri sa facem
//"referinta" catre acea tabela/entitate
@Entity(name = "user")   // mapped to a database table (in cazul nostru tabelul v a fi user)
@Table(name="user", schema = "public") //Specifies the table name explicitly (user) si pune schema pe public


//de aici incep field urile pt User
public class User {
    @Id   //annotation pt PK
    @Column(name="ID")  //annotation pt coloana
    //TODO: de cautat un exemplu cu diferentele de @GeneratedValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // anntation: id will be auto-incremented by the database
    private Long id;    //aici e exemplu cu GenerationType.IDENTITY

    @Column(name="USER_NAME")
    private String userName;   //coloana userName

    @Column(name="FIRST_NAME")
    private String firstName;   //coloana firstName

    @Column(name="LAST_NAME")
    private String lastName;   //coloana lastName

    @Column(name="AGE")
    private Integer age;   //coloana age

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
