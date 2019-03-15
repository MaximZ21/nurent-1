
public class Book {
    int id;
    String name;
    String owner_name;
    int owner_id;
    String current_holder_name;
    int current_holder_id;
    String description;

    public Book(int id, String name, String owner_name, int owner_id,
                String current_holder_name, int current_holder_id, String description) {
        this.id = id;
        this.name = name;
        this.owner_name = owner_name;
        this.owner_id = owner_id;
        this.current_holder_name = current_holder_name;
        this.current_holder_id = current_holder_id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getCurrent_holder_name() {
        return current_holder_name;
    }

    public void setCurrent_holder_name(String current_holder_name) {
        this.current_holder_name = current_holder_name;
    }

    public int getCurrent_holder_id() {
        return current_holder_id;
    }

    public void setCurrent_holder_id(int current_holder_id) {
        this.current_holder_id = current_holder_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


