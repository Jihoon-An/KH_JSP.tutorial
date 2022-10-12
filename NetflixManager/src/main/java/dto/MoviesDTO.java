package dto;

public class MoviesDTO {
    private int id;
    private String title;
    private String genre;
    private String launch_time;

    public MoviesDTO(int id, String title, String genre, String launch_time) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.launch_time = launch_time;
    }

    public MoviesDTO(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    public MoviesDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLaunch_time() {
        return launch_time;
    }

    public void setLaunch_time(String launch_time) {
        this.launch_time = launch_time;
    }
}
