package com.example.springbootdemoproject.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="product")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;

    @ManyToOne
    //@JoinColumn(name="category_id",nullable = false)
    @JsonBackReference
    private Category category;

    @ManyToOne
    @JoinColumn(name="seller_id")
    private User user;
}

