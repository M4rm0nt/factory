import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        Rechnung rechnung = new Rechnung();

        Leistung beratung = LeistungFabrik.erstelleLeistung("Beratung", new BigDecimal("100.00"), new NormalerAufwand(), new NiedrigeSteuer());
        rechnung.addPosition(beratung);

        Leistung programmierung = LeistungFabrik.erstelleLeistung("Programmierung", new BigDecimal("200.00"), new ErhoehterAufwand(), new HoheSteuer());
        rechnung.addPosition(programmierung);

        Leistung schulung = LeistungFabrik.erstelleLeistung("Schulung", new BigDecimal("300.00"), new NormalerAufwand(), new NiedrigeSteuer());
        rechnung.addPosition(schulung);

        System.out.println(rechnung);
    }
}