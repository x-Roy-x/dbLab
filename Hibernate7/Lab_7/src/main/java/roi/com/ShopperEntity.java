package roi.com;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shopper", schema = "lab_6_r", catalog = "")
public class ShopperEntity {
    private int shopperId;
    private String surnameOfShopper;
    private String nameOfShopper;
    private String ageOfShopper;
    private List<GoodEntity> goodEntities;
    private PasswordOfShopperEntity passwordOfShopperByPasswordOfShopper;

    public ShopperEntity() {
    }

    public ShopperEntity(String surnameOfShopper, String nameOfShopper, String ageOfShopper, PasswordOfShopperEntity passwordOfShopperEntity) {
       // this.shopperId = shopperId;
        this.surnameOfShopper = surnameOfShopper;
        this.nameOfShopper = nameOfShopper;
        this.ageOfShopper = ageOfShopper;
        this.passwordOfShopperByPasswordOfShopper = passwordOfShopperEntity;
    }

    @Id
    @Column(name = "shopper_id", nullable = false)
    public int getShopperId() {
        return shopperId;
    }

    public void setShopperId(int shopperId) {
        this.shopperId = shopperId;
    }

    @Basic
    @Column(name = "surname_of_shopper", nullable = false, length = 25)
    public String getSurnameOfShopper() {
        return surnameOfShopper;
    }

    public void setSurnameOfShopper(String surnameOfShopper) {
        this.surnameOfShopper = surnameOfShopper;
    }

    @Basic
    @Column(name = "name_of_shopper", nullable = false, length = 25)
    public String getNameOfShopper() {
        return nameOfShopper;
    }

    public void setNameOfShopper(String nameOfShopper) {
        this.nameOfShopper = nameOfShopper;
    }

    @Basic
    @Column(name = "age_of_shopper", nullable = true, length = 45)
    public String getAgeOfShopper() {
        return ageOfShopper;
    }

    public void setAgeOfShopper(String ageOfShopper) {
        this.ageOfShopper = ageOfShopper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopperEntity that = (ShopperEntity) o;

        if (shopperId != that.shopperId) return false;
        if (surnameOfShopper != null ? !surnameOfShopper.equals(that.surnameOfShopper) : that.surnameOfShopper != null) return false;
        if (nameOfShopper != null ? !nameOfShopper.equals(that.nameOfShopper) : that.nameOfShopper != null) return false;
        if (ageOfShopper != null ? !ageOfShopper.equals(that.ageOfShopper) : that.ageOfShopper != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = shopperId;
        result = 31 * result + (surnameOfShopper != null ? surnameOfShopper.hashCode() : 0);
        result = 31 * result + (nameOfShopper != null ? nameOfShopper.hashCode() : 0);
        result = 31 * result + (ageOfShopper != null ? ageOfShopper.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "password_of_shopper", referencedColumnName = "password_of_shopper", nullable = false)
    public PasswordOfShopperEntity getPasswordOfShopperByPasswordOfShopper() {
        return passwordOfShopperByPasswordOfShopper;
    }

    public void setPasswordOfShopperByPasswordOfShopper(PasswordOfShopperEntity passwordOfShopperByPasswordOfShopper) {
        this.passwordOfShopperByPasswordOfShopper = passwordOfShopperByPasswordOfShopper;
    }

    @ManyToMany(mappedBy = "shopperEntities")
    public List<GoodEntity> getGoodEntities() {
        return goodEntities;
    }

    public void addLecturerEntity(GoodEntity lecturerEntity){
        if(!getGoodEntities().contains(lecturerEntity)){
            getGoodEntities().add(lecturerEntity);
        }
        if(!lecturerEntity.getShopperEntities().contains(this)){
            lecturerEntity.getShopperEntities().add(this);
        }
    }

    public void deleteLecturerEntity(GoodEntity lecturerEntity){
        if(getGoodEntities().contains(lecturerEntity)){
            getGoodEntities().remove(lecturerEntity);
        }
        if(lecturerEntity.getShopperEntities().contains(this)){
            lecturerEntity.getShopperEntities().remove(this);
        }
    }

    public void setGoodEntities(List<GoodEntity> goodEntities) {
        this.goodEntities = goodEntities;
    }

    @Override
    public String toString() {
        return "ShopperEntity{" +
                "shopperId=" + shopperId +
                ", goodId=" + goodEntities.get(0).getGoodId() +
                '}';
    }
}
