/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Poste;

import Home.Home;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;


public class PosteHome extends Form  {
    public PosteHome(){
        setTitle("Home Poste !");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option poste"));
        Button btnAddPoste = new Button("Add Poste");
        Button btnListPostes = new Button("Show Poste");
        Button btnBack = new Button("Back");

        Resources res = null;
        btnAddPoste.addActionListener(e-> new AjoutPosteForm(res).show());
        btnListPostes.addActionListener(e-> new AffichagePoste(this).show());
         btnBack.addActionListener(e->
                {
            Home home = new Home();
            home.show();
        }
                    
        );
        add(btnAddPoste);
        add(btnListPostes);
                add(btnBack);



    }
    
}
