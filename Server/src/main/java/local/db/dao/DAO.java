package local.db.dao;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Data Access class util used to get data from JSON file
 */
public abstract class DAO {
    /**
     * Root of the json file that is used as a database file
     */
    protected JSONObject root;
    /**
     * FileWriter that will be incharge of modifying the json file
     */
    protected FileWriter fileWriter;
    /**
     * FileReader that will be incharge of reading data from the json file
     */
    protected FileReader fileReader;

    /**
     * Default constructor that instantiates the file reader with the proper path to the json db file
     * @throws IOException
     * @throws ParseException
     */
    public DAO() throws IOException, ParseException {
        // file reader associated to the json file storing data
        fileReader = new FileReader("src/main/java/local/db.json");
        JSONParser parser = new JSONParser();
        // parsing the file reader and getting the root a JSONObject
        root = (JSONObject) parser.parse(fileReader);
    }

    public JSONObject getRoot() {
        return root;
    }

    public void setRoot(JSONObject root) {
        this.root = root;
    }

    public FileWriter getFileWriter() {
        return fileWriter;
    }

    public void setFileWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public FileReader getFileReader() {
        return fileReader;
    }

    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }
}
