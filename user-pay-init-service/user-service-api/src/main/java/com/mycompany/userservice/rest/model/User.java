package com.mycompany.userservice.rest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;


/**
 * JPA-сущность User.
 */
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true)
    private Long id;

    // имя пользователя
    @Column(name = "user_name", unique = true, nullable = false)
//    @NotNull
    @NotBlank(message = "Поле 'userName' не должно быть пустым") // только для String (не пустая и не пробельная).
    @Size(min = 1, max = 12, message = "Поле 'userName' должно содержать от 1 до 12 символов") // проверка на длину / эта аннотация предназначена для строк, коллекций и массивов
    private String userName;

//    @Column(name = "user_password")
//    private String password;

//    @Column(name = "user_email")
//    private String email;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userName);
    }
}