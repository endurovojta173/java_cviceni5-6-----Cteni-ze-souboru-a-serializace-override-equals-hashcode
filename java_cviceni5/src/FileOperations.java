import java.io.*;


public class FileOperations {
    static boolean isExistingReadableFile(File f){
        return f.exists() && f.isFile() && f.canRead();
    }

    public FileOperations() {
    }

    static public boolean saveCitizensToFile(File inputFile, File outputFile){
        if(isExistingReadableFile(inputFile)){
            try(
                //Textovy vstup z csv
                BufferedReader br = new BufferedReader(new FileReader(inputFile));
                //Binarni vystup (serializace)
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outputFile));
            )
            {
                String line;
                while ((line = br.readLine()) != null){
                    String[] parts = line.split(";");
                    if(parts.length == 5){
                        parts[0] = parts[0].trim();
                        parts[1] = parts[1].trim();
                        parts[2] = parts[2].trim();
                        parts[3] = parts[3].trim();
                        parts[4] = parts[4].trim();
                        Citizien citizien = new Citizien(parts);
                        //Serializace - zapsani objektu do souboru
                        out.writeObject(citizien);
                    }
                }
                return true;
            }
            catch (IOException | NumberFormatException e){
                System.err.println(e.getMessage());
                return false;
            }
        }
        System.err.println("Chyba pri nacitano souboru");
        return false;
    }

    static public void printCitizensFromFile(File binaryFile){
        if(isExistingReadableFile(binaryFile)){
            try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(binaryFile))) {
            while(true){
                Citizien c = (Citizien) input.readObject();
                System.out.println(c.toString());
            }

            } catch (EOFException e) {
                System.out.println("--- Konec souboru ---");
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        return;
    }
}
