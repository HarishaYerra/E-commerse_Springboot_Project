package com.example.springbootdemoproject.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="category")
@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max=20)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Product> products= new ArrayList<>();

    @ManyToOne
    //@JoinColumn(name = "order_id")  // Foreign Key in category table
    @JsonBackReference // Helps with JSON serialization
    private CustomerOrder order;

//    @ManyToMany(mappedBy ="categories")
//    //@JoinColumn(name = "order_id")  // Foreign Key in category table
//    //@JsonBackReference // Helps with JSON serialization
//    @JsonIgnore
//    private List<CustomerOrder> order = new ArrayList<>();
}

