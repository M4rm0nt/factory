import java.math.BigDecimal;

@FunctionalInterface
public interface Aufwand {
    BigDecimal berechneBetrag(Leistung leistung);
}