package Gui.Trajet;

import Entity.Trajet;
import Services.ServiceTrajet;
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

public class AffichageTrajet extends Form {

    private ArrayList<Trajet> trajets;

    public AffichageTrajet(Form previous) {
        setTitle("List Trajet here :");
        setLayout(BoxLayout.y());
        trajets = ServiceTrajet.getInstance().getAllTrajets();

        for (Trajet trajet : trajets) {
            Container card = new Container(new BorderLayout());
            card.getStyle().setBorder(Border.createLineBorder(1, ColorUtil.GRAY));
            card.getStyle().setMarginUnit(Style.UNIT_TYPE_DIPS);
            card.getStyle().setMargin(Component.BOTTOM, 10);
            card.getStyle().setBgColor(0xFFFFFF);

            Label DepartLabel = new Label("Depart:" + trajet.getDepart());
            Label DestinationLabel = new Label("Destination:" + trajet.getDestination());

            DepartLabel.getStyle().setFgColor(0x000000);
            DestinationLabel.getStyle().setFgColor(0x000000);

            card.add(BorderLayout.CENTER, BoxLayout.encloseY(DepartLabel, DestinationLabel));
            this.add(card);

            Button btndelete = new Button("delete");
            add(btndelete);

            Button updateButton = new Button("Update trajet");

            updateButton.addActionListener(e -> {
                Form updateForm = new Form("Update trajet");
                TextField ptsd = new TextField("", "Depart");
                TextField ptsa = new TextField("", "Destination");
                Button submitButton = new Button("Submit");

                submitButton.addActionListener(submitEvent -> {
                    // Get the updated values of the fields
                    String ptsd2 = ptsd.getText();
                    String ptsa2 = ptsa.getText();

                    // Call the service function to update the station
                    Services.ServiceTrajet.getInstance().modifierTrajet(trajet.getId(), ptsd2, ptsa2);

                    // Show a confirmation message to the user
                    Dialog.show("Success", "Trajet updated successfully", "OK", null);
                    AffichageTrajet refresh = new AffichageTrajet(previous);
                    refresh.show();
                });

                updateForm.add(ptsd).add(ptsa).add(submitButton);
                updateForm.show();
            });
            add(updateButton);

            btndelete.addActionListener((e) -> {
                Services.ServiceTrajet.getInstance().suppTrajet(trajet);
                AffichageTrajet refresh = new AffichageTrajet(previous);
                    refresh.show();

            });
        }

        Button btnHome = new Button("Home");
        add(btnHome);

        btnHome.addActionListener((e) -> {
            TrajetHome home = new TrajetHome();
            home.show();
        });

        
    }

}
