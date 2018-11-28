package com.roi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.roi.DTO.EntityInterface;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "shopper", schema = "lab_8_r")
public class Shopper implements EntityInterface {
    private Long id;
    private String name_of_shopper;
    private String surname_of_shopper;
    private PasswordOfShopper passwordByPassword;
    Set<Good> goods = new HashSet<>();


    public Shopper(String name, String surname, PasswordOfShopper passwordByPassword) {
        this.name_of_shopper = name;
        this.surname_of_shopper = surname;
        this.passwordByPassword = passwordByPassword;
    }

    public Shopper(Long id, String name_of_shopper, String surname_of_shopper) {
        this.id = id;
        this.name_of_shopper = name_of_shopper;
        this.surname_of_shopper = surname_of_shopper;
    }

    public Shopper() {
    }


    @Id
    @Column(name = "shopper_id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_of_shopper", nullable = true, length = 50)
    public String getName_of_shopper() {
        return name_of_shopper;
    }

    public void setName_of_shopper(String name_of_shopper) {
        this.name_of_shopper = name_of_shopper;
    }

    @Basic
    @Column(name = "surname_of_shopper", nullable = true, length = 50)
    public String getSurname_of_shopper() {
        return surname_of_shopper;
    }

    public void setSurname_of_shopper(String surname_of_shopper) {
        this.surname_of_shopper = surname_of_shopper;
    }

    @ManyToOne
    @JoinColumn(name = "password_of_shopper_id", referencedColumnName = "password_of_shopper_id", nullable = false)
    @JsonIgnore
    public PasswordOfShopper getPasswordByPassword() {
        return passwordByPassword;
    }

    public void setPasswordByPassword(PasswordOfShopper passwordByPassword) {
        this.passwordByPassword = passwordByPassword;
    }


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "shopper_good",
            joinColumns = { @JoinColumn(name = "shopper_id", referencedColumnName = "shopper_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "good_id", referencedColumnName = "good_id", nullable = false), }
    )
    @JsonIgnore
    public Set<Good> getGoods() {
        return goods;
    }

    public void setGoods(Set<Good> goods) {
        this.goods = goods;
    }

    public void addShopperGood(Good customerEntity){
        if(!getGoods().contains(customerEntity)){
            getGoods().add(customerEntity);
        }
        if(!customerEntity.getShopperSet().contains(this)){
            customerEntity.getShopperSet().add(this);
        }
    }

    public void deleteGoodEntity(Good good){
        if(getGoods().contains(good)){
            getGoods().remove(good);
        }
        if(good.getShopperSet().contains(this)){
            good.getShopperSet().remove(this);
        }
    }



    @Override
    public String toString() {
        return "ShopperEntity{" +
                "id=" + id +
                ", name_of_shopper='" + name_of_shopper + '\'' +
                ", surname_of_shopper='" + surname_of_shopper + '\'' +
                ", goods=" + goods +
                '}';
    }

    public String toStringJoinTable(){
        return "MobileEntity{" +
                "id=" + id +
                " goods=" + goods +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shopper that = (Shopper) o;
        return id == that.id &&
                Objects.equals(name_of_shopper, that.name_of_shopper) &&
                Objects.equals(surname_of_shopper, that.surname_of_shopper);}


    @Override
    public int hashCode() {
        return Objects.hash(id, name_of_shopper, surname_of_shopper);
    }



}
