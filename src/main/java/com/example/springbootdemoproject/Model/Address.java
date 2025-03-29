package com.example.springbootdemoproject.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;

    @NotBlank
    @Size(min = 5, message = "Street name must atleast 5 characters")
    private String street;

    @NotBlank
    @Size(min = 5, message = "Building name must atleast 5 characters")
    private String building;

    @NotBlank
    @Size(min = 5, message = "City name must atleast 5 characters")
    private String city;

    @NotBlank
    @Size(min = 5, message = "State name must atleast 5 characters")
    private String state;

    @NotBlank
    @Size(min = 5, message = "Country name must atleast 2 characters")
    private String country;

    @NotBlank
    @Size(min = 5, message = "Pincode  must atleast 6 characters")
    private String pincode;

    @Getter@Setter
    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses")
    private List<User> users = new ArrayList<>();

    public Address(long addressId, String street, String building, String city, String state, String country, String pincode) {
        this.addressId = addressId;
        this.street = street;
        this.building = building;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }
}
