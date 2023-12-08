package com.example.cursach.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "variants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Variants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @ManyToOne
//            (cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private Question question;

}
