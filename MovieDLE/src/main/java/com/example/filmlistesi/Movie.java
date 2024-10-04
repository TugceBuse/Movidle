package com.example.filmlistesi;

public class Movie {

    private String name;
    private String year;
    private String genre;
    private String origin;
    private String director;
    private String star;
    private String link;

    private String poster;

    @Override
    public String toString(){
        return name+" "+year+" "+genre+" "+origin+" "+director+" "+star+" "+link+" "+poster;
    }

    public Movie(String name, String year, String genre, String origin, String director, String star, String link,String poster) {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.origin = origin;
        this.director = director;
        this.star = star;
        this.link = link;
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public static String[] getAllNames(){
        String[] names = new String[250];
        int k=0;
        for(Movie movie :ReadText.getMovieList()){
            names[k] = movie.getName();
            k++;
        }
        return names;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPoster(){ return "images\\movieposters\\"+poster;}
    public void setPoster(String poster) {this.poster = poster;}
}


