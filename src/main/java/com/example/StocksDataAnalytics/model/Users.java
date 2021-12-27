package com.example.StocksDataAnalytics.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

    private long id;
    private String firstName;
    private String lastName;
    private Boolean active;
    private String username;
    private String password;
    private Boolean superUser;

    public Users() {

    }

    public Users(String firstName, String lastName, Boolean active,
                 String username, String password, Boolean superUser) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.username = username;
        this.password = password;
        this.superUser = superUser;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
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
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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
}