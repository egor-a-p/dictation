package info.egor_a_petrov.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "stories")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String author;

    @Column(columnDefinition = "mediumtext")
    private String content;

    @Column(columnDefinition = "mediumblob")
    private byte[] image;

    @Column(columnDefinition = "mediumblob")
    private byte[] audio;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "stories")
    private Set<User> users;

    public Story() {
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getAudio() {
        return audio;
    }

    public void setAudio(byte[] audio) {
        this.audio = audio;
    }
}
