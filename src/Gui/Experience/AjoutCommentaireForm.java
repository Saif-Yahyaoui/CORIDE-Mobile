package Gui.Experience;

import Entity.Experience;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class AjoutCommentaireForm extends Form {
    
    public AjoutCommentaireForm(Resources res) {
        super("Ajout Experience", BoxLayout.y()); 
        
       
        TextField text = new TextField("", "Text");
        addStringValue("Text", text);
        TextField rate = new TextField("", "Rate");
        addStringValue("Rate", rate);
      
        
        Button btnAjouter = new Button("Ajouter");
        add(btnAjouter);
        
        btnAjouter.addActionListener((e) -> {
            if (text.getText().isEmpty()|| rate.getText().isEmpty()) {
                Dialog.show("Erreur", "Veuillez remplir tous les champs", "OK", null);
            } else {
                Experience experience = new Experience( text.getText(),Integer.parseInt(rate.getText()));
                Services.ServiceCommentaire.getInstance().AjouterExperience(experience);
                Dialog.show("Succès", "Experience ajouté avec succès", "OK", null);
                text.clear();
            }
        });
        
        Button btnHome = new Button("Accueil");
        add(btnHome);
        
        btnHome.addActionListener((e) -> {
            ExperienceHome home = new ExperienceHome();
            home.show();
        });
    }
    
    private void addStringValue(String s, TextField tf) {
        add(BorderLayout.west(tf))
            .add(BorderLayout.east(new com.codename1.ui.Label(s, "PaddedLabel")));
    }
    
}
