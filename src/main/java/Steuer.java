import java.math.BigDecimal;

@FunctionalInterface
public interface Steuer {
    BigDecimal berechneSteuersatz(Leistung leistung);
}