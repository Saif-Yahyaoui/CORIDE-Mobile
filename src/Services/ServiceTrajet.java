/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Entity.Trajet;
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
public class ServiceTrajet {
    public static ServiceTrajet instance = null;
    
    private ConnectionRequest req;
    private Trajet t;
    public boolean resultOK;
    ArrayList<Trajet> trajets;
    public static ServiceTrajet getInstance(){
        if (instance == null){
            instance = new ServiceTrajet();
        }
        return instance;
    }
    public ServiceTrajet(){
        req = new ConnectionRequest();
                
                
                
    }
    public void AjouterTrajet(Trajet it){
        String url = Statics.BASE_URL+"addTrajets?depart="+it.getDepart()+"&destination="+it.getDestination();
        req.setUrl(url);
        req.addResponseListener((e)->{
        String str = new String(req.getResponseData());
            System.out.println("data=="+str);});
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
  
    public ArrayList<Trajet> parseTrajet(String jsonText) {
        try {
            trajets = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> trajetListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) trajetListJson.get("root");
            for (Map<String, Object> obj : list) {
                Trajet i = new Trajet();
                float id = Float.parseFloat(obj.get("id").toString());
                i.setId((int) id);
                i.setDepart((String)obj.get("depart"));
                i.setDestination((String)obj.get("destination"));

         
                
               trajets.add(i);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(trajets);
        return trajets;
    }

    public ArrayList<Trajet> getAllTrajets() {
        String url = Statics.BASE_URL + "trajets";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                trajets = parseTrajet(new String(req.getResponseData()));
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return trajets;
    }
     
    public boolean suppTrajet(Trajet i)
    {

         String url = Statics.BASE_URL + "deleteTrajets?id="+i.getId();
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);             }
         });
          NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
    public void modifierTrajet(int id, String ptsd, String ptsa) {
    String url = Statics.BASE_URL + "updateTrajets?id="+id+"&depart="+ptsd+"&destination="+ptsa;
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



   
    

