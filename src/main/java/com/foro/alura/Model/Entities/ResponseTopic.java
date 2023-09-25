package com.foro.alura.Model.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Response")
@Table(name="responseTopic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String response  ;
    private String fecha_creacion;
    @ManyToOne
    private Topic topic;
    @ManyToOne
    private User user;
}
