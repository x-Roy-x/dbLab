package com.roi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.roi.DTO.EntityInterface;

import javax.persistence.*;
import java.util.Set;
import java.util.Objects;

@Entity
@Table(name = "password_of_shopper", schema = "lab_8_r")
public class PasswordOfShopper implements EntityInterface {
    private Long id;
    private int passwordOfShopper;

    public PasswordOfShopper(Long id, int password) {
        this.id = id;
        this.passwordOfShopper = password;
    }

    private Set<Shopper> shopperByPasswordOfShopper;

    @Id
    @Column(name = "password_of_shopper_id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PasswordOfShopper(Long id) {
        this.id = id;
    }

    public PasswordOfShopper() {
    }

    @Basic
    @Column(name = "password_of_shopper", nullable = true)
    public int getPasswordOfShopper() {
        return passwordOfShopper;
    }

    public void setPasswordOfShopper(int passwordOfShopper) {
        this.passwordOfShopper = passwordOfShopper;
    }


    @OneToMany(
            mappedBy = "passwordByPassword",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    public Set<Shopper> getShopper() {
        return shopperByPasswordOfShopper;
    }
    public void setShopper(Set<Shopper> shopperByPassword)
    {
        this.shopperByPasswordOfShopper = shopperByPassword;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasswordOfShopper that = (PasswordOfShopper) o;
        return id == that.id &&
                Objects.equals(passwordOfShopper, that.passwordOfShopper);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passwordOfShopper);
    }
    @Override
    public String toString() {
        return "PasswordOfShopper{" +
                "id=" + id +
                ", passwordOfShopper='" + passwordOfShopper + '\'' +
                '}';
    }

}
