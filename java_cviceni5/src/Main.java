import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() throws FileNotFoundException {
    File file = new File("adresy.csv");
    File file2 = new File("adresy.dat");
    FileOperations.saveCitizensToFile(file, file2);
    FileOperations.printCitizensFromFile(file2);



    //cv6
    /*String ext = askForText("Pripona souboru bro");
    File dir = FileOperations.chooseDirectory();
    System.out.println(FileOperations.getTotalSizeOfFilesByExtension(dir, ext));*/

    Osoba.ExtraNevhodnaOsoba[] pole = new Osoba.ExtraNevhodnaOsoba[100000];

    // Naplnění pole instancemi
    for (int i = 0; i < pole.length; i++) {
        pole[i] = new Osoba.ExtraNevhodnaOsoba();
    }

    System.out.println("Pole naplněno 100 000 instancemi ExtraNevhodnaOsoba.");
    System.out.println("HashCode prvního prvku: " + pole[8].hashCode()); // Vypíše 5

}
public static String askForText(String message) {
    return JOptionPane.showInputDialog(null, message, "Zadejte text", JOptionPane.PLAIN_MESSAGE);
}


public static void showResult(long size) {
    JOptionPane.showMessageDialog(null,
            "Celková velikost souborů: " + size + " bajtů.",
            "Výsledek",
            JOptionPane.INFORMATION_MESSAGE);
}
