package com.example.springbootdemoproject.Model;
import com.example.springbootdemoproject.Model.CustomerOrder;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity //Marks this class as a database entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="users",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
        }) //Maps this class to the 'users' table in the database
public class User {



    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto incremented ID
    @Column(name="user_id")
    private Long id;

    @NotBlank
    @Size(max=20)
    @Column(name="username")
    private String name; // User's name

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CustomerOrder> orders;

    //roles
    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
            joinColumns =@JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles= new HashSet<>();


    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_addresses",
                    joinColumns=@JoinColumn(name="user_id"),
                    inverseJoinColumns =@JoinColumn(name="address_id"))
            private List<Address> addresses= new ArrayList<>();
    @OneToMany(mappedBy = "user",
                cascade={CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true)
    private Set<Product> products;
}

