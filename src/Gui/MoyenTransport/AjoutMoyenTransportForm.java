package Gui.MoyenTransport;

import Entity.MoyenTransport;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class AjoutMoyenTransportForm extends Form {
    
    public AjoutMoyenTransportForm(Resources res) {
        super("Ajout MoyenTransport", BoxLayout.y()); 
        
        TextField nbPlace = new TextField("", "Nombre de place");
        addStringValue("Nombre de place", nbPlace);
        
        TextField matricule = new TextField("", "Matricule");
        addStringValue("Matricule", matricule);
        
        TextField marque = new TextField("", "Marque");
        addStringValue("Marque", marque);
        
        TextField type = new TextField("", "Type");
        addStringValue("Type", type);
        
        Button btnAjouter = new Button("Ajouter");
        add(btnAjouter);
        
        btnAjouter.addActionListener((e) -> {
            if (nbPlace.getText().isEmpty() || marque.getText().isEmpty() || type.getText().isEmpty() || matricule.getText().isEmpty()) {
                Dialog.show("Erreur", "Veuillez remplir tous les champs", "OK", null);
            } else {
                MoyenTransport moyenTransport = new MoyenTransport(Double.parseDouble(nbPlace.getText()), marque.getText(),type.getText(),matricule.getText());
                Services.ServiceMoyenTransport.getInstance().AjouterMoyenTransport(moyenTransport);
                Dialog.show("Succès", "MoyenTransport ajouté avec succès", "OK", null);
                nbPlace.clear();
                marque.clear();
                type.clear();
                matricule.clear();
            }
        });
        
        Button btnHome = new Button("Accueil");
        add(btnHome);
        
        btnHome.addActionListener((e) -> {
            MoyenTransportHome home = new MoyenTransportHome();
            home.show();
        });
    }
    
    private void addStringValue(String s, TextField tf) {
        add(BorderLayout.west(tf))
            .add(BorderLayout.east(new com.codename1.ui.Label(s, "PaddedLabel")));
    }
    
}
