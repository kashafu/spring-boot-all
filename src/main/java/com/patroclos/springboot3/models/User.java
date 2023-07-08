package com.patroclos.springboot3.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.processing.Generated;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "user")
public class User {
  @Id
  private String id;

  @NotBlank
  @Size(max = 20)
  @Indexed(unique = true)
  private String username;

  @NotBlank
  @Size(max = 120)
  private String password;

  @NotBlank
  @Size(max = 50)
  @Email
  @Indexed(unique = true)
  private String email;

//  @DBRef
//  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

//  public Set<Role> getRoles() {
//    return roles;
//  }
//
//  public void setRoles(Set<Role> roles) {
//    this.roles = roles;
//  }
}
