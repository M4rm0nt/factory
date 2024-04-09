import java.math.BigDecimal;

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