/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package Gui.User;

import Home.Home;
import Services.ServiceUser;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm {

    public ProfileForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(true);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });

        Button modiff = new Button("Modifier");
        Button Supprimer = new Button("Supprimer");

        Button btnBack = new Button("Back");
        Button btnOut = new Button("Logout");

//         TextField name = new TextField(SessionManager.getName());
//        name.setUIID("TextFieldBlack");
//        addStringValue("Name", name);
        TextField Email = new TextField(SessionManager.getEmail(), "E-Mail", 20, TextField.EMAILADDR);
        Email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", Email);

        TextField Password = new TextField(SessionManager.getPassowrd(), "Password", 20, TextField.PASSWORD);
        Password.setUIID("TextFieldBlack");
        addStringValue("Password", Password);

        Supprimer.setUIID("Update");
        modiff.setUIID("Edit");
        addStringValue("", Supprimer);
        addStringValue("", modiff);

        modiff.addActionListener((ActionEvent edit) -> {
            InfiniteProgress ip = new InfiniteProgress();
            //final Dialog ipDlg = ip.showInifinieteBlooking();
            ServiceUser.getInstance().modifUser(SessionManager.getId(), Email.getText());
            SessionManager.setEmail(Email.getText());//
            Dialog.show("Succes", "Modifications des coordonnees avec succes", "OK", null);
            // ipDlg.dispose();
            refreshTheme();

        });

        Supprimer.addPointerPressedListener(l -> {

            Dialog dig = new Dialog("Suppression");

            if (dig.show("Suppression", "Vous voulez supprimer Votre Compte ?", "Annuler", "Oui")) {
                dig.dispose();
            } else {
                dig.dispose();
            }

            if (ServiceUser.getInstance().deleteUser(SessionManager.getId())) {
                Form previousForm = new Form("Previous Form");
                Resources re = null;
                try {
                    re = Resources.open("/theme.res");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                new AddUser(previousForm, re).show();
            }

        });

        btnBack.addActionListener(e
                -> {
            Home home = new Home();
            home.show();
        }
        );
        add(btnBack);

        btnOut.addActionListener(e
                -> {
            Form previousForm = new Form("Previous Form");
            Resources re = null;
            try {
                re = Resources.open("/theme.res");
            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }
            new Login(previousForm, re).show();
        }
        );
        add(btnOut);

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
