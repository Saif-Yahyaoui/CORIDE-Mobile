package Gui.Job;

import Entity.Job;
import Entity.MoyenTransport;
import Services.ServiceJob;
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

public class AffichageJob extends Form {

    private ArrayList<Job> jobs;

    public AffichageJob(Form previous) {
        setTitle("List Job here :");
        setLayout(BoxLayout.y());
        jobs = ServiceJob.getInstance().getAllJobs();

        for (Job job : jobs) {
            Container card = new Container(new BorderLayout());
            card.getStyle().setBorder(Border.createLineBorder(1, ColorUtil.GRAY));
            card.getStyle().setMarginUnit(Style.UNIT_TYPE_DIPS);
            card.getStyle().setMargin(Component.BOTTOM, 10);
            card.getStyle().setBgColor(0xFFFFFF);

            Label titleLabel = new Label("Place:" + job.getTitle());
            Label descriptionLabel = new Label("Description:" + job.getDescription());
            Label locationLabel = new Label("Location:" + job.getLocation());
            Label salaryLabel = new Label("Salary:" + job.getSalary());

            titleLabel.getStyle().setFgColor(0x000000);
            descriptionLabel.getStyle().setFgColor(0x000000);
            locationLabel.getStyle().setFgColor(0x000000);
            salaryLabel.getStyle().setFgColor(0x000000);

            card.add(BorderLayout.CENTER, BoxLayout.encloseY(titleLabel, descriptionLabel,locationLabel,salaryLabel));
            this.add(card);

            Button btndelete = new Button("delete");
            add(btndelete);

            Button updateButton = new Button("Update job");

            updateButton.addActionListener(e -> {
                Form updateForm = new Form("Update job");
                TextField title = new TextField("", "Title");
                TextField description = new TextField("", "Description");
                TextField location = new TextField("", "Location");
                TextField salary = new TextField("", "Salary");
                Button submitButton = new Button("Submit");

                submitButton.addActionListener(submitEvent -> {
                    // Get the updated values of the fields

                    String title2 = title.getText();
                    String description2 = description.getText();
                    String location2 = location.getText();
                   int salary2 = Integer.parseInt(salary.getText());

                    // Call the service function to update the station
                    Services.ServiceJob.getInstance().modifierJob(job.getId(), title2, description2,location2,salary2);

                    // Show a confirmation message to the user
                    Dialog.show("Success", "Job updated successfully", "OK", null);
                    AffichageJob refresh = new AffichageJob(previous);
                    refresh.show();
                });

                updateForm.add(title).add(description).add(location).add(salary).add(submitButton);
                updateForm.show();
            });
            add(updateButton);

            btndelete.addActionListener((e) -> {
                Services.ServiceJob.getInstance().suppJob(job);
                AffichageJob refresh = new AffichageJob(previous);
                    refresh.show();

            });
        }

        Button btnHome = new Button("Home");
        add(btnHome);

        btnHome.addActionListener((e) -> {
            JobHome home = new JobHome();
            home.show();
        });

        
    }

}
