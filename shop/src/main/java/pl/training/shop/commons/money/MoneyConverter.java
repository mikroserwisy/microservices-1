package pl.training.shop.commons.money;

import org.javamoney.moneta.FastMoney;
import pl.training.shop.commons.LocalMoney;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<FastMoney, String> {

    @Override
    public String convertToDatabaseColumn(FastMoney value) {
        return value != null ? value.toString() : null;
    }

    @Override
    public FastMoney convertToEntityAttribute(String value) {
        return value != null ? FastMoney.parse(value) : LocalMoney.zero();
    }

}
