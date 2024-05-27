package com.gl.todo_app.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProfilePicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long img_id;

    private String name;

    private String type;

    @Lob
    @Column(name = "image",length = 50000)
    private byte[] image;

    @OneToOne(cascade = CascadeType.PERSIST, targetEntity = User.class)
    private User user;
}
