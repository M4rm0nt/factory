import java.math.BigDecimal;

@FunctionalInterface
public interface Steuer {
    BigDecimal executeBerechneSteuersatz(Leistung leistung);
}