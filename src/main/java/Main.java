import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface Aufwand {
    BigDecimal berechneBetrag(Leistung leistung);
}

class NormalerAufwand implements Aufwand {
    @Override
    public BigDecimal berechneBetrag(Leistung leistung) {
        return leistung.getPreis().multiply(BigDecimal.valueOf(0.5));
    }
}

class ErhoehterAufwand implements Aufwand {
    @Override
    public BigDecimal berechneBetrag(Leistung leistung) {
        return leistung.getPreis().multiply(BigDecimal.valueOf(1.5));
    }
}

@FunctionalInterface
interface Steuer {
    BigDecimal berechneSteuersatz(Leistung leistung);
}

class NiedrigeSteuer implements Steuer {
    @Override
    public BigDecimal berechneSteuersatz(Leistung leistung) {
        return leistung.getPreis().multiply(BigDecimal.valueOf(0.2));
    }
}

class HoheSteuer implements Steuer {
    @Override
    public BigDecimal berechneSteuersatz(Leistung leistung) {
        return leistung.getPreis().multiply(BigDecimal.valueOf(0.75));
    }
}

class Leistung {
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

class Rechnung {
    private final List<Leistung> leistungen = new ArrayList<>();

    public void addPosition(Leistung leistung) {
        this.leistungen.add(leistung);
    }

    public BigDecimal getGesamtsumme() {
        BigDecimal gesamtsumme = BigDecimal.ZERO;
        for (Leistung leistung : leistungen) {
            gesamtsumme = gesamtsumme.add(leistung.berechneGesamtbetrag());
        }
        return gesamtsumme.setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getGesamtsummeOhneAufwand() {
        BigDecimal gesamtsumme = BigDecimal.ZERO;
        for (Leistung leistung : leistungen) {
            gesamtsumme = gesamtsumme.add(leistung.getPreis());
        }
        return gesamtsumme.setScale(2, RoundingMode.HALF_EVEN);
    }

    public List<Leistung> getLeistungen() {
        return leistungen;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Rechnung:\n");
        for (Leistung leistung : leistungen) {
            sb.append("\t").append(leistung).append("\n");
        }
        sb.append("Gesamtsumme ohne Aufwand: ").append(getGesamtsummeOhneAufwand()).append("\n");
        sb.append("Gesamtsumme inklusive Aufwand: ").append(getGesamtsumme()).append("\n");
        return sb.toString();
    }
}

class LeistungFactory {
    public static Leistung createLeistung(String beschreibung, BigDecimal preis, Aufwand aufwand, Steuer steuer) {
        Leistung leistung = new Leistung(beschreibung, preis);
        leistung.setAufwand(aufwand);
        leistung.setSteuer(steuer);
        return leistung;
    }
}

public class Main {
    public static void main(String[] args) {
        Rechnung rechnung = new Rechnung();

        Leistung beratung = LeistungFactory.createLeistung("Beratung", new BigDecimal("100.00"), new NormalerAufwand(), new NiedrigeSteuer());
        rechnung.addPosition(beratung);

        Leistung programmierung = LeistungFactory.createLeistung("Programmierung", new BigDecimal("200.00"), new ErhoehterAufwand(), new HoheSteuer());
        rechnung.addPosition(programmierung);

        Leistung schulung = LeistungFactory.createLeistung("Schulung", new BigDecimal("300.00"), new NormalerAufwand(), new NiedrigeSteuer());
        rechnung.addPosition(schulung);

        for (Leistung leistung : rechnung.getLeistungen()) {
            if (leistung.getAufwand() == null) {
                throw new IllegalStateException("Aufwand für Leistung '" + leistung.getBeschreibung() + "' ist nicht festgelegt.");
            }
        }

        System.out.println(rechnung);
    }
}