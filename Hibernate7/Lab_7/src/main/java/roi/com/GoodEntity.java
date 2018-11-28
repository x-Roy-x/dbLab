package roi.com;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Good", schema = "lab_6_r", catalog = "")
public class GoodEntity {
    private int goodId;
    private String nameOfGood;
    private String countyOfManufacture;
    private String price;
    private List<ShopperEntity> shopperEntities;

    public GoodEntity() {
    }

    public GoodEntity(int goodId, String nameOfGood, String countryOfManufacture, String price, List<ShopperEntity> shopperEntities) {
        this.goodId = goodId;
        this.nameOfGood = nameOfGood;
        this.countyOfManufacture = countryOfManufacture;
        this.price = price;
        this.shopperEntities = shopperEntities;
    }

    @Id
    @Column(name = "good_id", nullable = false)
    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    @Basic
    @Column(name = "name_of_good", nullable = false, length = 45)
    public String getNameOfGood() {
        return nameOfGood;
    }

    public void setNameOfGood(String nameOfGood) {
        this.nameOfGood = nameOfGood;
    }

    @Basic
    @Column(name = "country_of_manufacture", nullable = false, length = 45)
    public String getCountyOfManufacture() {
        return countyOfManufacture;
    }

    public void setCountyOfManufacture(String countyOfManufacture) {
        this.countyOfManufacture = countyOfManufacture;
    }

    @Basic
    @Column(name = "price", nullable = false, length = 45)
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodEntity that = (GoodEntity) o;

        if (goodId != that.goodId) return false;
        if (nameOfGood != null ? !nameOfGood.equals(that.nameOfGood) : that.nameOfGood != null) return false;
        if (countyOfManufacture != null ? !countyOfManufacture.equals(that.countyOfManufacture) : that.countyOfManufacture != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goodId;
        result = 31 * result + (nameOfGood != null ? nameOfGood.hashCode() : 0);
        result = 31 * result + (countyOfManufacture != null ? countyOfManufacture.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @ManyToMany
    @JoinTable(name = "shopper_and_good", catalog = "", schema = "lab_6_r",
            joinColumns = @JoinColumn(name = "good_id", referencedColumnName = "good_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "shopper_id", referencedColumnName = "shopper_id", nullable = false))
    public List<ShopperEntity> getShopperEntities() {
        return shopperEntities;
    }

    public void setShopperEntities(List<ShopperEntity> shopperEntities) {
        this.shopperEntities = shopperEntities;
    }

    @Override
    public String toString() {
        return "GoodEntity{" +
                "goodId=" + goodId +
                ", nameOfGood='" + nameOfGood + '\'' +
                ", countyOfManufacture='" + countyOfManufacture + '\'' +
                ", price='" + price + '\'' +
                ", shopperEntities=" + shopperEntities +
                '}';
    }
}
