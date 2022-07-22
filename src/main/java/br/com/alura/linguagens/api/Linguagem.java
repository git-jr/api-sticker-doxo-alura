package br.com.alura.linguagens.api;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mainLanguages")
public class Linguagem {

    @Id
    private String id;
    private String title;
    private String image;
    private int ranking;

    public Linguagem(String title, String image, int ranking) {
        this.title = title;
        this.image = image;
        this.ranking = ranking;
    }

    

    public Linguagem(String id, String title, String image, int ranking) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.ranking = ranking;
    }



    public Linguagem(){

    }
    
    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public int getRanking() {
        return ranking;
    }


    public String getId() {
        return id;
    }



    public void setId(String id) {
        this.id = id;
    }



    public void setTitle(String title) {
        this.title = title;
    }



    public void setImage(String image) {
        this.image = image;
    }



    public void setRanking(int ranking) {
        this.ranking = ranking;
    }


    

}
