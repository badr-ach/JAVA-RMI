package local.db.dao;

import local.db.models.Movie;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Movies Data Access class to manage movies data stored in the JSON file
 */
public class Movies extends DAO {
    private JSONArray catalog;
    private JSONArray movies;

    /**
     * Default constructor that initializes the movies and catalog arrays from the JSON file
     * @throws IOException in case of failure to access the file
     * @throws ParseException in case of failure to parse the json file
     */
    public Movies() throws IOException, ParseException {
        movies = (JSONArray)root.get("movies");
        catalog = (JSONArray)root.get("catalog");
    }

    /**
     * Get the list of the movies and their associated description
     * @return List<Movie> A list containing all the movies stored
     */
    public List<Movie> getCatalogue(){
        List<Movie> list = new ArrayList<>();
        for(Object o : catalog){
            JSONObject json_o = (JSONObject) o;
            list.add(new Movie(json_o.getAsString("name"),json_o.getAsString("ISBN"),
                    json_o.getAsString("synopsis"),json_o.getAsString("trailer")));
        }
        return list;
    }


    /**
     * Get the movie bytes for the specified ISBN in order to play it.
     * It uses a dichotomic search approach in the array
     * @param ISBN the ISBN of the movie to be played.
     * @return ByteArrayInputStream the byte stream of the movie.
     */
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

        ByteArrayInputStream in = new ByteArrayInputStream (((JSONObject)movies.get(middle)).getAsString("movie").getBytes());
        return in;
    }


    /**
     * Get the movie name for the specified ISBN from the JSONObject
     * @param ISBN the ISBN of the movie
     * @return String the name of the movie
     */
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
