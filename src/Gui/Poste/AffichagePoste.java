package Gui.Poste;

import Entity.Poste;
import Services.ServicePoste;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import java.util.ArrayList;

public class AffichagePoste extends Form {

    private ArrayList<Poste> postes;

    public AffichagePoste(Form previous) {
        setTitle("List Postes here :");
        setLayout(BoxLayout.y());
        postes = ServicePoste.getInstance().getAllPostes();

        for (Poste poste : postes) {
            Container card = new Container(new BorderLayout());
            card.getStyle().setBorder(Border.createLineBorder(1, ColorUtil.GRAY));
            card.getStyle().setMarginUnit(Style.UNIT_TYPE_DIPS);
            card.getStyle().setMargin(Component.BOTTOM, 10);
            card.getStyle().setBgColor(0xFFFFFF);

            Label UserLabel = new Label("User:" + poste.getUser());
            Label TrajetLabel = new Label("Trajet:" + poste.getTrajet());
            Label VehiculeLabel = new Label("Vehicule:" + poste.getVehicule());
            Label PrixLabel = new Label("Prix:" + poste.getPrix());

            UserLabel.getStyle().setFgColor(0x000000);
            TrajetLabel.getStyle().setFgColor(0x000000);
            VehiculeLabel.getStyle().setFgColor(0x000000);
            PrixLabel.getStyle().setFgColor(0x000000);

            card.add(BorderLayout.CENTER, BoxLayout.encloseY(UserLabel, TrajetLabel, VehiculeLabel, PrixLabel));
            this.add(card);

            Button btndelete = new Button("delete");
            add(btndelete);

            Button updateButton = new Button("Update poste");

            updateButton.addActionListener(e -> {
                Form updateForm = new Form("Update poste");
                TextField user = new TextField("", "User");
                TextField trajet = new TextField("", "Trajet");
                TextField vehicule = new TextField("", "Vehicule");
                TextField prix = new TextField("", "Prix");

                Button submitButton = new Button("Submit");

                submitButton.addActionListener(submitEvent -> {
                    // Get the updated values of the fields
                    String user2 = user.getText();
                    String trajet2 = trajet.getText();
                    String vehicule2 = vehicule.getText();
                    double prix2 = Double.parseDouble(prix.getText());

                    // Call the service function to update the station
                    Services.ServicePoste.getInstance().modifierPoste(poste.getId(), user2, trajet2, vehicule2, prix2);

                    // Show a confirmation message to the user
                    Dialog.show("Success", "Poste updated successfully", "OK", null);
                    AffichagePoste refresh = new AffichagePoste(previous);
                    refresh.show();
                });

                updateForm.add(user).add(trajet).add(vehicule).add(prix).add(submitButton);
                updateForm.show();
            });
            add(updateButton);

            btndelete.addActionListener((e) -> {
                Services.ServicePoste.getInstance().suppPoste(poste);
                AffichagePoste refresh = new AffichagePoste(previous);
                refresh.show();

            });
        }

        Button btnHome = new Button("Home");
        add(btnHome);

        btnHome.addActionListener((e) -> {
            PosteHome home = new PosteHome();
            home.show();
        });

    }

}
