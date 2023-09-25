package com.foro.alura.Model.Entities;

import com.foro.alura.Model.DTO.DatesRegisterAndUpdateTopic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity(name = "Topic")
@Table(name="topic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)

    private String titulo;
    @Column(unique = true)
    private String mensaje  ;
    private String fecha_creacion;
    private String estatus;
    private String curso;
    @ManyToOne
    private User user;

    public Topic(DatesRegisterAndUpdateTopic datesRegisterTopic) {
        this.titulo=datesRegisterTopic.titulo();
        this.mensaje= datesRegisterTopic.mensaje();
        this.fecha_creacion= datesRegisterTopic.fecha_creacion();
        this.estatus=datesRegisterTopic.estatus();
        this.user=datesRegisterTopic.user();
        this.curso= datesRegisterTopic.curso();
    }
}
