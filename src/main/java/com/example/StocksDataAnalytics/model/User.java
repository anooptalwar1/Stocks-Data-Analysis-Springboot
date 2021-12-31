package com.example.StocksDataAnalytics.model;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {

    private Integer id;
    private String firstName;
    private String lastName;

    private Boolean active;
    private String userName;
    private String password;
    private Boolean superUser;
    private String roles;



    public User() {

    }

    public User(String firstName, String lastName, Boolean active,
                 String userName, String password, Boolean superUser, String roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.userName = userName;
        this.password = password;
        this.superUser = superUser;
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "active", nullable = false)
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }

    @Column(name = "username", nullable = false)
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "super_user", nullable = false)
    public Boolean getSuperUser() {
        return superUser;
    }
    public void setSuperUser(Boolean superUser) {
        this.superUser = superUser;
    }

    @Column(name = "roles", nullable = true)
    public String getRoles() { return roles; }
    public void setRoles(String roles) { this.roles = roles; }
}