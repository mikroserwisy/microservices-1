package pl.training.shop.payments.persistence;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Table(name = "properties")
@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
class PropertyEntity {

    @GeneratedValue
    @Id
    private Long id;
    @NonNull
    private String key;
    @NonNull
    private String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyEntity that = (PropertyEntity) o;
        return Objects.equals(id, that.id) && key.equals(that.key) && value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return 13;
    }
}
