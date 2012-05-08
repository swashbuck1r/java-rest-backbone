package example.rest;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class Word implements Serializable {
    @JsonProperty
    public String id;
    
    @JsonProperty
    public String word;

    @JsonProperty
    public String definition;

    @JsonProperty
    public String type;

    public Word() {
    }
    
    public Word(String word, String definition, String type) {
        this.id = word;
        this.word = word;
        this.definition = definition;
        this.type = type;
    }
}
