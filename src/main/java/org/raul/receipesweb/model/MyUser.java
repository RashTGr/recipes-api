package org.raul.receipesweb.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
}
