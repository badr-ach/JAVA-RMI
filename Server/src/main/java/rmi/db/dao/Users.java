package rmi.db.dao;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import rmi.db.models.User;

import java.io.*;

public class Users extends DAO {
    private JSONArray users;

    public Users() throws IOException, ParseException {
        super();
        users = (JSONArray)root.get("users");
    }

    public boolean AddUser(User user){
        try {
            JSONObject o = new JSONObject();
            o.put("email", user.getEmail());
            o.put("password", user.getPassword());
            users.add(o);
            this.setFileWriter(new FileWriter("src/main/java/rmi/db.json"));
            fileWriter.write(root.toJSONString());
            fileWriter.flush();
            return true;
        }catch(IOException e){
            System.out.println("Failed to Add User : " + e.getMessage());
            return false;
        }
    }

    public boolean findUser(User user){
        for(Object o : users){
            JSONObject json_o= (JSONObject) o;
            if(json_o.getAsString("email").equals(user.getEmail()) && json_o.getAsString("password").equals(user.getPassword()))
                return true;
        }
        return false;
    }
}
