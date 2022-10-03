package local.db.dao;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DAO {
    protected JSONObject root;
    protected FileWriter fileWriter;
    protected FileReader fileReader;

    public DAO() throws IOException, ParseException {
        fileReader = new FileReader("src/main/java/local/db.json");
        JSONParser parser = new JSONParser();
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
