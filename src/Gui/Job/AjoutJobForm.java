package Gui.Job;

import Entity.Job;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class AjoutJobForm extends Form {
    
    public AjoutJobForm(Resources res) {
        super("Ajout Job", BoxLayout.y()); 
        
       
        TextField title = new TextField("", "Title");
        addStringValue("Title", title);
        
        TextField description = new TextField("", "Description");
        addStringValue("Description", description);
        
        TextField location = new TextField("", "Location");
        addStringValue("Location", location);
        
         TextField salary = new TextField("", "Salary");
        addStringValue("Salary", salary);
        
        Button btnAjouter = new Button("Ajouter");
        add(btnAjouter);
        
        btnAjouter.addActionListener((e) -> {
            if (title.getText().isEmpty()|| description.getText().isEmpty() || location.getText().isEmpty() || salary.getText().isEmpty()) {
                Dialog.show("Erreur", "Veuillez remplir tous les champs", "OK", null);
            } else {
                Job job = new Job( title.getText(),description.getText(),location.getText(),Integer.parseInt(salary.getText()));
                Services.ServiceJob.getInstance().AjouterJob(job);
                Dialog.show("Succès", "Job ajouté avec succès", "OK", null);
                salary.clear();
                description.clear();
                location.clear();
                title.clear();
            }
        });
        
        Button btnHome = new Button("Accueil");
        add(btnHome);
        
        btnHome.addActionListener((e) -> {
            JobHome home = new JobHome();
            home.show();
        });
    }
    
    private void addStringValue(String s, TextField tf) {
        add(BorderLayout.west(tf))
            .add(BorderLayout.east(new com.codename1.ui.Label(s, "PaddedLabel")));
    }
    
}
