package com.modul2.learning.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//este util sa punem si proprietatea name, la @Entity, atunci cand vrem sa construim query-uri si in acele query-uri sa facem
//"referinta" catre acea tabela/entitate
@Entity(name = "user")   // mapped to a database table (in cazul nostru tabelul v a fi user)
@Table(name = "user", schema = "public") //Specifies the table name explicitly (user) si pune schema pe public


//de aici incep field urile pt User
public class User {
    @Id   //annotation pt PK
    @Column(name = "ID")  //annotation pt coloana
    //TODO: de cautat un exemplu cu diferentele de @GeneratedValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // anntation: id will be auto-incremented by the database
    private Long id;    //aici e exemplu cu GenerationType.IDENTITY

    @Column(name = "USER_NAME")
    private String userName;   //coloana userName

    @Column(name = "FIRST_NAME")
    private String firstName;   //coloana firstName

    @Column(name = "LAST_NAME")
    private String lastName;   //coloana lastName

    @Column(name = "AGE")
    private Integer age;   //coloana age

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            fetch = FetchType.LAZY,
            //daca voi scoate din lista o carte atunci la salvarea userului, cartea va fi stearsa din baza de date
            orphanRemoval = true,
            //cum se numeste
            mappedBy = "user")
    private List<Book> books = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "USER_APPLICATION", schema = "public",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "application_id", nullable = false))
    private List<Application> applications = new ArrayList<>();

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

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        this.books.add(book);
        book.setUser(this);
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public void addApplication(Application application) {
        this.applications.add(application);
        application.addUser(this);
    }
}
