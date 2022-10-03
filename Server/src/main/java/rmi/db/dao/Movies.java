package rmi.db.dao;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import rmi.db.models.Movie;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Movies extends DAO{
    private JSONArray catalog;
    private JSONArray movies;

    public Movies() throws IOException, ParseException {
        movies = (JSONArray)root.get("movies");
        catalog = (JSONArray)root.get("catalog");
    }

    public List<Movie> getCatalogue(){
        List<Movie> list = new ArrayList<>();
        for(Object o : catalog){
            JSONObject json_o = (JSONObject) o;
            list.add(new Movie(json_o.getAsString("name"),json_o.getAsString("ISBN"),
                    json_o.getAsString("synopsis"),json_o.getAsString("trailer")));
        }
        return list;
    }

    public ByteArrayInputStream getMovieBytes(String ISBN){
        int middle = (movies.size()/2);
        int upper_bound = movies.size();
        int lower_bound = 0;
        String movieISBN = ((JSONObject)movies.get(middle)).getAsString("ISBN");
        while(!ISBN.equals(movieISBN) && upper_bound != 0){
            if(ISBN.compareTo(movieISBN) < 0) {
                upper_bound = middle;
                middle = lower_bound + (middle-lower_bound)/2;
            }else{
                lower_bound = middle;
                middle = middle +  ((upper_bound -middle)/2);
            }
        }
/*
        for(int i = 0 ; i < movies.size() ; i++){
            if(((JSONObject)movies.get(i)).getAsString("ISBN").equals(ISBN)){

            }
        }
*/
        ByteArrayInputStream in = new ByteArrayInputStream (((JSONObject)movies.get(middle)).getAsString("movie").getBytes());
        return in;
    }

    public String getMovieName(String ISBN){
        int middle = (catalog.size()/2);
        int upper_bound = catalog.size();
        int lower_bound = 0;
        String movieISBN = ((JSONObject)catalog.get(middle)).getAsString("ISBN");
        while(!ISBN.equals(movieISBN) && upper_bound != 0){
            if(ISBN.compareTo(movieISBN) < 0) {
                upper_bound = middle;
                middle = lower_bound + (middle-lower_bound)/2;
            }else{
                lower_bound = middle;
                middle = middle +  ((upper_bound -middle)/2);
            }
        }
        if(upper_bound == 0)
            return "";
        else
            return ((JSONObject)catalog.get(middle)).getAsString("name");
    }

}
