//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() throws FileNotFoundException {
    File file = new File("adresy.csv");
    File file2 = new File("adresy.dat");
    FileOperations.saveCitizensToFile(file, file2);
    FileOperations.printCitizensFromFile(file2);

}
