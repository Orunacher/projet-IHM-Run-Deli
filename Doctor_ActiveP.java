
package presentation ;

import java.time.LocalDate ;
import java.util.List ;

import controle.Doctor_ActiveC ;
import controle.Patient_ActiveC ;
import javafx.beans.value.ChangeListener ;
import javafx.beans.value.ObservableValue ;
import javafx.collections.FXCollections ;
import javafx.collections.ObservableList ;
import javafx.event.ActionEvent ;
import javafx.event.EventHandler ;
import javafx.scene.Scene ;
import javafx.scene.control.Button ;
import javafx.scene.control.ChoiceBox ;
import javafx.scene.control.DatePicker ;
import javafx.scene.control.Label ;
import javafx.scene.control.RadioButton ;
import javafx.scene.control.TextField ;
import javafx.scene.control.ToggleGroup ;
import javafx.scene.layout.FlowPane ;
import javafx.scene.layout.GridPane ;
import javafx.scene.layout.HBox ;
import javafx.stage.Modality ;
import javafx.stage.Stage ;
//-------------------------------------------------------------------------------------
// classe de présentation active (éditable) d'un patient :
// elle hérite maintenant d'un Stage pour réduire le nombre de classes de présentation
//-------------------------------------------------------------------------------------

public class Doctor_ActiveP extends Stage {

   private TextField rppsField ;
   private TextField lastNameField ;
   private TextField firstNameField ;
   private TextField addressField ;
   private TextField phoneNumberField ;
   private TextField specialityField ;


   private Doctor_ActiveC control ;
   
   public Doctor_ActiveP (Doctor_ActiveC doctor_ActiveC, Stage application) {
      // la fenêtre est déclarée modale de façon à bloquer les interactions avec la fenêtre "mère"
      initModality(Modality.WINDOW_MODAL);
      // la fenêtre mère est la présentation principale de l'application
      initOwner (application) ;

      control = doctor_ActiveC ;
      
      // mise en place des composants de présentation : on lres remplira plus tard via un appel à la méthode update
      
      Label rppsTitle = new Label ("RPPS : ") ;
      Label lastNameTitle = new Label ("Last Name : ") ;
      Label firstNameTitle = new Label ("First Name : ") ;
      Label addressTitle = new Label ("Address : ") ;
      Label phoneNumberTitle = new Label ("PhoneNumber : ") ;
      Label specialityTitle = new Label ("Speciality : ") ;
      rppsField = new TextField () ;
      lastNameField = new TextField () ;
      firstNameField = new TextField () ;
      addressField = new TextField () ;
      phoneNumberField = new TextField () ;
      specialityField = new TextField () ;
      
      GridPane doctorPane = new GridPane () ;
      
      doctorPane.addRow (0, rppsTitle, rppsField);
      doctorPane.addRow (1, lastNameTitle, lastNameField);
      doctorPane.addRow (2, firstNameTitle, firstNameField);
      doctorPane.addRow (3, addressTitle, addressField);
      doctorPane.addRow (4, phoneNumberTitle, phoneNumberField);
      doctorPane.addRow (5, specialityTitle, specialityField);
     
      
      
      // Philippe TANGUY le 13/05/2019
      // ajout de ce qui manquait pour que la fenêtre s'affiche correctement.
      // Voir classe "Patient_ActiveP" ligne 140
      
      // Il vous reste à gérer les boutons de validtion et d'annulation...
      // Bon courage !
      
      // création du container global
      GridPane globalPane = new GridPane () ;
      // ajout de l'éditeur de docteur dans le container global
      globalPane.addRow (0, doctorPane) ;
      // ajout du container de boutons dans le container global 
//      globalPane.addRow (1,  buttons);
      // création de la scène globale à partir du container
      Scene scene = new Scene (globalPane) ;
      // définition de cette scène comme scène du composant stage
      setScene (scene) ;
      setTitle ("Edit Doctor") ;

   }
   
   public void update (String rpps, String lastName, String firstName, String address, String phoneNumber, String speciality) {

      rppsField.setText (rpps) ;
      lastNameField.setText (lastName) ;
      firstNameField.setText (firstName) ;
      addressField.setText (address) ;
      phoneNumberField.setText (phoneNumber) ;
      specialityField.setText (speciality) ;
      
   }

   //-------------------------------------------------------------------------------------
   // méthode de mise à jour du patient associé : on fait les transformations nécessaires
   // pour récupérer des éléments graphiques ce qui permet de mettre à jour un patient
   // et on les transmet au controle via une méthode dédiée 
   //-------------------------------------------------------------------------------------
   
   public String getRpps () {
      return (rppsField.getText ()) ;
   }
   
   public String getLastName () {
      return (lastNameField.getText ()) ;
   }
   
   public String getFirstName () {
      return (firstNameField.getText ()) ;
   }
   
   public String getAddress () {
      return (addressField.getText ()) ;
   }
   
   public String getPhoneNumber () {
      return (phoneNumberField.getText ()) ;
   }
   
   public String getSpeciality () {
      return (specialityField.getText ()) ;
   }
   
   

   //-------------------------------------------------------------------------------------
   // méthode relai vers le controle associé
   //-------------------------------------------------------------------------------------
   
   public Doctor_ActiveC getControl () {
      return control ;
   }
   
   //-------------------------------------------------------------------------------------
   // méthode appelée en cas d'abandon du dialogue : on se contente de fermer la fenêtre
   //-------------------------------------------------------------------------------------
   protected void handleCancel () {
      close () ;
   }

   //-------------------------------------------------------------------------------------
   // méthode appelée en cas de succès du dialogue : on modifie le patient avec les données saisies dans le formulaire
   //-------------------------------------------------------------------------------------
   protected void handleOK () {
      // on prévient le controle que les modifications sont à prendre en compte 
      control.editionComplete () ;
      // on ferme la fenêtre
      close () ;
   }

}
   