/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Experience;

import Gui.Job.*;
import Gui.MoyenTransport.*;
import Home.Home;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;


public class ExperienceHome extends Form  {
    public ExperienceHome(){
        setTitle("Home MoyenTransport !");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option Experience"));
        Button btnAdd = new Button("Add Experience");
        Button btnList = new Button("Show Experience");
        Button btnBack = new Button("Back");

        Resources res = null;
        btnAdd.addActionListener(e-> new AjoutCommentaireForm(res).show());
        btnList.addActionListener(e-> new AffichageCommentaire(this).show());
        btnBack.addActionListener(e->
                {
            Home home = new Home();
            home.show();
        }
                    
        );

        add(btnAdd);
        add(btnList);
                add(btnBack);



    }
    
}
