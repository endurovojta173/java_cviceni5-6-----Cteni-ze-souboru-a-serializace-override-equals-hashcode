import java.util.Objects;
import java.util.Random;

public class Osoba {
    String jmeno;
    boolean muz;
    int vyska;

    public Osoba() {
        Random rand = new Random();
        this.muz = rand.nextBoolean();
        //150-200
        this.vyska = 150 + rand.nextInt(51);
        //2-5
        int delkaJmena = 2 + rand.nextInt(4);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < delkaJmena; i++) {
            sb.append((char) ('a' + rand.nextInt(26)));
        }
        this.jmeno = sb.toString();
    }

    @Override
    public String toString() {
        return "Osoba{" +
                "jmeno='" + jmeno + '\'' +
                ", muz=" + muz +
                ", vyska=" + vyska +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Osoba)) return false;
        Osoba other = (Osoba) o;
        return this.jmeno.equals(other.jmeno) &&
                this.muz == other.muz &&
                this.vyska == other.vyska;

    }
    //hashcody
    static class ExtraNevhodnaOsoba extends Osoba {
        @Override
        public int hashCode() {
            return 5;
        }
    }

    class NevhodnaOsoba extends Osoba {
        @Override
        public int hashCode() {
            return this.vyska;
        }
    }

    class OsobaBezHashCode extends Osoba {
    }

    class PrijatelnaOsoba extends Osoba {
        @Override
        public int hashCode() {
            return this.vyska * this.jmeno.length();
        }
    }

    class PerfektniOsoba extends Osoba {
        @Override
        public int hashCode() {
            int vysledek = this.muz ? 1 : 0;
            vysledek = 31 * vysledek + vyska;
            vysledek = 31 * vysledek + jmeno.hashCode();
            return vysledek;
        }
    }
    //vrací výsledek volání statické metody hash třídy Objects, které předá hodnoty všem proměnných objektu.
    class ObjectHashOsoba extends Osoba {
        @Override
        public int hashCode() {
            return Objects.hash(this.jmeno, this.muz, this.vyska);
        }
    }

    //vrací výsledek volání statické metody hashCode třídy Objects, které předá odkaz na vlastní objekt.
    class ObjectsHashCodeOsoba extends Osoba {
        @Override
        public int hashCode() {
            return Objects.hash(this);
        }
    }
}
