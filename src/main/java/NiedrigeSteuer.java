import java.math.BigDecimal;

public class NiedrigeSteuer implements Steuer {
    @Override
    public BigDecimal executeBerechneSteuersatz(Leistung leistung) {
        return leistung.getPreis().multiply(BigDecimal.valueOf(0.2));
    }
}