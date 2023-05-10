package Gui.MoyenTransport;

import Entity.MoyenTransport;
import Services.ServiceMoyenTransport;
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

public class AffichageMoyenTransport extends Form {

    private ArrayList<MoyenTransport> moyenTransports;

    public AffichageMoyenTransport(Form previous) {
        setTitle("List MoyenTransport here :");
        setLayout(BoxLayout.y());
        moyenTransports = ServiceMoyenTransport.getInstance().getAllMoyenTransports();

        for (MoyenTransport moyenTransport : moyenTransports) {
            Container card = new Container(new BorderLayout());
            card.getStyle().setBorder(Border.createLineBorder(1, ColorUtil.GRAY));
            card.getStyle().setMarginUnit(Style.UNIT_TYPE_DIPS);
            card.getStyle().setMargin(Component.BOTTOM, 10);
            card.getStyle().setBgColor(0xFFFFFF);

            Label nbPlaceLabel = new Label("Place:" + moyenTransport.getNbPlace());
            Label marqueLabel = new Label("Marque:" + moyenTransport.getMarque());
            Label typeLabel = new Label("Type:" + moyenTransport.getType());
            Label matriculeLabel = new Label("Matricule:" + moyenTransport.getMatricule());

            nbPlaceLabel.getStyle().setFgColor(0x000000);
            marqueLabel.getStyle().setFgColor(0x000000);
            typeLabel.getStyle().setFgColor(0x000000);
            matriculeLabel.getStyle().setFgColor(0x000000);

            card.add(BorderLayout.CENTER, BoxLayout.encloseY(nbPlaceLabel, marqueLabel,typeLabel,matriculeLabel));
            this.add(card);

            Button btndelete = new Button("delete");
            add(btndelete);

            Button updateButton = new Button("Update moyenTransports");

            updateButton.addActionListener(e -> {
                Form updateForm = new Form("Update moyenTransports");
                TextField nblace = new TextField("", "Place");
                TextField marque = new TextField("", "Marque");
                TextField type = new TextField("", "Type");
                TextField matricule = new TextField("", "Matricule");
                Button submitButton = new Button("Submit");

                submitButton.addActionListener(submitEvent -> {
                    // Get the updated values of the fields
                   double nblace2 = Double.parseDouble(nblace.getText());

                    String marque2 = marque.getText();
                    String type2 = type.getText();
                    String matricule2 = matricule.getText();

                    // Call the service function to update the station
                    Services.ServiceMoyenTransport.getInstance().modifierMoyenTransport(moyenTransport.getId(), nblace2, marque2,type2,matricule2);

                    // Show a confirmation message to the user
                    Dialog.show("Success", "MoyenTransport updated successfully", "OK", null);
                    AffichageMoyenTransport refresh = new AffichageMoyenTransport(previous);
                    refresh.show();
                });

                updateForm.add(nblace).add(marque).add(type).add(matricule).add(submitButton);
                updateForm.show();
            });
            add(updateButton);

            btndelete.addActionListener((e) -> {
                Services.ServiceMoyenTransport.getInstance().suppMoyenTransport(moyenTransport);
                AffichageMoyenTransport refresh = new AffichageMoyenTransport(previous);
                    refresh.show();

            });
        }

        Button btnHome = new Button("Home");
        add(btnHome);

        btnHome.addActionListener((e) -> {
            MoyenTransportHome home = new MoyenTransportHome();
            home.show();
        });

        
    }

}
