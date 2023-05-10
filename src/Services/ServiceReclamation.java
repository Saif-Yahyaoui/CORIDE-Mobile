/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Reclamation;
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
public class ServiceReclamation {

    public static ServiceReclamation instance = null;

    private ConnectionRequest req;
    private Reclamation t;
    public boolean resultOK;
    ArrayList<Reclamation> reclamations;

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    public ServiceReclamation() {
        req = new ConnectionRequest();

    }

    public void AjouterReclamation(Reclamation it) {
        String url = Statics.BASE_URL + "addReclamations?user=" + it.getUser() + "&rec=" + it.getRec() + "&sujet=" + it.getSujet();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data==" + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public ArrayList<Reclamation> parseReclamation(String jsonText) {
        try {
            reclamations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> reclamationListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) reclamationListJson.get("root");
            if (list != null) { // Check if list is not null
                for (Map<String, Object> obj : list) {
                    Reclamation i = new Reclamation();
                    float id = Float.parseFloat(obj.get("id").toString());
                    i.setId((int) id);
                    Double user = (Double) obj.get("user");
                    i.setUser(user.intValue());
                    i.setRec((String) obj.get("rec"));
                    i.setSujet((String) obj.get("sujet"));

                    reclamations.add(i);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(reclamations);
        return reclamations;
    }

    public ArrayList<Reclamation> getAllReclamations() {
        String url = Statics.BASE_URL + "reclamations";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reclamations = parseReclamation(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }

    public boolean suppReclamation(Reclamation i) {

        String url = Statics.BASE_URL + "deleteReclamations?id=" + i.getId();
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

    public void modifierReclamation(int id, int user, String rec, String sujet) {
        String url = Statics.BASE_URL + "updateReclamations?id=" + id + "&user=" + user + "&rec=" + rec + "&sujet=" + sujet;
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
