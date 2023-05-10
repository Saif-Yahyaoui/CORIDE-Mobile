package Gui.Reclamation;

import Entity.Reclamation;
import Services.ServiceReclamation;
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

public class AffichageReclamation extends Form {

    private ArrayList<Reclamation> reclamations;

    public AffichageReclamation(Form previous) {
        setTitle("List Reclamation here :");
        setLayout(BoxLayout.y());
        reclamations = ServiceReclamation.getInstance().getAllReclamations();

        for (Reclamation reclamation : reclamations) {
            Container card = new Container(new BorderLayout());
            card.getStyle().setBorder(Border.createLineBorder(1, ColorUtil.GRAY));
            card.getStyle().setMarginUnit(Style.UNIT_TYPE_DIPS);
            card.getStyle().setMargin(Component.BOTTOM, 10);
            card.getStyle().setBgColor(0xFFFFFF);

            Label userLabel = new Label("User:" + reclamation.getUser());
            Label recLabel = new Label("Rec:" + reclamation.getRec());
            Label sujetLabel = new Label("Sujet:" + reclamation.getSujet());

            userLabel.getStyle().setFgColor(0x000000);
            recLabel.getStyle().setFgColor(0x000000);
            sujetLabel.getStyle().setFgColor(0x000000);

            card.add(BorderLayout.CENTER, BoxLayout.encloseY(userLabel, recLabel, sujetLabel));
            this.add(card);

            Button btndelete = new Button("delete");
            add(btndelete);

            Button updateButton = new Button("Update reclamation");

            updateButton.addActionListener(e -> {
                Form updateForm = new Form("Update reclamation");
                TextField user = new TextField("", "User");
                TextField rec = new TextField("", "Rec");
                TextField sujet = new TextField("", "Sujet");
                Button submitButton = new Button("Submit");

                submitButton.addActionListener(submitEvent -> {
                    // Get the updated values of the fields
                    int user2 = Integer.parseInt(user.getText());

                    String rec2 = rec.getText();
                    String sujet2 = sujet.getText();

                    // Call the service function to update the station
                    Services.ServiceReclamation.getInstance().modifierReclamation(reclamation.getId(), user2, rec2, sujet2);

                    // Show a confirmation message to the user
                    Dialog.show("Success", "Reclamation updated successfully", "OK", null);
                    AffichageReclamation refresh = new AffichageReclamation(previous);
                    refresh.show();
                });

                updateForm.add(user).add(rec).add(sujet).add(submitButton);
                updateForm.show();
            });
            add(updateButton);

            btndelete.addActionListener((e) -> {
                Services.ServiceReclamation.getInstance().suppReclamation(reclamation);
                AffichageReclamation refresh = new AffichageReclamation(previous);
                refresh.show();

            });
        }

        Button btnHome = new Button("Home");
        add(btnHome);

        btnHome.addActionListener((e) -> {
            ReclamationHome home = new ReclamationHome();
            home.show();
        });

    }

}
