/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Itineraire;

import Entity.Iteneraire;
import Services.ServiceItineraire;
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

/**
 *
 * @author hanin
 */
public class AffichageItineraire extends Form {

    //     public ListReservations(Form previous) {
//        setTitle("List Reservations");
//        setLayout(BoxLayout.y());
//        ArrayList<Reservation> reservations = ServiceReservation.getInstance().getAllReservations();
//        //widgets
//        SpanLabel sl = new SpanLabel();
//        sl.setText(reservations.toString());
//
//        //end
//        this.add(sl);
//
//    }
    public AffichageItineraire(Form previous) {
        setTitle("List Itineraires");
        setLayout(BoxLayout.y());
        ArrayList<Iteneraire> itineraires = ServiceItineraire.getInstance().getAllIteneraires();

        for (Iteneraire itineraire : itineraires) {
            Container card = new Container(new BorderLayout());
            card.getStyle().setBorder(Border.createLineBorder(1, ColorUtil.GRAY));
            card.getStyle().setMarginUnit(Style.UNIT_TYPE_DIPS);
            card.getStyle().setMargin(Component.BOTTOM, 10);
            card.getStyle().setBgColor(0xFFFFFF);

            Label DepartLabel = new Label("Pts du depart:" + itineraire.getPts_depart());
            Label ArriveLabel = new Label("Pts du arrive:" + itineraire.getPts_arrive());

//        Label clientLabel = new Label("Client: " + reservation.getId_client());
//        Label dateLabel = new Label("Date: " + reservation.getDate_reservation());
//        Label heureDepartLabel = new Label("Heure de départ: " + reservation.getHeure_depart());
//        Label heureArriveLabel = new Label("Heure d'arrivée: " + reservation.getHeure_arrive());
//        Label typeTicketLabel = new Label("Type de ticket: " + reservation.getType_ticket());
//        Label moyenTransportLabel = new Label("Moyen de transport: " + reservation.getId_moy());
//        Label itLabel = new Label("Itineraire: " + reservation.getId_it());
//        Label statusLabel = new Label("Status: " + reservation.getStatus());
            DepartLabel.getStyle().setFgColor(0x000000);
            ArriveLabel.getStyle().setFgColor(0x000000);
//        heureDepartLabel.getStyle().setFgColor(0x000000);
//        heureArriveLabel.getStyle().setFgColor(0x000000);
//        typeTicketLabel.getStyle().setFgColor(0x000000);
//        moyenTransportLabel.getStyle().setFgColor(0x000000);
//        itLabel.getStyle().setFgColor(0x000000);
//        statusLabel.getStyle().setFgColor(0x000000);

            card.add(BorderLayout.CENTER, BoxLayout.encloseY(DepartLabel, ArriveLabel
            ));
            this.add(card);

            Button btndelete = new Button("delete");

            add(btndelete);

            Button updateButton = new Button("Update Itineraire");
            updateButton.addActionListener(e -> {
                Form updateForm = new Form("Update Itineraire");

                TextField ptsd = new TextField("", "ptsDepart");
                TextField ptsa = new TextField("", "ptsArrive");

                Button submitButton = new Button("Submit");
                submitButton.addActionListener(submitEvent -> {
                    // Get the updated values of the fields

                    String ptsd2 = ptsd.getText();
                    String ptsa2 = ptsa.getText();

                    // Call the service function to update the station
                    Services.ServiceItineraire.getInstance().modifierItineraire(itineraire.getId(), ptsd2, ptsa2);

                    // Show a confirmation message to the user
                    Dialog.show("Success", "Iteneraire updated successfully", "OK", null);
                    AffichageItineraire refresh = new AffichageItineraire(previous);
                    refresh.show();
                });

                updateForm.add(ptsd).add(ptsa).add(submitButton);
                updateForm.show();
            });

            add(updateButton);
            btndelete.addActionListener((e) -> {
                Services.ServiceItineraire.getInstance().suppItenraire(itineraire);
                AffichageItineraire refresh = new AffichageItineraire(previous);
                refresh.show();
            });
        }
        Button btnHome = new Button("Home");
        add(btnHome);

        btnHome.addActionListener((e) -> {
            ItineraireHome home = new ItineraireHome();
            home.show();

        });

    }
}
