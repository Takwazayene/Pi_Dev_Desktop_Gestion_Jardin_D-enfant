package gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TransactionController {

    private String cardType;
    @FXML
    private RadioButton visaRadio;

    @FXML
    private RadioButton masterCardRadio;

    @FXML
    private RadioButton amexRadio;

    @FXML
    private RadioButton discoveryRadio;

    @FXML
    private TextField creditNumField;

    @FXML
    private TextField monthField;

    @FXML
    private TextField yearField;

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField billingAddress1Field;

    @FXML
    private TextField billingAddress2Field;

    @FXML
    private TextField cityField;

    @FXML
    private TextField stateField;

    @FXML
    private TextField zipField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button payButton;

    @FXML
    private Label verifLabel;
    @FXML
    private Button goToShop;

    void CancelButtontoLogin(ActionEvent event) {

    }

    @FXML
    void pay(ActionEvent event) {

    }

  public void showAlert(String alertString) {
		Alert errorAlert = new Alert(Alert.AlertType.ERROR);
		errorAlert.setTitle("Form Error!");
		errorAlert.setHeaderText(alertString);
		errorAlert.show();
	}

    @FXML
	public void selectVisaRadio(ActionEvent event) {
		masterCardRadio.setSelected(false);
		amexRadio.setSelected(false);
		discoveryRadio.setSelected(false);
		cardType = "Visa";
                verifLabel.setText("Payment Par Visa Séléctionné ! ");
		return;
	}

    @FXML
	public void selectMasterRadio(ActionEvent event) {
		visaRadio.setSelected(false);
		amexRadio.setSelected(false);
		discoveryRadio.setSelected(false);
		cardType = "MasterCard";
                verifLabel.setText("Payment Par MasterCard Séléctionné ! ");

		return;
	}

    @FXML
	public void selectAmericanRadio(ActionEvent event) {
		visaRadio.setSelected(false);
		masterCardRadio.setSelected(false);
		discoveryRadio.setSelected(false);
		cardType = "AMex";
                verifLabel.setText("Payment Par Amex Séléctionné ! ");

		return;
	}

    @FXML
	public void selectDiscoveryRadio(ActionEvent event) {
		visaRadio.setSelected(false);
		masterCardRadio.setSelected(false);
		amexRadio.setSelected(false);
		cardType = "Discovery";
                verifLabel.setText("Payment Par Discovery Séléctionné ! ");

		return;
	}

    @FXML
    private void goToShopAction(ActionEvent event) throws IOException {
         Parent root;
        root = FXMLLoader.load(getClass().getResource("ShoppingCart.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();

    }

}
