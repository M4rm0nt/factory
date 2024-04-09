import java.math.BigDecimal;

public class HoheSteuer implements Steuer {
    @Override
    public BigDecimal berechneSteuersatz(Leistung leistung) {
        return leistung.getPreis().multiply(BigDecimal.valueOf(0.75));
    }
}