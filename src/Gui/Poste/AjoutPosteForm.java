package Gui.Poste;

import Entity.Poste;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class AjoutPosteForm extends Form {
    
    public AjoutPosteForm(Resources res) {
        super("Ajout Poste", BoxLayout.y()); 
        
        TextField user = new TextField("", "User");
        addStringValue("User", user);
        
        TextField trajet = new TextField("", "Trajet");
        addStringValue("Trajet", trajet);
        
        TextField vehicule = new TextField("", "Vehicule");
        addStringValue("Vehicule", vehicule);
        
        TextField prix = new TextField("", "Prix");
        addStringValue("Prix", prix);
        
        Button btnAjouter = new Button("Ajouter");
        add(btnAjouter);
        
        btnAjouter.addActionListener((e) -> {
            if (user.getText().isEmpty() || trajet.getText().isEmpty() || vehicule.getText().isEmpty()|| prix.getText().isEmpty()) {
                Dialog.show("Erreur", "Veuillez remplir tous les champs", "OK", null);
            } else {
                Poste poste = new Poste(user.getText(), trajet.getText(), vehicule.getText(), Double.parseDouble(prix.getText()));
                Services.ServicePoste.getInstance().AjouterPoste(poste);
                Dialog.show("Succès", "Trajet ajouté avec succès", "OK", null);
                trajet.clear();
                vehicule.clear();
                prix.clear();

            }
        });
        
        Button btnHome = new Button("Accueil");
        add(btnHome);
        
        btnHome.addActionListener((e) -> {
            PosteHome home = new PosteHome();
            home.show();
        });
    }
    
    private void addStringValue(String s, TextField tf) {
        add(BorderLayout.west(tf))
            .add(BorderLayout.east(new com.codename1.ui.Label(s, "PaddedLabel")));
    }
    
}
