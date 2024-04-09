import java.math.BigDecimal;

public class NormalerAufwand implements Aufwand {
    @Override
    public BigDecimal berechneBetrag(Leistung leistung) {
        return leistung.getPreis().multiply(BigDecimal.valueOf(0.5));
    }
}
