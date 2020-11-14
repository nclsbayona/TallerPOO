package boundaries;

import java.io.File;

import javax.swing.JFileChooser;

import static control.CSVtoXML.setBoth;
import static control.CSVtoXML.convert;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainJavaFX extends Application {
    private static Scene scene;
    private static final String XMLNAME = "archivoXML";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            String[] csvHeaders = { "Title", "Genres", "Release Date", "Release Country", "Movie Rating",
                    "Review Rating", "Movie Run Time", "Plot", "Cast", "Language", "Filming Locations", "Budget" };
            File csv = null;
            String nomFXML = "UIVacia";
            scene = new Scene(LoadFXML(nomFXML));

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open CSV File");
            csv = fileChooser.showOpenDialog(primaryStage);
            // JFrame
            /*
             * FileChooser fileChooser2 = new FileChooser();
             * fileChooser2.setTitle("Select Directory"); File
             * xml=fileChooser2.showOpenDialog(primaryStage);
             */
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Select destination directory");
            chooser.setCurrentDirectory(new File("."));
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                /*
                 * primaryStage.setScene(scene); primaryStage.show();
                 */
                setBoth(String.valueOf(csv), String.valueOf(chooser.getSelectedFile())+'/'+MainJavaFX.XMLNAME);
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

    static void setRoot(String fxml) throws Exception {
        scene.setRoot(LoadFXML(fxml));
    }

    private static Parent LoadFXML(String fxml) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainJavaFX.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
