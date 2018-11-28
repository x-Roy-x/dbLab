package com.roi.domain;

import com.roi.DTO.EntityInterface;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "good", schema = "lab_8_r")
public class Good implements EntityInterface {
    private Long id;
    private String name_of_good;
    private String country_of_manufacture;
    private Set<Shopper> shoppers = new HashSet<>();
    @Id
    @Column(name = "good_id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_of_good", nullable = false, length = 50)
    public String getName_of_good() {
        return name_of_good;
    }

    public void setName_of_good(String name_of_good) {
        this.name_of_good = name_of_good;
    }

    @Basic
    @Column(name = "country_of_manufacture", nullable = true, length = 50)
    public String getCountry_of_manufacture() {
        return country_of_manufacture;
    }

    public void setCountry_of_manufacture(String country_of_manufacture) {
        this.country_of_manufacture = country_of_manufacture;
    }


    @ManyToMany(targetEntity = Shopper.class, mappedBy="goods")
    public Set<Shopper> getShopperSet() {
        return shoppers;
    }

    public void setShopperSet(Set<Shopper> shoppers) {
        this.shoppers = shoppers;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Good that = (Good) o;
        return id == that.id &&
                Objects.equals(name_of_good, that.name_of_good) &&
                Objects.equals(country_of_manufacture, that.country_of_manufacture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name_of_good, country_of_manufacture);
    }
    @Override
    public String toString(){
        return "Id= " + id + ", name_of_good= " + name_of_good
                + ", country_of_manufacture= " + country_of_manufacture;
    }
}
