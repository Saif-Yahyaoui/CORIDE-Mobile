/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Job;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/**
 *
 * @author kalee
 */
public class ServiceJob {

    public static ServiceJob instance = null;

    private ConnectionRequest req;
    private Job t;
    public boolean resultOK;
    ArrayList<Job> jobs;

    public static ServiceJob getInstance() {
        if (instance == null) {
            instance = new ServiceJob();
        }
        return instance;
    }

    public ServiceJob() {
        req = new ConnectionRequest();

    }

    public void AjouterJob(Job it) {
        String url = Statics.BASE_URL + "addJobs?title=" + it.getTitle() + "&description=" + it.getDescription() + "&location=" + it.getLocation() + "&salary=" + it.getSalary();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data==" + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public ArrayList<Job> parseJob(String jsonText) {
        try {
            jobs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> jobListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) jobListJson.get("root");
            for (Map<String, Object> obj : list) {
                Job i = new Job();
                float id = Float.parseFloat(obj.get("id").toString());
                i.setId((int) id);
                i.setTitle((String) obj.get("title"));
                i.setDescription((String) obj.get("description"));
                i.setLocation((String) obj.get("location"));
                if (obj.get("salary") != null) {

                    i.setSalary(((Double) obj.get("salary")).intValue());
                }
                    jobs.add(i);
                }

            }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
            System.out.println(jobs);
            return jobs;
        }

    

    public ArrayList<Job> getAllJobs() {
        String url = Statics.BASE_URL + "jobs";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                jobs = parseJob(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return jobs;
    }

    public boolean suppJob(Job i) {

        String url = Statics.BASE_URL + "deleteJobs?id=" + i.getId();
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
                
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public void modifierJob(int id, String title, String description, String location, int salary) {
        String url = Statics.BASE_URL + "updateJobs?id=" + id + "&title=" + title + "&description=" + description + "&location=" + location + "&salary=" + salary;
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setHttpMethod("PUT");
        req.addResponseListener((NetworkEvent evt) -> {
            if (req.getResponseCode() == 200) {
                JSONParser parser = new JSONParser();
                try {
                    Map<String, Object> response = parser.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    System.out.println(response);
                } catch (IOException | JSONException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
}
