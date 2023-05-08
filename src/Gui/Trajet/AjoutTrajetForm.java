package Gui.Trajet;

import Entity.Trajet;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class AjoutTrajetForm extends Form {
    
    public AjoutTrajetForm(Resources res) {
        super("Ajout Trajet", BoxLayout.y()); 
        
        TextField depart = new TextField("", "Point de départ");
        addStringValue("Départ", depart);
        
        TextField destination = new TextField("", "Point d'arrivée");
        addStringValue("Destination", destination);
        
        Button btnAjouter = new Button("Ajouter");
        add(btnAjouter);
        
        btnAjouter.addActionListener((e) -> {
            if (depart.getText().isEmpty() || destination.getText().isEmpty()) {
                Dialog.show("Erreur", "Veuillez remplir tous les champs", "OK", null);
            } else {
                Trajet trajet = new Trajet(depart.getText(), destination.getText());
                Services.ServiceTrajet.getInstance().AjouterTrajet(trajet);
                Dialog.show("Succès", "Trajet ajouté avec succès", "OK", null);
                depart.clear();
                destination.clear();
            }
        });
        
        Button btnHome = new Button("Accueil");
        add(btnHome);
        
        btnHome.addActionListener((e) -> {
            TrajetHome home = new TrajetHome();
            home.show();
        });
    }
    
    private void addStringValue(String s, TextField tf) {
        add(BorderLayout.west(tf))
            .add(BorderLayout.east(new com.codename1.ui.Label(s, "PaddedLabel")));
    }
    
}
