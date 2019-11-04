package edu.hubu.learn.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "flower")
@Data
public class Flower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String yanse;

    private String mean;

    @Column(name="avatar_url")
    private String avatar;

	public void setAvatar(String fileName) {
	}
}