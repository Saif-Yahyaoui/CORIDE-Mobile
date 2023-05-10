package Gui.Experience;

import Entity.Experience;
import Services.ServiceCommentaire;
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

public class AffichageCommentaire extends Form {

    private ArrayList<Experience> experiences;

    public AffichageCommentaire(Form previous) {
        setTitle("List Commentaires here :");
        setLayout(BoxLayout.y());
        experiences = ServiceCommentaire.getInstance().getAllExperiences();

        for (Experience experience : experiences) {
            Container card = new Container(new BorderLayout());
            card.getStyle().setBorder(Border.createLineBorder(1, ColorUtil.GRAY));
            card.getStyle().setMarginUnit(Style.UNIT_TYPE_DIPS);
            card.getStyle().setMargin(Component.BOTTOM, 10);
            card.getStyle().setBgColor(0xFFFFFF);

            Label textLabel = new Label("Text:" + experience.getText());
            Label rateLabel = new Label("Rate:" + experience.getRate());

            textLabel.getStyle().setFgColor(0x000000);
            rateLabel.getStyle().setFgColor(0x000000);

            card.add(BorderLayout.CENTER, BoxLayout.encloseY(textLabel));
            
            this.add(card);

            Button btndelete = new Button("delete");
            add(btndelete);

            Button updateButton = new Button("Update experience");

            updateButton.addActionListener(e -> {
                Form updateForm = new Form("Update experience");
                TextField text = new TextField("", "Text");
                TextField rate = new TextField("", "Rate");
                Button submitButton = new Button("Submit");

                submitButton.addActionListener(submitEvent -> {
                    // Get the updated values of the fields

                    String text2 = text.getText();
                    int rate2 = Integer.parseInt(rate.getText());

                    // Call the service function to update the station
                    Services.ServiceCommentaire.getInstance().modifierExperience(experience.getId(), text2, rate2);

                    // Show a confirmation message to the user
                    Dialog.show("Success", "Experience updated successfully", "OK", null);
                    AffichageCommentaire refresh = new AffichageCommentaire(previous);
                    refresh.show();
                });

                updateForm.add(text).add(rate).add(submitButton);
                updateForm.show();
            });
            add(updateButton);

            btndelete.addActionListener((e) -> {
                Services.ServiceCommentaire.getInstance().suppExperience(experience);
                AffichageCommentaire refresh = new AffichageCommentaire(previous);
                    refresh.show();

            });
        }

        Button btnHome = new Button("Home");
        add(btnHome);

        btnHome.addActionListener((e) -> {
            ExperienceHome home = new ExperienceHome();
            home.show();
        });

        
    }

}
