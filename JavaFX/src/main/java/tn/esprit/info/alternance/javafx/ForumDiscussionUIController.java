package tn.esprit.info.alternance.javafx;
import khadamni.gui.*;
import khadamni.modeles.Commentaire;
import khadamni.utiles.Connection;
import khadamni.modeles.Publication;
import khadamni.utiles.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import java.util.Date;

import static java.sql.DriverManager.getConnection;

public class ForumDiscussionUIController {



    @FXML
    private Button addComment;

    @FXML
    private Button addPub;

    @FXML
    private TextArea comment;

    @FXML
    private TextArea contentPub;

    @FXML
    private TextField titrePub;

    @FXML
    void addCommentToPub(ActionEvent event) {
        Connection myConnection = new Connection();
        java.sql.Connection jdbcConnection = myConnection.getConnection();
        String content = comment.getText();

        if (content.isEmpty()) {
            showAlert("Title and content cannot be empty!");
        } else {
            Commentaire newCommentaire = new Commentaire(content,new Date(),1);
            int commentaireId = CommentaireGui.createCommentaire(jdbcConnection, newCommentaire);
            myConnection.closeConnection();
            showAlert(commentaireId != -1 ? "Publication created with ID: " + commentaireId : "Failed to create the publication.");
        }
    }

    @FXML
    void addPublication(ActionEvent event) {
        Connection myConnection = new Connection();
        java.sql.Connection jdbcConnection = myConnection.getConnection();

        String title = titrePub.getText();
        String content = contentPub.getText();

        if (title.isEmpty() || content.isEmpty()) {
            showAlert("Title and content cannot be empty!");
        } else {
            int clientId = 1;
            int freelancerId = 1;
            Publication newPublication = new Publication(title, content, new Date(), clientId, freelancerId);
            int publicationId = PublicationGui.createPublication(jdbcConnection, newPublication);
            myConnection.closeConnection();
            showAlert(publicationId != -1 ? "Publication created with ID: " + publicationId : "Failed to create the publication.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        assert addComment != null : "fx:id=\"addComment\" was not injected: check your FXML file 'ForumDiscussionUI.fxml'.";
        assert addPub != null : "fx:id=\"addPub\" was not injected: check your FXML file 'ForumDiscussionUI.fxml'.";
        assert comment != null : "fx:id=\"comment\" was not injected: check your FXML file 'ForumDiscussionUI.fxml'.";
        assert contentPub != null : "fx:id=\"contentPub\" was not injected: check your FXML file 'ForumDiscussionUI.fxml'.";
        assert titrePub != null : "fx:id=\"titrePub\" was not injected: check your FXML file 'ForumDiscussionUI.fxml'.";
    }
}
