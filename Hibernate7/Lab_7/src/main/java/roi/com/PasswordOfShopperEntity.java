package roi.com;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "password_of_shopper", schema = "lab_6_r", catalog = "")
public class PasswordOfShopperEntity {
    private String password_of_shopper;

    public PasswordOfShopperEntity() {
    }

    public PasswordOfShopperEntity(String nameOfGroup) {
        this.password_of_shopper = nameOfGroup;
    }

    @Id
    @Column(name = "password_of_shopper", nullable = false, length = 25)
    public String getPassword_of_shopper() {
        return password_of_shopper;
    }

    public void setPassword_of_shopper(String password_of_entity) {
        this.password_of_shopper = password_of_entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PasswordOfShopperEntity that = (PasswordOfShopperEntity) o;

        if (password_of_shopper != null ? !password_of_shopper.equals(that.password_of_shopper) : that.password_of_shopper != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return password_of_shopper != null ? password_of_shopper.hashCode() : 0;
    }
}
