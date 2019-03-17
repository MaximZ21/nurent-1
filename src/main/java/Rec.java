import java.io.Serializable;

public class Rec implements Serializable {

    public String rtype;
    public String book_id;
    public String owner_id;
    public String owner_name;
    public String taker_id;
    public String taker_name;
    public String message;

    public Rec(String rtype, String book_id, String owner_id, String owner_name, String taker_id, String taker_name) {
        this.rtype = rtype;
        this.book_id = book_id;
        this.owner_id = owner_id;
        this.owner_name = owner_name;
        this.taker_id = taker_id;
        this.taker_name = taker_name;
    }
}
