import java.math.BigDecimal;

public class LeistungFabrik {
    public static Leistung erstelleLeistung(String beschreibung, BigDecimal preis, Aufwand aufwand, Steuer steuer) {
        Leistung leistung = new Leistung(beschreibung, preis);
        leistung.setAufwand(aufwand);
        leistung.setSteuer(steuer);
        return leistung;
    }
}