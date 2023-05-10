/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui.User;

import Entity.User;
import Home.Home;
import Services.ServiceUser;
import com.codename1.capture.Capture;
import com.codename1.components.SpanLabel;
import com.codename1.components.Switch;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 *
 * @author MSI
 */
public class AddUser extends Form {
//    private String imagePath;

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');

        return (atIndex > 0 && dotIndex > atIndex);

    }
//    private void capturePhoto() {
//        try {
//            long timestamp = System.currentTimeMillis();
////            //String imagePath = Capture.capturePhoto();
////            String imagePath = Capture.capturePhoto();
////            String targetPath="C:\\xampp\\htdocs\\images"+ timestamp + ".jpg";
//            InputStream inputStream = FileSystemStorage.getInstance().openInputStream(imagePath);
//
//            // Open an output stream for the target path
//            OutputStream outputStream = FileSystemStorage.getInstance().openOutputStream(targetPath);
//
//            // Copy the contents from the input stream to the output stream
//            Util.copy(inputStream, outputStream);
//
//            // Close the streams
//            inputStream.close();
//            outputStream.close();
//            imagePath = targetPath;
//            // Rename the captured photo file to the unique name
//            FileSystemStorage.getInstance().rename(imagePath, timestamp + ".jpg");
//            Dialog.show("Image Upload", "Votre photo a été téléchargé avec succés", "OK", null);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            Dialog.show("Image Upload", "Echec lors de téléchargement d'image ", "OK", null);
//        }
//    }
//
//    
//    public String getImagePath() {
//        return imagePath;
//    }

    public AddUser(Form prev, Resources res) {
        setTitle("Inscription");
        setLayout(BoxLayout.yCenter());

        Label name = new Label("name:");
        TextField tname = new TextField("", "votre nom");

        Label last_name = new Label("Last name:");
        TextField tlast_name = new TextField("", "votre prénom");

        Label email = new Label("email");
        TextField temail = new TextField("", "votre email:");

        Label password = new Label("password:");
        TextField tpassword = new TextField();

        Label gender = new Label("gender:");
        TextField tgender = new TextField("", "votre gender");

        Label phone = new Label("phone:");
        TextField tphone = new TextField("", "votre phone");

//         Label birthday = new Label("birthday:");
//        TextField tbirthday = new TextField("", "votre birthday");
        tpassword.setHint("votre mot de passe");
        tpassword.setConstraint(TextField.PASSWORD);

        Label password2 = new Label("Comfirmation mot de passe:");
        TextField tpassword2 = new TextField();
        tpassword2.setHint("Entrer votre mot de passe de nouveau");
        tpassword2.setConstraint(TextField.PASSWORD);

        tphone.setConstraint(TextField.PHONENUMBER);

        Button addU = new Button("s'inscrire");
        addU.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String testemail = temail.getText().trim();

                if (tname.getText().isEmpty() || tlast_name.getText().isEmpty() || temail.getText().isEmpty() || tpassword2.getText().isEmpty() || tpassword.getText().isEmpty() || tgender.getText().isEmpty() || tphone.getText().isEmpty()) {
                    Dialog.show("error", "Vous devez remplir tous les champs", "OK", null);
                } else {
                    if (tpassword.getText().equals(tpassword2.getText())) {
                        if (tphone.getText().length() <= 8) {
                            if (isValidEmail(testemail)) {
                                User user = new User(tname.getText(), tlast_name.getText(), temail.getText(), tpassword.getText(), tgender.getText(), tphone.getText());
                                if (ServiceUser.getService().addUser(user)) {
                                    Dialog.show("success", "compte ajouté avec succés", "OK", null);
                                    Form previousForm = new Form("Previous Form");
                                    Resources res = null;
                                    try {
                                        res = Resources.open("/theme.res");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    new Login(previousForm, res).show();
                                } else {
                                    Dialog.show("error", "ajout a échoué", "OK", null);
                                }
                            } else {
                                Dialog.show("error", "Vous devez saisir une adresse e-mail valide", "OK", null);
                            }
                        } else {
                            Dialog.show("error", "Vous devez saisir un numéro de téléphone valide", "OK", null);
                        }
                    } else {
                        Dialog.show("error", "Vous devez saisir deux mots de passe identiques", "OK", null);
                    }
                }
            }
        }));

        addAll(name, tname, last_name, tlast_name, email, temail, password, tpassword, password2, tpassword2, gender, tgender, phone, tphone);
        //add(uploadButton);
        add(addU);
        Label login = new Label("Vous avez déjà un compte ?");
        login.getUnselectedStyle().setUnderline(true);

        // Add a PointerPressedListener to the label to show the SecondForm
        login.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new Login(prev, res).show();
            }
        });

        // Add the label to the MainForm
        addComponent(login);

    }
}
