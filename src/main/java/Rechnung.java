import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Rechnung {
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

    public List<Leistung> getLeistungen() {
        return leistungen;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Rechnung:\n");
        for (Leistung leistung : leistungen) {
            sb.append("\t").append(leistung).append("\n");
        }
        sb.append("Gesamtsumme der Rechnung: ").append(getGesamtsumme()).append("\n");
        return sb.toString();
    }
}