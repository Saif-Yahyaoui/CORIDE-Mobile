/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.MoyenTransport;
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
public class ServiceMoyenTransport {
    public static ServiceMoyenTransport instance = null;
    
    private ConnectionRequest req;
    private MoyenTransport t;
    public boolean resultOK;
    ArrayList<MoyenTransport> moyenTransports;
    public static ServiceMoyenTransport getInstance(){
        if (instance == null){
            instance = new ServiceMoyenTransport();
        }
        return instance;
    }
    public ServiceMoyenTransport(){
        req = new ConnectionRequest();
                
                
                
    }
    public void AjouterMoyenTransport(MoyenTransport it){
        String url = Statics.BASE_URL+"addMoyenTransports?type="+it.getType()+"&nbPlace="+it.getNbPlace()+"&marque="+it.getMarque()+"&matricule="+it.getMatricule();
        req.setUrl(url);
        req.addResponseListener((e)->{
        String str = new String(req.getResponseData());
            System.out.println("data=="+str);});
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
  
public ArrayList<MoyenTransport> parseMoyenTransport(String jsonText) {
    try {
        moyenTransports = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> moyenTransportListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
        List<Map<String, Object>> list = (List<Map<String, Object>>) moyenTransportListJson.get("root");
        if (list != null) { // Check if list is not null
            for (Map<String, Object> obj : list) {
                MoyenTransport i = new MoyenTransport();
                float id = Float.parseFloat(obj.get("id").toString());
                i.setId((int) id);
                i.setType((String)obj.get("type"));
                i.setNbPlace((double)obj.get("nbPlace"));
                i.setMarque((String)obj.get("marque"));
                i.setMatricule((String)obj.get("matricule"));


                moyenTransports.add(i);
            }
        }
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    System.out.println(moyenTransports);
    return moyenTransports;
}




 public ArrayList<MoyenTransport> getAllMoyenTransports() {
    String url = Statics.BASE_URL + "moyenTransports";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            moyenTransports = parseMoyenTransport(new String(req.getResponseData()));                
            req.removeResponseListener(this);
            System.out.println(moyenTransports);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return moyenTransports;
}

     
    public boolean suppMoyenTransport(MoyenTransport i)
    {

         String url = Statics.BASE_URL + "deleteMoyenTransports?id="+i.getId();
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
     
    public void modifierMoyenTransport(int id, double nbPlace, String type, String marque, String matricule) {
    String url = Statics.BASE_URL + "updateMoyenTransports?id="+id+"&type="+type+"&nbPlace="+nbPlace+"&marque="+marque+"&matricule="+matricule;
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



   
    

