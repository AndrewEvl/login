package by.login.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Table(name = "users_table")
public class User extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "mail")
    private String mail;
    @Column(name = "password")
    private String password;
    @Column(name = "status_now")
    private Status statusNow;
    @Column(name = "previous_status")
    private Status previousStatus;
    @Column(name = "image")
    private String image;
}
