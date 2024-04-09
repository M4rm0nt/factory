import java.math.BigDecimal;
import java.math.RoundingMode;

public class Leistung {
    private final String beschreibung;
    private final BigDecimal preis;
    private Aufwand aufwand;
    private Steuer steuer;

    public Leistung(String beschreibung, BigDecimal preis) {
        this.beschreibung = beschreibung;
        this.preis = preis;
    }

    public Aufwand getAufwand() {
        return aufwand;
    }

    public void setAufwand(Aufwand aufwand) {
        this.aufwand = aufwand;
    }

    public void setSteuer(Steuer steuer) {
        this.steuer = steuer;
    }

    public BigDecimal getPreis() {
        return preis;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public BigDecimal berechneBetragInklAufwand() {
        return aufwand.berechneBetrag(this).setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal berechneSteuersatz() {
        return steuer.berechneSteuersatz(this).setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal berechneGesamtbetrag() {
        BigDecimal betragInklAufwand = berechneBetragInklAufwand();
        BigDecimal steuerBetrag = berechneSteuersatz();
        return preis.add(betragInklAufwand).add(steuerBetrag).setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String toString() {
        return "Leistung: \n" +
                "\tBeschreibung = '" + beschreibung + "',\n" +
                "\tPreis = " + preis + ",\n" +
                "\tAufwand = " + berechneBetragInklAufwand() + ",\n" +
                "\tSteuer = " + berechneSteuersatz() + ",\n" +
                "\tGesamtbetrag der Leistung inkl. Aufwand und Steuer = " + berechneGesamtbetrag() + "\n";
    }
}