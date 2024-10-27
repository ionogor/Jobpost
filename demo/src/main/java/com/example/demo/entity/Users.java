package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @NotEmpty
    private String email;
    private boolean isActive;
    @NotEmpty
    private String password;
    private Date registrationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userTypeId",referencedColumnName = "userTypeId")
    private UsersType userTypeId;

    public Users() {
    }

    public Users(int userId, String email, boolean isActive, String password, Date registrationDate, UsersType userTypeId) {
        this.userId = userId;
        this.email = email;
        this.isActive = isActive;
        this.password = password;
        this.registrationDate = registrationDate;
        this.userTypeId = userTypeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public @NotEmpty String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public @NotEmpty String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public UsersType getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(UsersType userTypeId) {
        this.userTypeId = userTypeId;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                ", password='" + password + '\'' +
                ", registrationDate=" + registrationDate +
                ", userTypeId=" + userTypeId +
                '}';
    }
}
