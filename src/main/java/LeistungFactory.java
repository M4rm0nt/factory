import java.math.BigDecimal;

public class LeistungFactory {
    public static Leistung createLeistung(String beschreibung, BigDecimal preis, Aufwand aufwand, Steuer steuer) {
        Leistung leistung = new Leistung(beschreibung, preis);
        leistung.setAufwand(aufwand);
        leistung.setSteuer(steuer);
        return leistung;
    }
}