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
import Entity.Poste;
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
public class ServicePoste {
    public static ServicePoste instance = null;
    
    private ConnectionRequest req;
    private Poste t;
    public boolean resultOK;
    ArrayList<Poste> postes;
    public static ServicePoste getInstance(){
        if (instance == null){
            instance = new ServicePoste();
        }
        return instance;
    }
    public ServicePoste(){
        req = new ConnectionRequest();
                
                
                
    }
    public void AjouterPoste(Poste it){
        String url = Statics.BASE_URL+"addPostes?user="+it.getUser()+"&trajet="+it.getTrajet()+"&vehicule="+it.getVehicule()+"&prix="+it.getPrix();
        req.setUrl(url);
        req.addResponseListener((e)->{
        String str = new String(req.getResponseData());
            System.out.println("data=="+str);});
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
  
public ArrayList<Poste> parsePoste(String jsonText) {
    try {
        postes = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> posteListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
        List<Map<String, Object>> list = (List<Map<String, Object>>) posteListJson.get("root");
        if (list != null) { // Check if list is not null
            for (Map<String, Object> obj : list) {
                Poste i = new Poste();
                float id = Float.parseFloat(obj.get("id").toString());
                i.setId((int) id);
                i.setUser((String)obj.get("user"));
                i.setTrajet((String)obj.get("trajet"));
                i.setVehicule((String)obj.get("vehicule"));
                i.setPrix((double)obj.get("prix"));

                postes.add(i);
            }
        }
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    System.out.println(postes);
    return postes;
}




 public ArrayList<Poste> getAllPostes() {
    String url = Statics.BASE_URL + "poste";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            postes = parsePoste(new String(req.getResponseData()));                
            req.removeResponseListener(this);
            System.out.println(postes);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return postes;
}

     
    public boolean suppPoste(Poste i)
    {

         String url = Statics.BASE_URL + "deletePostes?id="+i.getId();
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
     
    public void modifierPoste(int id, String user, String trajet, String vehicule, double prix) {
    String url = Statics.BASE_URL + "updatePostes?id="+id+"&user="+user+"&trajet="+trajet+"&vehicule="+vehicule+"&prix="+prix;
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



   
    

