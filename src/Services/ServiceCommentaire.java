/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Experience;
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
public class ServiceCommentaire {
    public static ServiceCommentaire instance = null;
    
    private ConnectionRequest req;
    private Experience t;
    public boolean resultOK;
    ArrayList<Experience> experiences;
    public static ServiceCommentaire getInstance(){
        if (instance == null){
            instance = new ServiceCommentaire();
        }
        return instance;
    }
    public ServiceCommentaire(){
        req = new ConnectionRequest();
                
                
                
    }
    public void AjouterExperience(Experience it){
        String url = Statics.BASE_URL+"addExperiences?text="+it.getText()+"&rate="+it.getRate();
        req.setUrl(url);
        req.addResponseListener((e)->{
        String str = new String(req.getResponseData());
            System.out.println("data=="+str);});
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
  
public ArrayList<Experience> parseExperience(String jsonText) {
    try {
        experiences = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> experienceListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
        List<Map<String, Object>> list = (List<Map<String, Object>>) experienceListJson.get("root");
        if (list != null) { // Check if list is not null
            for (Map<String, Object> obj : list) {
                Experience i = new Experience();
                float id = Float.parseFloat(obj.get("id").toString());
                i.setId((int) id);
                i.setText((String)obj.get("text"));
               // Cast rate and likes to Integer instead of double
                if (obj.get("rate") != null) {
                    double rate = Double.parseDouble(obj.get("rate").toString());
                    i.setRate((int) rate);
                }
           
                experiences.add(i);
            }
        }
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    System.out.println(experiences);
    return experiences;
}




 public ArrayList<Experience> getAllExperiences() {
    String url = Statics.BASE_URL + "experiences";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            experiences = parseExperience(new String(req.getResponseData()));                
            req.removeResponseListener(this);
            System.out.println(experiences);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return experiences;
}

     
    public boolean suppExperience(Experience i)
    {

         String url = Statics.BASE_URL + "deleteExperiences?id="+i.getId();
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
     
    public void modifierExperience(int id, String text, int rate) {
    String url = Statics.BASE_URL + "updateExperiences?id="+id+"&text="+text+"&rate="+rate;
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



   
    

