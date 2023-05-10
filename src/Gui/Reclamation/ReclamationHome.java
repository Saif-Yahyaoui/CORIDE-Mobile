/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Reclamation;

import Home.Home;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class ReclamationHome extends Form {

    public ReclamationHome() {
        setTitle("Home Reclamation !");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option Reclamation"));
        Button btnAdd = new Button("Add Reclamation");
        Button btnList = new Button("Show Reclamation");
        Button btnBack = new Button("Back");

        Resources res = null;
        btnAdd.addActionListener(e -> new AjoutReclamationForm(res).show());
        btnList.addActionListener(e -> new AffichageReclamation(this).show());
        btnBack.addActionListener(e
                -> {
            Home home = new Home();
            home.show();
        }
        );

        add(btnAdd);
        add(btnList);
        add(btnBack);

    }

}
