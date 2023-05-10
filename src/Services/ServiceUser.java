/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.User;
import Gui.User.SessionManager;
import Gui.User.WalkthruForm;
import Utils.Statics;
import com.codename1.io.*;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/**
 *
 * @author hanin
 */
public class ServiceUser {

    public static ServiceUser instance = null;
    public ConnectionRequest request;
    private static ServiceUser service;
    private boolean resultOK;
    public static boolean resultOkk = true;

    private ArrayList<User> users;

    private ServiceUser() {
        request = new ConnectionRequest();
    }

    public static ServiceUser getService() {
        if (service == null) {
            service = new ServiceUser();
        }
        return service;
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public boolean deleteUser(int id) {
        String url = Statics.BASE_URL + "deleteUser?id="+id;
        request.setUrl(url);
        request.setPost(true);

        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = request.getResponseCode() == 200;
                request.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return resultOK;

    }
//    public boolean modifUser(String Email) {
//        String url = Statics.BASE_URL + "editUser?email="+Email;
//       
//        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
////        String birthdayStr = sdf.format(u.getBirthday());
////        request.addArgument("birthday", birthdayStr);
//        request.setUrl(url);
//
//        request.setPost(true);
//        request.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                JSONParser jsonParser;
//                jsonParser = new JSONParser();
//                resultOK = request.getResponseCode() == 200;
//                request.removeResponseCodeListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(request);
//        return resultOK;
//    }
    public static void modifUser(int id, String email) {
    String url = Statics.BASE_URL + "editUser?id=" + id + "&email=" + email;
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);
    request.setHttpMethod("PUT");
    
    request.addResponseListener((NetworkEvent evt) -> {
            if (request.getResponseCode() == 200) {
                JSONParser parser = new JSONParser();
                try {
                    Map<String, Object> response = parser.parseJSON(new CharArrayReader(new String(request.getResponseData()).toCharArray()));
                    System.out.println(response);
                } catch (IOException | JSONException ex) {
                }
            }
        });
    NetworkManager.getInstance().addToQueueAndWait(request);
}


// public boolean deleteUser(int id ) {
//        String url = Statics.BASE_URL +"deleteUser?id="+id;
//        
//        request.setUrl(url);
//        
//        request.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                    
//                    request.removeResponseCodeListener(this);
//            }
//        });
//        
//        NetworkManager.getInstance().addToQueueAndWait(request);
//        return  resultOkk;
//    }
//    

//    public User login(String username, String password) {
//        String url = Statics.BASE_URL + "login";
//        request.setContentType(" application/json");
//        request.setPost(true);
//        String jsonBody = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
//        request.setRequestBody(jsonBody);
//        request.setUrl(url);
//
//        final User[] user = new User[1];
//        request.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                JSONParser jsonParser;
//                jsonParser = new JSONParser();
//                resultOK = request.getResponseCode() == 200;
//                request.removeResponseCodeListener(this);
//                System.out.println("respl " + new String(request.getResponseData()));
//                user[0] = parseUser(new String(request.getResponseData()));
//                System.out.println("usersl " + user[0]);
//                request.removeResponseListener(this);
//
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(request);
//        return user[0];
//    }
    
//SignIn
    
    public void login(TextField Email,TextField Password, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"login?email="+Email.getText().toString()+"&password="+Password.getText().toString();
        request = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        request.setUrl(url);
        
        request.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(request.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Email ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                 new WalkthruForm(rs).show();
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);
                
                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setEmail(user.get("email").toString());
                
               
                
         
                
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(request);
    }
    

    public boolean addUser(User u) {
        String url = Statics.BASE_URL + "addUser?name="+u.getName()+"&lastName="+u.getLast_name()+"&email="+u.getEmail()+"&password="+u.getPassword()+"&gender="+u.getGender()+"&phone="+u.getPhone();

        
    
  
//        request.addArgument("name", u.getName());
//        request.addArgument("last_name", u.getLast_name());
//        request.addArgument("email", u.getEmail());
//        request.addArgument("password", u.getPassword());
//        request.addArgument("gender", u.getGender());
//        request.addArgument("phone", u.getPhone());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String birthdayStr = sdf.format(u.getBirthday());
//        request.addArgument("birthday", birthdayStr);
        request.setUrl(url);

        request.setPost(true);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonParser;
                jsonParser = new JSONParser();
                resultOK = request.getResponseCode() == 200;
                request.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return resultOK;
    }

    private User parseUser(String jsonText) {
        User u = new User();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            u.setId((int) obj.get("id"));
            u.setName(obj.get("name").toString());
            u.setLast_name(obj.get("last_name").toString());
            u.setEmail(obj.get("email").toString());
            u.setPassword(obj.get("password").toString());
            u.setGender(obj.get("gender").toString());
            u.setPhone(obj.get("phone").toString());
//            String birthdayStr = obj.get("birthday").toString();
//            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            try {
//                Date birthday = format.parse(birthdayStr);
//                u.setBirthday(birthday);
//            } catch (ParseException e) {
//                // Gérer l'exception ici
//                System.out.println("Erreur lors de la conversion de la date: " + e.getMessage());
//            }
        } catch (IOException exp) {
            System.out.println("ioexp" + exp.getMessage());
        }
        return u;
    }

    public ArrayList<User> parseUsers(String jsonText) {
        ArrayList<User> users = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> mapUsers = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapUsers.get("root");
            for (Map<String, Object> obj : listOfMaps) {
                User u = new User();
            u.setId((int) obj.get("id"));
            u.setName(obj.get("name").toString());
            u.setLast_name(obj.get("last_name").toString());
            u.setEmail(obj.get("email").toString());
            u.setPassword(obj.get("password").toString());
            u.setGender(obj.get("gender").toString());
            u.setPhone(obj.get("phone").toString());
//            String birthdayStr = obj.get("birthday").toString();
//            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            try {
//                Date birthday = format.parse(birthdayStr);
//                u.setBirthday(birthday);
//            } catch (ParseException e) {
//                // Gérer l'exception ici
//                System.out.println("Erreur lors de la conversion de la date: " + e.getMessage());
//            }
                users.add(u);
            }
        } catch (IOException exp) {
            System.out.println("ioexp" + exp.getMessage());
        }
        return users;
    }

    public ArrayList<User> getAllUsers() {
        String url = Statics.BASE_URL + "showAllUser";
        request.setPost(true);
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
                String response = new String(request.getResponseData());
                System.out.println("resp " + response);
                ArrayList<User> users = parseUsers(response);
                System.out.println("users" + users.toString());
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return users;
    }

    public User getUser(int id) {
        String url = Statics.BASE_URL + "showUser?id=" + id;
        request.setPost(true);
        request.setUrl(url);
        final User[] user = new User[1];
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
                //  System.out.println("resp "+new String(request.getResponseData()));
                user[0] = parseUser(new String(request.getResponseData()));
                // System.out.println("users"+users.toString());
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return user[0];
    }

    public ArrayList<String> emailPass(String email) {

        String url = Statics.BASE_URL + "email";
        request.addArgument("email", email);
        ArrayList<String> code = new ArrayList<>();
        request.setUrl(url);
        request.setPost(true);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser j = new JSONParser();
                String jsonText = new String(request.getResponseData());
                try {
                    Map<String, Object> obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                    code.add(obj.get("code").toString());
                    code.add(obj.get("id").toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                request.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        System.out.println("code array string " + code);
        return code;
    }
}
