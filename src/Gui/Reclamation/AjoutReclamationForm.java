package Gui.Reclamation;

import Entity.Reclamation;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class AjoutReclamationForm extends Form {
    
    public AjoutReclamationForm(Resources res) {
        super("Ajout Reclamation", BoxLayout.y()); 
        
        TextField user = new TextField("", "User");
        addStringValue("User", user);
        
        TextField rec = new TextField("", "Rec");
        addStringValue("Rec", rec);
        
        TextField sujet = new TextField("", "Sujet");
        addStringValue("Sujet", sujet);
        
       
        Button btnAjouter = new Button("Ajouter");
        add(btnAjouter);
        
        btnAjouter.addActionListener((e) -> {
            if (user.getText().isEmpty() || sujet.getText().isEmpty() || rec.getText().isEmpty()) {
                Dialog.show("Erreur", "Veuillez remplir tous les champs", "OK", null);
            } else {
                Reclamation reclamation = new Reclamation(Integer.parseInt(user.getText()), sujet.getText(),rec.getText());
                Services.ServiceReclamation.getInstance().AjouterReclamation(reclamation);
                Dialog.show("Succès", "Reclamation ajouté avec succès", "OK", null);
                user.clear();
                sujet.clear();
                rec.clear();
            }
        });
        
        Button btnHome = new Button("Accueil");
        add(btnHome);
        
        btnHome.addActionListener((e) -> {
            ReclamationHome home = new ReclamationHome();
            home.show();
        });
    }
    
    private void addStringValue(String s, TextField tf) {
        add(BorderLayout.west(tf))
            .add(BorderLayout.east(new com.codename1.ui.Label(s, "PaddedLabel")));
    }
    
}
