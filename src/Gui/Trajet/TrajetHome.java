/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Trajet;

import Home.Home;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;


public class TrajetHome extends Form  {
    public TrajetHome(){
        setTitle("Home trajet !");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option trajet"));
        Button btnAddTrajet = new Button("Add Trajet");
        Button btnListTrajets = new Button("Show Trajets");
        Button btnBack = new Button("Back");

        Resources res = null;
        btnAddTrajet.addActionListener(e-> new AjoutTrajetForm(res).show());
        btnListTrajets.addActionListener(e-> new AffichageTrajet(this).show());
        btnBack.addActionListener(e->
                {
            Home home = new Home();
            home.show();
        }
                    
        );

        add(btnAddTrajet);
        add(btnListTrajets);
                add(btnBack);

      


    }
    
}
