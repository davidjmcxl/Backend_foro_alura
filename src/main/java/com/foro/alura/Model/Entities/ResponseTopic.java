package com.foro.alura.Model.Entities;

import com.foro.alura.Model.DTO.RegisterAndUpdateResponse;
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

    public ResponseTopic(RegisterAndUpdateResponse responseTopic) {
        this.response=responseTopic.response();
        this.fecha_creacion=responseTopic.fecha_creacion();
        this.topic=responseTopic.topic();
        this.user=responseTopic.user();
    }
}
