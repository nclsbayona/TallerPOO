package boundaries;

import java.io.File;
import static control.CSVtoXML.convert;
import static control.CSVtoXML.setBoth;
import javax.swing.JFileChooser;

public class Main {
    private static final String XMLNAME = "archivoXML";
    public static void main(String[] args) {
        try {
            String[] csvHeaders = { "Title", "Genres", "Release Date", "Release Country", "Movie Rating",
                    "Review Rating", "Movie Run Time", "Plot", "Cast", "Language", "Filming Locations", "Budget" };
            File csv = null;
    
            JFileChooser fchooser = new JFileChooser();
            fchooser.setDialogTitle("Select CSV archive");
            fchooser.setCurrentDirectory(new File("."));
            if (fchooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                csv=fchooser.getSelectedFile();
            // JFrame
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Select destination folder");
            chooser.setCurrentDirectory(new File("."));
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
    
                setBoth(String.valueOf(csv), String.valueOf(chooser.getSelectedFile())+'/'+Main.XMLNAME);
                convert(csvHeaders);
            }
        } catch (Exception e) {
            System.err.println("Error en Main\n\n" + e.getLocalizedMessage() + '\n');
            e.printStackTrace();
        } finally {
            System.out.println("Done");
            System.exit(0);
        }
    }
}
