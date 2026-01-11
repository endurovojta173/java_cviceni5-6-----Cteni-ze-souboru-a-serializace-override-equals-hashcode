import javax.swing.*;
import java.io.*;


public class FileOperations {
    static boolean isExistingReadableFile(File f) {
        return f.exists() && f.isFile() && f.canRead();
    }

    public FileOperations() {
    }

    static public boolean saveCitizensToFile(File inputFile, File outputFile) {
        if (isExistingReadableFile(inputFile)) {
            try (
                    //Textovy vstup z csv
                    BufferedReader br = new BufferedReader(new FileReader(inputFile));
                    //Binarni vystup (serializace)
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outputFile));
            ) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length == 5) {
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
            } catch (IOException | NumberFormatException e) {
                System.err.println(e.getMessage());
                return false;
            }
        }
        System.err.println("Chyba pri nacitano souboru");
        return false;
    }

    static public void printCitizensFromFile(File binaryFile) {
        if (isExistingReadableFile(binaryFile)) {
            try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(binaryFile))) {
                while (true) {
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

    static long getTotalSizeOfFilesByExtension(File dir, String ext) {
        if (!dir.isDirectory()) throw new IllegalArgumentException("Parametr dir neni adresar");
        if (!dir.exists()) throw new IllegalArgumentException("Adresar neexistuje");
        if (!dir.canRead()) throw new IllegalArgumentException("Adresar neni citelny");
        if (dir == null) throw new IllegalArgumentException("Adresar neni adresar");
        if (ext == null) throw new IllegalArgumentException("Pripona je ko");
        long dirSize = 0;
        if (dir.listFiles() != null) {
            for (File file : dir.listFiles()) {
                if (file.getName().endsWith("." + ext)) {
                    dirSize += file.length();
                } else if (file.isDirectory()) {
                    // Pozor: Zde voláme tu samou metodu pro podadresář.
                    // Tím se znovu spustí i validace pro tento podadresář
                    // a následně se prohledá jeho obsah.
                    try {
                        dirSize += getTotalSizeOfFilesByExtension(file, ext);
                    } catch (IllegalArgumentException e) {
                        // Volitelné: Pokud podadresář nelze číst, rekurzivní volání vyhodí výjimku.
                        // Zde ji můžeme ignorovat a pokračovat dál, nebo nechat probublat nahoru.
                        // Dle zadání "vyhodí výjimku" nechávám bez try-catch (nebo ji znovu vyhodím),
                        // ale v praxi se často nečitelné složky jen přeskočí.
                        throw e;
                    }
                }
            }
        }
        return dirSize;
    }
    public static File chooseDirectory() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Vyberte složku");
        File selectedDir = null;
        while (selectedDir == null) {
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedDir = chooser.getSelectedFile();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Musíte vybrat složku, jinak nelze pokračovat.",
                        "Výběr složky",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        return selectedDir;
    }

}

