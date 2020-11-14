package control;

import static control.AyudaUI.setXmlTitle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SelectorUIControl {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane bigBoy;

    @FXML
    private Button submitRouteButton;

    @FXML
    private TextField txtField;

    @FXML
    void submitRoute(ActionEvent event) {
        setXmlTitle(txtField.getText());
    }

    @FXML
    void initialize() {
        assert bigBoy != null : "fx:id=\"bigBoy\" was not injected: check your FXML file 'Untitled'.";
        assert submitRouteButton != null
                : "fx:id=\"submitRouteButton\" was not injected: check your FXML file 'Untitled'.";
        assert txtField != null : "fx:id=\"txtField\" was not injected: check your FXML file 'Untitled'.";
    }
}
