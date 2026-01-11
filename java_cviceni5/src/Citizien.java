import java.io.Serializable;
//Musim naimplementovat, abz to slo serializovat do souboreu
public class Citizien implements Serializable {
    String name, surname, street, postcode, city;

    public Citizien(String[] information) {
        this.name = information[0];
        this.surname = information[1];
        this.street = information[2];
        this.postcode = information[3];
        this.city = information[4];
    }

    @Override
    public String toString() {
        return "Citizien{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", street='" + street + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
