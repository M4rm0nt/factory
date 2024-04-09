import java.math.BigDecimal;

public class ErhoehterAufwand implements Aufwand {
    @Override
    public BigDecimal berechneBetrag(Leistung leistung) {
        return leistung.getPreis().multiply(BigDecimal.valueOf(1.5));
    }
}