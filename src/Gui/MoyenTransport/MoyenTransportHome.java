/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.MoyenTransport;

import Home.Home;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;


public class MoyenTransportHome extends Form  {
    public MoyenTransportHome(){
        setTitle("Home MoyenTransport !");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option MoyenTransport"));
        Button btnAdd = new Button("Add MoyenTransport");
        Button btnList = new Button("Show MoyenTransports");
        Button btnBack = new Button("Back");

        Resources res = null;
        btnAdd.addActionListener(e-> new AjoutMoyenTransportForm(res).show());
        btnList.addActionListener(e-> new AffichageMoyenTransport(this).show());
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
