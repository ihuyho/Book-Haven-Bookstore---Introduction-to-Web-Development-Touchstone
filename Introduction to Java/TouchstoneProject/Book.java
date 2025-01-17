package TouchstoneProject;

public class Book {
    private String title;
    private String authorLast;
    private String authorFirst;
    private String genre;
    private String rating;

    public Book(String title, String authorLast, String authorFirst, String genre, String rating) {
        this.title = title;
        this.authorLast = authorLast;
        this.authorFirst = authorFirst;
        this.genre = genre;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title.length() > 0) {
            this.title = title;
        }
    }

    public String getAuthorLast() {
        return authorLast;
    }

    public void setAuthorLast(String authorLast) {
        if(authorLast.length() > 0) {
            this.authorLast = authorLast;
        }
    }

    public String getAuthorFirst() {
        return authorFirst;
    }

    public void setAuthorFirst(String authorFirst) {
        if(authorFirst.length() > 0) {
            this.authorFirst = authorFirst;
        }
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        if(genre.length() > 0) {
            this.genre = genre;
        }
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        if(rating.length() > 0) {
            this.rating = rating;
        }
    }
    

}
