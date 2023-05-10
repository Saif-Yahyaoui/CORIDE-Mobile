/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Home;

import Gui.Experience.ExperienceHome;
import Gui.User.ProfileForm;
import Gui.Job.JobHome;
import Gui.MoyenTransport.MoyenTransportHome;
import Gui.Poste.PosteHome;
import Gui.Reclamation.ReclamationHome;
import Gui.Trajet.*;
import Gui.User.SessionManager;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;


public class Home extends Form  {
    public Home(){
        String us = SessionManager.getEmail();
         setTitle(us);

        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option : "));
        //Gestion
        Button btnListTrajets = new Button("Trajet");
        Button btnListPostes = new Button("Poste");
        Button btnListMoyenTransport = new Button("MoyenTransport");
        Button btnListReclamation = new Button("Reclamation");
        Button btnListJob = new Button("Job");
        Button btnListExperience = new Button("Experience");
        Button btnProfile = new Button("Profile");



        //gestion
        Resources res = null;
        //btnListTasks.addActionListener(e-> new ListTasksForm(this).show());
        btnListTrajets.addActionListener(e-> 
           {
            TrajetHome home = new TrajetHome();
            home.show();
        }
        );
        btnListPostes.addActionListener(e-> 
        {
            PosteHome home = new PosteHome();
            home.show();
        }
        );
        btnListMoyenTransport.addActionListener(e-> 
        {
            MoyenTransportHome home = new MoyenTransportHome();
            home.show();
        }
        );
         btnListReclamation.addActionListener(e-> 
        {
            ReclamationHome home = new ReclamationHome();
            home.show();
        }
        );
          btnListJob.addActionListener(e-> 
        {
            JobHome home = new JobHome();
            home.show();
        }
        );
            btnListExperience.addActionListener(e-> 
        {
            ExperienceHome home = new ExperienceHome();
            home.show();
        }
        );
           btnProfile.addActionListener(e-> 
        {
            ProfileForm p = new ProfileForm(res);
            p.show();
        }
        );
        //GESTION8BUTTON
        add(btnListTrajets);
        add(btnListPostes);
        add(btnListMoyenTransport);
        add(btnListReclamation);
        add(btnListJob);
        add(btnListExperience);
        add(btnProfile);







    }
    
}
