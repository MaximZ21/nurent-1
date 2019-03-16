import java.io.Serializable;

public class BookPost implements Serializable {

    String id;
    String name;
    String owner_name;
    String owner_id;
    String current_holder_name;
    String current_holder_id;
    String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getCurrent_holder_name() {
        return current_holder_name;
    }

    public void setCurrent_holder_name(String current_holder_name) {
        this.current_holder_name = current_holder_name;
    }

    public String getCurrent_holder_id() {
        return current_holder_id;
    }

    public void setCurrent_holder_id(String current_holder_id) {
        this.current_holder_id = current_holder_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
