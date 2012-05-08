package example.rest;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Path("/rest/words")
public class WordResource {
    public static DictionaryService dict = new DictionaryService();
    @GET
    @Path("{word}")
    public Word getWord(@PathParam("word") String w) {
        return dict.getWord(w);
    }

    @GET
    @Path("/")
    public List<Word> list() {
        return dict.getWords();
    }
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/")
    public Word getWord(Word w) {
        return dict.addWord(w);
    }
}
