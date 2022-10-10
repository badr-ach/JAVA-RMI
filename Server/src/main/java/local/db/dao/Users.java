package local.db.dao;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import local.db.models.User;

import java.io.*;

/**
 * Users Data Access class to manage users data stored in the JSON file
 */
public class Users extends DAO {
    private JSONArray users;

    /**
     * Default constructor that initializes the users array from the JSON file
     * @throws IOException in case of failure to access the file
     * @throws ParseException in case of failure to parse the json file
     */
    public Users() throws IOException, ParseException {
        super();
        users = (JSONArray)root.get("users");
    }

    /**
     * Add the specified user to the database json file
     * @param User the user to be added to the file
     * @return boolean whether it was successfully added or not
     */
    public boolean AddUser(User user){
        try {
            JSONObject o = new JSONObject();
            o.put("email", user.getEmail());
            o.put("password", user.getPassword());
            users.add(o);
            this.setFileWriter(new FileWriter("src/main/java/local/db.json"));
            fileWriter.write(root.toJSONString());
            fileWriter.flush();
            return true;
        }catch(IOException e){
            System.out.println("Failed to Add User : " + e.getMessage());
            return false;
        }
    }

    /**
     * Check if the specified user exists already
     *  @param User the user to be looked up in the file
     *  @return boolean whether it was successfully found or not
     */

    public boolean findUser(User user){
        for(Object o : users){
            JSONObject json_o= (JSONObject) o;
            if(json_o.getAsString("email").equals(user.getEmail()) && json_o.getAsString("password").equals(user.getPassword()))
                return true;
        }
        return false;
    }
}
