import org.apache.commons.dbutils.DbUtils;
import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

class Request {

    private static final String LogStatement = "INSERT INTO Logs(date_time, username, activity, result, additional_info)" +
            "VALUES(?,?,?,?,?);";




    public String getOption(int book_id, int o, int c, int m){
        String option = "";
        if(o == c && c!=m){
            Connector connector = new Connector();
            Connection conn = connector.getConnection();
            ResultSet rs = null;
            PreparedStatement ps = null;
            try {
                String query1 = "SELECT * FROM library.requests WHERE book_id = ? AND taker_id = ? AND type = 'take';";
                System.out.println(query1);
                PreparedStatement ps1 = conn.prepareStatement(query1);
                ps1.setInt(1,book_id);
                ps1.setInt(2,m);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    return "patt";
                }
                else{
                    return "request";
                }
            } catch (Exception ex) {
                System.out.println("Exception in getUserId" + ex.getMessage());
            } finally {
                DbUtils.closeQuietly(rs);
                DbUtils.closeQuietly(ps);
                DbUtils.closeQuietly(conn);
            }
        }
        if(o==c && o==m){
            Connector connector = new Connector();
            Connection conn = connector.getConnection();
            ResultSet rs = null;
            PreparedStatement ps = null;
            try {
                String query1 = "SELECT * FROM library.requests WHERE book_id = ? ;";
                System.out.println(query1);
                PreparedStatement ps1 = conn.prepareStatement(query1);
                ps1.setInt(1,book_id);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    return "displayRequests";
                }
                else{
                    return "nothing";
                }
            } catch (Exception ex) {
                System.out.println("Exception in getUserId" + ex.getMessage());
            } finally {
                DbUtils.closeQuietly(rs);
                DbUtils.closeQuietly(ps);
                DbUtils.closeQuietly(conn);
            }
            return "";
        }
        if(o!=c && m == c){
            Connector connector = new Connector();
            Connection conn = connector.getConnection();
            ResultSet rs = null;
            PreparedStatement ps = null;
            try {
                String query1 = "SELECT * FROM library.requests WHERE book_id = ? AND taker_id = ? AND type = 'return';";
                System.out.println(query1);
                PreparedStatement ps1 = conn.prepareStatement(query1);
                ps1.setInt(1,book_id);
                ps1.setInt(2,m);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    return "patr";
                }
                else{
                    return "return";
                }
            } catch (Exception ex) {
                System.out.println("Exception in getUserId" + ex.getMessage());
            } finally {
                DbUtils.closeQuietly(rs);
                DbUtils.closeQuietly(ps);
                DbUtils.closeQuietly(conn);
            }
        }
        if(o!=c && m!=c){
            return "taken";
        }

        return option;
    }

    public boolean at(int taker_id, int book_id, int owner_id, String owner_name, String taker_name){
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String psquery1 = "INSERT INTO library.requests(type, book_id, owner_id, owner_name, taker_id, taker_name) " +
                    "VALUES(?,?,?,?,?,?);";
            PreparedStatement ps1 = conn.prepareStatement(psquery1);
            ps1.setString(1,"return");
            ps1.setInt(2,book_id);
            ps1.setInt(3,owner_id);
            ps1.setString(4,owner_name);
            ps1.setInt(5,taker_id);
            ps1.setString(6,taker_name);
            ps1.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Exception in getUserId" + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
        return false;
    }


    public boolean returnBook(int taker_id, int book_id, int owner_id, String owner_name, String taker_name){
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String psquery1 = "INSERT INTO library.requests(type, book_id, owner_id, owner_name, taker_id, taker_name) " +
                    "VALUES(?,?,?,?,?,?);";
            PreparedStatement ps1 = conn.prepareStatement(psquery1);
            ps1.setString(1,"return");
            ps1.setInt(2,book_id);
            ps1.setInt(3,owner_id);
            ps1.setString(4,owner_name);
            ps1.setInt(5,taker_id);
            ps1.setString(6,taker_name);
            ps1.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Exception in getUserId" + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
        return false;
    }

    public boolean requestBook(int taker_id, int book_id, int owner_id, String owner_name, String taker_name){
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String psquery1 = "INSERT INTO library.requests(type, book_id, owner_id, owner_name, taker_id, taker_name) " +
                    "VALUES(?,?,?,?,?,?);";
            PreparedStatement ps1 = conn.prepareStatement(psquery1);
            ps1.setString(1,"take");
            ps1.setInt(2,book_id);
            ps1.setInt(3,owner_id);
            ps1.setString(4,owner_name);
            ps1.setInt(5,taker_id);
            ps1.setString(6,taker_name);
            ps1.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Exception in getUserId" + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
        return false;
    }


    public int getUserId(String token){
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String query1 = "SELECT id FROM library.users WHERE token = ? ;";
            System.out.println(query1);
            conn = connector.getConnection();
            PreparedStatement ps1 = conn.prepareStatement(query1);
            ps1.setString(1,token);
            rs = ps1.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception ex) {
            System.out.println("Exception in getUserId" + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
        return -1;
    }

    public String getUserName(String token){
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String fname = "";
        String sname = "";
        try {
            String query1 = "SELECT fname,sname FROM library.users WHERE token = ? ;";
            System.out.println(query1);
            conn = connector.getConnection();
            PreparedStatement ps1 = conn.prepareStatement(query1);
            ps1.setString(1,token);
            rs = ps1.executeQuery();
            if (rs.next()) {
                fname = rs.getString("fname");
                sname = rs.getString("sname");
            }
            return fname+" "+sname;
        } catch (Exception ex) {
            System.out.println("Exception in getUserId" + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
        return "";
    }

    public Book getBookInfo(int id){
        Book book = null;
        Rec rec = null;
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String query2 = "SELECT * FROM library.books WHERE id = ? ;";
            System.out.println(query2);
            PreparedStatement ps1 = conn.prepareStatement(query2);
            ps1.setInt(1,id);
            rs = ps1.executeQuery();
            if (rs.next()) {
                book = new Book(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("owner_name"),
                        rs.getInt("owner_id"),
                        rs.getString("current_holder_name"),
                        rs.getInt("current_holder_id"),
                        rs.getString("description")
                );
            }
            System.out.println(book.name);
        } catch (Exception ex) {
            System.out.println("Exception in getBookInfo: " + ex.getMessage());
        } finally {
//            DbUtils.closeQuietly(rs);
//            DbUtils.closeQuietly(ps);
//            DbUtils.closeQuietly(conn);
        }
        try {
            String query1 = "SELECT * FROM library.requests WHERE book_id = ? ;";
            System.out.println(query1);
            PreparedStatement ps1 = conn.prepareStatement(query1);
            ps1.setInt(1,id);
            rs = ps1.executeQuery();
            System.out.println("ID");
            System.out.println(id);
            if (rs.next()) {
                rec = new Rec(
                        rs.getString("type"),
                        rs.getString("book_id"),
                        Integer.toString(rs.getInt("owner_id")),
                        rs.getString("owner_name"),
                        Integer.toString((rs.getInt("taker_id"))),
                        rs.getString("taker_name")
                );
                book.recs.add(rec);
            }
            return book;
        } catch (Exception ex) {
            System.out.println("Exception in getBookInfo: " + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }

        return book;
    }

    public String generateToken(String username) {
        String uuid = UUID.randomUUID().toString();
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        PreparedStatement ps = null;
        try {
            String psquery1 = "UPDATE library.users SET library.users.token = ? WHERE username = ?;";
            ps = conn.prepareStatement(psquery1);
            ps.setString(1, uuid);
            ps.setString(2, username);
            ps.executeUpdate();
            return uuid;
        } catch (Exception ex) {
            System.out.println("Exception in generateToken(): " + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
        return null;
    }

    public String generateTokenForModerator(String username) {
        String uuid = UUID.randomUUID().toString();
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        PreparedStatement ps = null;
        try {
            String psquery1 = "UPDATE bitlab.Moderators SET token = ? WHERE username = ?;";
            conn = connector.getConnection();
            ps = conn.prepareStatement(psquery1);
            ps.setString(1, uuid);
            ps.setString(2, username);
            ps.executeUpdate();
            return uuid;
        } catch (Exception ex) {
            System.out.println("Exception in generateToken(): " + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
        return null;
    }

    public boolean checkToken(String token) {
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String psquery1 = "SELECT username FROM library.users WHERE token = ?;";
            conn = connector.getConnection();
            ps = conn.prepareStatement(psquery1);
            ps.setString(1, token);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception ex) {
            System.out.println("Exception in checkToken() " + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
        return false;
    }

    public boolean checkTokenForModerator(String token) {
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String psquery1 = "SELECT username FROM bitlab.Moderators WHERE token = ?;";
            ps = conn.prepareStatement(psquery1);
            ps.setString(1, token);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception ex) {
            System.out.println("Exception in checkTokenForModerator() " + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
        return false;
    }

    public void deleteToken(String token_to_delete) {
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String username = "";
        try {
            String psquery1 = "SELECT username FROM library.users WHERE token = ?;";
            ps = conn.prepareStatement(psquery1);
            ps.setString(1, token_to_delete);
            rs = ps.executeQuery();
            if (rs.next()) {
                username = rs.getString("username");
            }
            String psquery2 = "UPDATE library.users SET token = NULL WHERE token = ?;";
            ps = conn.prepareStatement(psquery2);
            ps.setString(1, token_to_delete);
            ps = conn.prepareStatement(LogStatement);
            ps.setString(1, getTime());
            ps.setString(2, username);
            ps.setString(3, "Log Out");
            ps.setString(4, "Success");
            ps.setString(5, username);
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Exception in deleteToken(): " + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }

    public void deleteTokenForModerator(String token_to_delete) {
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String username = "";
        try {
            String psquery1 = "SELECT username FROM Moderators WHERE token = ?;";
            ps = conn.prepareStatement(psquery1);
            ps.setString(1, token_to_delete);
            rs = ps.executeQuery();
            if (rs.next()) {
                username = rs.getString("username");
            }
            String psquery2 = "UPDATE Moderators SET token = NULL WHERE token = ?;";
            ps = conn.prepareStatement(psquery2);
            ps.setString(1, token_to_delete);
            ps = conn.prepareStatement(LogStatement);
            ps.setString(1, getTime());
            ps.setString(2, username);
            ps.setString(3, "Log Out");
            ps.setString(4, "Success");
            ps.setString(5, username);
        } catch (Exception ex) {
            System.out.println("Exception in deleteTokenForModerator(): " + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }

    public void addNewUser(String username, String password, String name, String surname, String phone, String email) throws Exception {
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String psquery1 = "SELECT * FROM library.users WHERE username = ?;";
            conn = connector.getConnection();
            ps = conn.prepareStatement(psquery1);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                throw new Exception("User with such username already exists");
            }
            String psquery2 = "SELECT * FROM library.users WHERE phone = ?;";
            ps = conn.prepareStatement(psquery2);
            ps.setString(1, phone);
            rs = ps.executeQuery();
            if (rs.next()) {
                throw new Exception("User with such phone number already exists");
            }
            String psquery10 = "SELECT * FROM library.users WHERE email = ?;";
            ps = conn.prepareStatement(psquery10);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                throw new Exception("User with such email already exists");
            }
            String psquery3 = "INSERT INTO library.users(fname,sname,username, password, email, phone) " +
                    "VALUES(?,?,?,?,?,?);";
            ps = conn.prepareStatement(psquery3);
            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setString(3, username);
            ps.setString(4, generateHash(password));
            ps.setString(5,email);
            ps.setString(6, phone);
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Exception in addNewUser: " + ex.getMessage());
            throw new Exception("Exception in addNewUser:" + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }

    public void addNewModerator(String username, String password) throws Exception {
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String psquery1 = "SELECT * FROM bitlab.Moderators WHERE username = ?;";
            conn = connector.getConnection();
            ps = conn.prepareStatement(psquery1);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                throw new Exception("Moderator with such username already exists");
            }
            String psquery2 = "INSERT INTO Moderators(username, password) " +
                    "VALUES(?,?);";
            ps = conn.prepareStatement(psquery2);
            ps.setString(1, username);
            ps.setString(2, generateHash(password));
            ps.executeUpdate();
            ps = conn.prepareStatement(LogStatement);
            ps.setString(1, getTime());
            ps.setString(2, username);
            ps.setString(3, "Add Moderator");
            ps.setString(4, "Success");
            ps.setString(5, username);
            ps.executeUpdate();
        } catch (Exception ex) {
            ps = conn.prepareStatement(LogStatement);
            ps.setString(1, getTime());
            ps.setString(2, username);
            ps.setString(3, "Add Moderaor");
            ps.setString(4, "Failure");
            ps.setString(5, ex.getMessage());
            ps.executeUpdate();
            System.out.println("Exception in addNewModerator: " + ex.getMessage());
            throw new Exception("Exception in addNewModerator:" + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }

    public void addBook(String name, String token) {
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        int id = -1;
        String fname = "";
        String sname = "";
        try {
            String psquery1 = "SELECT id,fname,sname FROM library.users WHERE token = ?;";
            ps = conn.prepareStatement(psquery1);
            ps.setString(1, token);
            rs = ps.executeQuery();
            if (rs.next()) {
                fname = rs.getString("fname");
                sname = rs.getString("sname");
                id = rs.getInt("id");
                String psquery2 = "INSERT INTO library.books" +
                        "(name, owner_name, owner_id, current_holder_name,  current_holder_id) " +
                        "VALUES(?,?,?,?,?);";
                ps = conn.prepareStatement(psquery2);
                ps.setString(1, name);
                ps.setString(2, fname+" "+sname);
                ps.setInt(3, id);
                ps.setString(4, fname+" "+sname);
                ps.setInt(5, id);
                ps.executeUpdate();
            }
        } catch (Exception ex) {
            try {
                System.out.println("Exception in addListing() " + ex.getMessage());
            } catch (Exception ex2) {
                System.out.println("Exception in addListing() 2" + ex.getMessage());
            } finally {
                DbUtils.closeQuietly(rs);
                DbUtils.closeQuietly(ps);
                DbUtils.closeQuietly(conn);
            }
        }
    }

    public void checkNameAndPassword(String username, String password) throws Exception {
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String psquery1 = "SELECT password FROM library.users WHERE username = ?;";
            ps = conn.prepareStatement(psquery1);
            ps.setString(1, username);
            rs = ps.executeQuery();
            boolean next = rs.next();
            if (!next) {
                throw new Exception("Invalid username");
            }
            String pass = rs.getString("password");
            System.out.println(pass);
            System.out.println(password);
            System.out.println(generateHash(password));
            if (!pass.equals(generateHash(password))) {
                throw new Exception("Invalid password");
            }
        } catch (Exception ex) {
            System.out.println("Exception in checkNameAndPassword: " + ex.getMessage());
            throw new Exception("Exception in checkNameAndPassword:" + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }

    public void checkNameAndPasswordForModerator(String username, String password) throws Exception {
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String psquery1 = "SELECT password FROM bitlab.Moderators WHERE username = ?;";
            ps = conn.prepareStatement(psquery1);
            ps.setString(1, username);
            rs = ps.executeQuery();
            boolean next = rs.next();
            if (!next) {
                throw new Exception("Invalid username");
            }
            String pass = rs.getString("password");
            if (!pass.equals(generateHash(password))) {
                throw new Exception("Invalid password");
            }
            ps = conn.prepareStatement(LogStatement);
            ps.setString(1, getTime());
            ps.setString(2, username);
            ps.setString(3, "Log In (Moderator)");
            ps.setString(4, "Success");
            ps.setString(5, username);
            ps.executeUpdate();
        } catch (Exception ex) {
            ps = conn.prepareStatement(LogStatement);
            ps.setString(1, getTime());
            ps.setString(2, username);
            ps.setString(3, "Log In (Moderator)");
            ps.setString(4, "Failure");
            ps.setString(5, ex.getMessage());
            ps.executeUpdate();
            System.out.println("Exception in checkNameAndPasswordForModerator: " + ex.getMessage());
            throw new Exception("Exception in checkNameAndPasswordForModerator:" + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }

    // possible sort_types: by_num_of_rooms , by_city_name , by_price ;
    // posiible sort_order: asc , desc (or)  ASC , DESC ;
    // if sort_order == null -> asc will be used
    public List<Book> getBookssByParameters(String name, String owner_name, String current_holder_name) {
        LinkedList<Book> list = new LinkedList<>();
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String query1 = "SELECT * FROM library.books";
            query1 += " WHERE 1 = 1";
            if (name != null) {
                query1 += " AND";
                query1 += " name is like '%" + name + "%'";
            }
            if (owner_name != null) {
                query1 += " AND";
                query1 += " owner_name is like '%" + owner_name + "%'";
            }
            if (current_holder_name != null) {
                query1 += " AND";
                query1 += " current_holder_name is like '%= " + current_holder_name + "%'";
            }
            query1 += ";";
            System.out.println(query1);
            conn = connector.getConnection();
            Statement st = conn.createStatement();
            rs = st.executeQuery(query1);
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("owner_name"),
                        rs.getInt("owner_id"),
                        rs.getString("current_holder_name"),
                        rs.getInt("current_holder_id"),
                        rs.getString("description")
                );
                list.addLast(book);
            }
            return list;
        } catch (Exception ex) {
            System.out.println("Exception in getListingsByParameters: " + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
        return null;
    }

    public List<Book> getBooksForUser(String token) {
        LinkedList<Book> list = new LinkedList<>();
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        int id = -1;
        try {
            String psquery1 = "SELECT id FROM library.users WHERE token = ?;";
            conn = connector.getConnection();
            ps = conn.prepareStatement(psquery1);
            ps.setString(1, token);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
            String psquery2 = "SELECT * FROM library.books WHERE owner_id = ?;";
            ps = conn.prepareStatement(psquery2);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("owner_name"),
                        rs.getInt("owner_id"),
                        rs.getString("current_holder_name"),
                        rs.getInt("current_holder_id"),
                        rs.getString("description")
                );
                list.addLast(book);
            }
            return list;
        } catch (Exception ex) {
            System.out.println("Exception in getBooksForUser: " + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
        return null;
    }

    public void deleteBook(String id, String token) {
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        int id1 = -1;
        int id2 = -1;
        try {
            String psquery1 = "SELECT id FROM library.users WHERE token = ?;";
            conn = connector.getConnection();
            ps = conn.prepareStatement(psquery1);
            ps.setString(1, token);
            rs = ps.executeQuery();
            if (rs.next()) {
                id1 = rs.getInt("id");
            }
            String psquery2 = "SELECT owner_id FROM library.books WHERE id = ?;";
            ps = conn.prepareStatement(psquery2);
            ps.setInt(1, Integer.parseInt(id));
            rs = ps.executeQuery();
            if (rs.next()) {
                id2 = rs.getInt("owner_id");
            }
            if (id1 == id2) {
                String psquery3 = "DELETE FROM library.books WHERE id = ?;";
                ps = conn.prepareStatement(psquery3);
                ps.setInt(1, Integer.parseInt(id));
                ps.executeUpdate();
            } else {
                //
            }
        } catch (Exception ex) {
            try {
                System.out.println("Exception in addListing() " + ex.getMessage());
            } catch (Exception ex2) {
                System.out.println("Exception in deleteListing() 2" + ex.getMessage());
            }
            System.out.println("Exception in deleteListing: " + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }

    public void hideListing(String id, String token) {
        System.out.println("1");
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String username1 = "";
        String username2 = "";
        try {
            String psquery1 = "SELECT username FROM Accounts WHERE token = ?;";
            conn = connector.getConnection();
            ps = conn.prepareStatement(psquery1);
            ps.setString(1, token);
            rs = ps.executeQuery();
            if (rs.next()) {
                username1 = rs.getString("username");
            }
            String psquery2 = "SELECT username FROM Listings WHERE id = ?;";
            ps = conn.prepareStatement(psquery2);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                username2 = rs.getString("username");
            }
            if (username1.equals(username2)) {
                String psquery3 = "SELECT status FROM Listings WHERE id = ?;";
                ps = conn.prepareStatement(psquery3);
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String status = rs.getString("status");
                    if (status.equals("visible")) {
                        String psquery4 = "UPDATE Listings SET status = 'hidden' WHERE id = ?;";
                        ps = conn.prepareStatement(psquery4);
                        ps.setString(1, id);
                        ps.executeUpdate();
                        ps = conn.prepareStatement(LogStatement);
                        ps.setString(1, getTime());
                        ps.setString(2, username1);
                        ps.setString(3, "Hide Listing");
                        ps.setString(4, "Success");
                        ps.setString(5, username1);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Exception in hideListing: " + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }

    public void showListing(String id, String token) {
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String username1 = "";
        String username2 = "";
        try {
            String psquery1 = "SELECT username FROM Accounts WHERE token = ?;";
            conn = connector.getConnection();
            ps = conn.prepareStatement(psquery1);
            ps.setString(1, token);
            rs = ps.executeQuery();
            if (rs.next()) {
                username1 = rs.getString("username");
            }
            String psquery2 = "SELECT username FROM Listings WHERE id = ?;";
            ps = conn.prepareStatement(psquery2);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                username2 = rs.getString("username");
            }
            if (username1.equals(username2)) {
                String psquery3 = "SELECT status FROM Listings WHERE id = ?;";
                ps = conn.prepareStatement(psquery3);
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String status = rs.getString("status");
                    if (status.equals("hidden")) {
                        String psquery4 = "UPDATE Listings SET status = 'visible' WHERE id = ?;";
                        ps = conn.prepareStatement(psquery4);
                        ps.setString(1, id);
                        ps.executeUpdate();
                        ps = conn.prepareStatement(LogStatement);
                        ps.setString(1, getTime());
                        ps.setString(2, username1);
                        ps.setString(3, "Show Listing");
                        ps.setString(4, "Success");
                        ps.setString(5, username1);
                        ps.executeUpdate();
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Exception in showListing: " + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }

    public void deleteListingForModerator(String id, String token) {
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String username1;
        try {
            String psquery1 = "SELECT username FROM Moderators WHERE token = ?;";
            conn = connector.getConnection();
            ps = conn.prepareStatement(psquery1);
            ps.setString(1, token);
            rs = ps.executeQuery();
            if (rs.next()) {
                username1 = rs.getString("username");
                String psquery2 = "DELETE FROM Listings WHERE id = ?;";
                ps = conn.prepareStatement(psquery2);
                ps.setString(1, id);
                ps.executeUpdate();
                ps = conn.prepareStatement(LogStatement);
                ps.setString(1, getTime());
                ps.setString(2, username1);
                ps.setString(3, "Delete Listing (Moderator)");
                ps.setString(4, "Success");
                ps.setString(5, username1);
                ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Exception in deleteListingForModerator: " + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }

    public void approveListingForModerator(String id, String token) {
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String username1;
        try {
            String psquery1 = "SELECT username FROM Moderators WHERE token = ?;";
            conn = connector.getConnection();
            ps = conn.prepareStatement(psquery1);
            ps.setString(1, token);
            rs = ps.executeQuery();
            if (rs.next()) {
                username1 = rs.getString("username");
                String psquery2 = "UPDATE Listings SET status = 'visible' WHERE id = ?;";
                ps = conn.prepareStatement(psquery2);
                ps.setString(1, id);
                ps.executeUpdate();
                ps = conn.prepareStatement(LogStatement);
                ps.setString(1, getTime());
                ps.setString(2, username1);
                ps.setString(3, "Approve Listing (Moderator)");
                ps.setString(4, "Success");
                ps.setString(5, username1);
                ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Exception in approveListingForModerator: " + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }

    public void notApproveListingForModerator(String id, String token, String comment) {
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String username1;
        try {
            String psquery1 = "SELECT username FROM Moderators WHERE token = ?;";
            conn = connector.getConnection();
            ps = conn.prepareStatement(psquery1);
            ps.setString(1, token);
            rs = ps.executeQuery();
            if (rs.next()) {
                username1 = rs.getString("username");
                String psquery2 = "UPDATE Listings SET status = 'not approved', comment = ?  WHERE id = ?;";
                ps = conn.prepareStatement(psquery2);
                ps.setString(1,comment);
                ps.setString(2, id);
                ps.executeUpdate();
                ps = conn.prepareStatement(LogStatement);
                ps.setString(1, getTime());
                ps.setString(2, username1);
                ps.setString(3, "Disapprove Listing (Moderator)");
                ps.setString(4, "Success");
                ps.setString(5, username1);
                ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Exception in NotApproveListingForModerator: " + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }

//    public List<Account> getAccountsForModerator(String token) {
//        LinkedList<Account> toReturn = new LinkedList<>();
//        Connector connector = new Connector();
//        Connection conn = connector.getConnection();
//        ResultSet rs = null;
//        PreparedStatement ps = null;
//        try {
//            conn = connector.getConnection();
//            String psquery1 = "SELECT username FROM Moderators WHERE token = ?;";
//            ps = conn.prepareStatement(psquery1);
//            ps.setString(1, token);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                String psquery2 = "SELECT * FROM Accounts ORDER BY username ASC;";
//                ps = conn.prepareStatement(psquery2);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    Account acc = new Account(
//                            rs.getString("username"),
//                            rs.getString("password"),
//                            rs.getString("name"),
//                            rs.getString("surname"),
//                            rs.getString("email"),
//                            rs.getString("phone")
//                    );
//                    toReturn.addLast(acc);
//                }
//                return toReturn;
//            }
//        } catch (Exception ex) {
//            System.out.println("Exception in getAccountsForModerator: " + ex.getMessage());
//        } finally {
//            DbUtils.closeQuietly(rs);
//            DbUtils.closeQuietly(ps);
//            DbUtils.closeQuietly(conn);
//        }
//        return null;
//    }

//    public List<Listing> getListingsUnderModerationForModerator(String token) {
//        LinkedList<Listing> list = new LinkedList<>();
//        Connector connector = new Connector();
//        Connection conn = connector.getConnection();
//        ResultSet rs = null;
//        PreparedStatement ps = null;
//        String username = "";
//        try {
//            conn = connector.getConnection();
//            String psquery1 = "SELECT username FROM Moderators WHERE token = ?;";
//            ps = conn.prepareStatement(psquery1);
//            ps.setString(1, token);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                username = rs.getString("username");
//            }
//            String psquery2 = "SELECT * FROM Listings WHERE status = 'under moderation';";
//            ps = conn.prepareStatement(psquery2);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                Listing listing = new Listing(rs.getInt("id"),
//                        rs.getString("username"),
//                        rs.getString("title"),
//                        rs.getString("city"),
//                        rs.getString("building"),
//                        rs.getInt("num_of_rooms"),
//                        rs.getString("description"),
//                        rs.getInt("price"),
//                        rs.getString("postdate"),
//                        rs.getString("contact_info"),
//                        rs.getString("status"),
//                        rs.getString("comment"),
//                        rs.getString("image")
//                );
//                list.addLast(listing);
//            }
//            ps = conn.prepareStatement(LogStatement);
//            ps.setString(1, getTime());
//            ps.setString(2, username);
//            ps.setString(3, "get not approved listings (moderator)");
//            ps.setString(4, "Success");
//            ps.setString(4, "-");
//            return list;
//        } catch (Exception ex) {
//            System.out.println("Exception in getListingsForUser: " + ex.getMessage());
//        } finally {
//            DbUtils.closeQuietly(rs);
//            DbUtils.closeQuietly(ps);
//            DbUtils.closeQuietly(conn);
//        }
//        return list;
//    }

//    public List<Listing> getListingsUnderModeration(String token) {
//        LinkedList<Listing> list = new LinkedList<>();
//        Connector connector = new Connector();
//        Connection conn = connector.getConnection();
//        ResultSet rs = null;
//        PreparedStatement ps = null;
//        String username = "";
//        try {
//            String query1 = "SELECT username FROM Accounts WHERE token = '" + token + "';";
//            conn = connector.getConnection();
//            Statement st = conn.createStatement();
//            rs = st.executeQuery(query1);
//            if (rs.next()) {
//                username = rs.getString("username");
//            }
//            query1 = "SELECT * FROM Listings WHERE username = '" + username + "' AND status = 'under moderation';";
//            rs = st.executeQuery(query1);
//            while (rs.next()) {
//                Listing listing = new Listing(rs.getInt("id"),
//                        rs.getString("username"),
//                        rs.getString("title"),
//                        rs.getString("city"),
//                        rs.getString("building"),
//                        rs.getInt("num_of_rooms"),
//                        rs.getString("description"),
//                        rs.getInt("price"),
//                        rs.getString("postdate"),
//                        rs.getString("contact_info"),
//                        rs.getString("status"),
//                        rs.getString("comment"),
//                        rs.getString("image")
//                );
//                list.addLast(listing);
//            }
//            ps = conn.prepareStatement(LogStatement);
//            ps.setString(1, getTime());
//            ps.setString(2, username);
//            ps.setString(3, "get listings under moderation");
//            ps.setString(4, "Success");
//            ps.setString(5, "-");
//            ps.executeUpdate();
//            return list;
//        } catch (Exception ex) {
//            System.out.println("Exception in getListingsUnderModeration: " + ex.getMessage());
//        } finally {
//            DbUtils.closeQuietly(rs);
//            DbUtils.closeQuietly(ps);
//            DbUtils.closeQuietly(conn);
//        }
//        return list;
//    }

//    public List<Listing> getVisibleListings(String token) {
//        LinkedList<Listing> list = new LinkedList<>();
//        Connector connector = new Connector();
//        Connection conn = connector.getConnection();
//        ResultSet rs = null;
//        PreparedStatement ps = null;
//        String username = "";
//        try {
//            String query1 = "SELECT username FROM Accounts WHERE token = '" + token + "';";
//            conn = connector.getConnection();
//            Statement st = conn.createStatement();
//            rs = st.executeQuery(query1);
//            if (rs.next()) {
//                username = rs.getString("username");
//            }
//            query1 = "SELECT * FROM Listings WHERE username = '" + username + "' AND status = 'visible';";
//            rs = st.executeQuery(query1);
//            while (rs.next()) {
//                Listing listing = new Listing(rs.getInt("id"),
//                        rs.getString("username"),
//                        rs.getString("title"),
//                        rs.getString("city"),
//                        rs.getString("building"),
//                        rs.getInt("num_of_rooms"),
//                        rs.getString("description"),
//                        rs.getInt("price"),
//                        rs.getString("postdate"),
//                        rs.getString("contact_info"),
//                        rs.getString("status"),
//                        rs.getString("comment"),
//                        rs.getString("image")
//                );
//                list.addLast(listing);
//            }
//            ps = conn.prepareStatement(LogStatement);
//            ps.setString(1, getTime());
//            ps.setString(2, username);
//            ps.setString(3, "get visible listings");
//            ps.setString(4, "Success");
//            ps.setString(5, "-");
//            ps.executeUpdate();
//            return list;
//        } catch (Exception ex) {
//            System.out.println("Exception in getVisibleListings: " + ex.getMessage());
//        } finally {
//            DbUtils.closeQuietly(rs);
//            DbUtils.closeQuietly(ps);
//            DbUtils.closeQuietly(conn);
//        }
//        return list;
//    }

//    public List<Listing> getHiddenListings(String token) {
//        LinkedList<Listing> list = new LinkedList<>();
//        Connector connector = new Connector();
//        Connection conn = connector.getConnection();
//        ResultSet rs = null;
//        PreparedStatement ps = null;
//        String username = "";
//        try {
//            String query1 = "SELECT username FROM Accounts WHERE token = '" + token + "';";
//            conn = connector.getConnection();
//            Statement st = conn.createStatement();
//            rs = st.executeQuery(query1);
//            if (rs.next()) {
//                username = rs.getString("username");
//            }
//            query1 = "SELECT * FROM Listings WHERE username = '" + username + "' AND status = 'hidden';";
//            rs = st.executeQuery(query1);
//            while (rs.next()) {
//                Listing listing = new Listing(rs.getInt("id"),
//                        rs.getString("username"),
//                        rs.getString("title"),
//                        rs.getString("city"),
//                        rs.getString("building"),
//                        rs.getInt("num_of_rooms"),
//                        rs.getString("description"),
//                        rs.getInt("price"),
//                        rs.getString("postdate"),
//                        rs.getString("contact_info"),
//                        rs.getString("status"),
//                        rs.getString("comment"),
//                        rs.getString("image")
//                );
//                list.addLast(listing);
//            }
//            ps = conn.prepareStatement(LogStatement);
//            ps.setString(1, getTime());
//            ps.setString(2, username);
//            ps.setString(3, "get hidden listings");
//            ps.setString(4, "Success");
//            ps.setString(5, "-");
//            ps.executeUpdate();
//            return list;
//        } catch (Exception ex) {
//            System.out.println("Exception in getHiddenListings: " + ex.getMessage());
//        } finally {
//            DbUtils.closeQuietly(rs);
//            DbUtils.closeQuietly(ps);
//            DbUtils.closeQuietly(conn);
//        }
//        return list;
//    }

//    public List<Listing> getNotApprovedListings(String token) {
//        LinkedList<Listing> list = new LinkedList<>();
//        Connector connector = new Connector();
//        Connection conn = connector.getConnection();
//        ResultSet rs = null;
//        PreparedStatement ps = null;
//        String username = "";
//        try {
//            String query1 = "SELECT username FROM Accounts WHERE token = '" + token + "';";
//            conn = connector.getConnection();
//            Statement st = conn.createStatement();
//            rs = st.executeQuery(query1);
//            if (rs.next()) {
//                username = rs.getString("username");
//            }
//            query1 = "SELECT * FROM Listings WHERE username = '" + username + "' AND status = 'not approved';";
//            rs = st.executeQuery(query1);
//            while (rs.next()) {
//                Listing listing = new Listing(rs.getInt("id"),
//                        rs.getString("username"),
//                        rs.getString("title"),
//                        rs.getString("city"),
//                        rs.getString("building"),
//                        rs.getInt("num_of_rooms"),
//                        rs.getString("description"),
//                        rs.getInt("price"),
//                        rs.getString("postdate"),
//                        rs.getString("contact_info"),
//                        rs.getString("status"),
//                        rs.getString("comment"),
//                        rs.getString("image")
//                );
//                list.addLast(listing);
//            }
//            ps = conn.prepareStatement(LogStatement);
//            ps.setString(1, getTime());
//            ps.setString(2, username);
//            ps.setString(3, "get not approved listings");
//            ps.setString(4, "Success");
//            ps.setString(5, "-");
//            ps.executeUpdate();
//            return list;
//        } catch (Exception ex) {
//            System.out.println("Exception in getNotApprovedListings: " + ex.getMessage());
//        } finally {
//            DbUtils.closeQuietly(rs);
//            DbUtils.closeQuietly(ps);
//            DbUtils.closeQuietly(conn);
//        }
//        return list;
//    }

    public void banAccountForModerator(String username, String token) {
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String username1;
        try {
            conn = connector.getConnection();
            String psquery1 = "SELECT username FROM Moderators WHERE token = ?;";
            ps = conn.prepareStatement(psquery1);
            ps.setString(1, token);
            rs = ps.executeQuery();
            if (rs.next()) {
                username1 = rs.getString("username");
                String psquery2 = "UPDATE bitlab.Accounts SET banned = 1 WHERE username = ?;";
                ps = conn.prepareStatement(psquery2);
                ps.setString(1, username);
                ps.executeUpdate();
                ps = conn.prepareStatement(LogStatement);
                ps.setString(1, getTime());
                ps.setString(2, username1);
                ps.setString(3, "Ban Account");
                ps.setString(4, "Success");
                ps.setString(5, username);
                ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Exception in banAccountForModerator: " + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }

    public void unbanAccountForModerator(String username, String token) {
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String username1;
        try {
            conn = connector.getConnection();
            String psquery1 = "SELECT username FROM Moderators WHERE token = ?;";
            ps = conn.prepareStatement(psquery1);
            ps.setString(1, token);
            rs = ps.executeQuery();
            if (rs.next()) {
                username1 = rs.getString("username");
                String psquery2 = "UPDATE bitlab.Accounts SET banned = 0 WHERE username = ?;";
                ps = conn.prepareStatement(psquery2);
                ps.setString(1, username);
                ps.executeUpdate();
                ps = conn.prepareStatement(LogStatement);
                ps.setString(1, getTime());
                ps.setString(2, username1);
                ps.setString(3, "Unban Account");
                ps.setString(4, "Success");
                ps.setString(5, username);
                ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Exception in banAccountForModerator: " + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }

    public List<LogRec> getLogsByParameter(String token, boolean user, String username,
                                           boolean logins, boolean listings, String startDate , String endDate) {
        LinkedList<LogRec> toReturn = new LinkedList<>();
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = connector.getConnection();
            String psquery1 = "SELECT username FROM bitlab.Moderators WHERE token = ?;";
            ps = conn.prepareStatement(psquery1);
            ps.setString(1, token);
            rs = ps.executeQuery();
            if (rs.next()) {
                if (user || logins || listings) {
                    String psquery2 = "SELECT * FROM bitlab.Logs WHERE";
                    boolean or = false;
                    if (user) {
                        psquery2 += " username = ? AND ";
                    }
                    if(startDate != null){
                        psquery2 += " date_time >= ? AND ";
                    }
                    if(endDate != null){
                        psquery2 += " date_time <= ? AND ";
                    }
                    if (logins) {
                        psquery2 += "( (activity = 'Log In' OR activity = 'Log Out' " +
                                "OR activity = 'Registration' OR activity = 'Log In(Moderator)' " +
                                "OR activity = 'Log Out(Moderator)' OR activity = 'Add Moderator')";
                        or = true;
                    }
                    if (listings) {
                        if (or) {
                            psquery2 += " OR";
                        }
                        psquery2 += " (activity = 'Show Listing' OR activity = 'Hide Listing' " +
                                "OR activity = 'Delete Listing' OR activity = 'Add Listing' OR " +
                                "activity = 'Delete Listing (Moderator)' OR activity = 'Approve Listing (Moderator)' " +
                                "OR activity = 'disapprove Listing (Moderator)')";
                        or = true;
                    }
                    psquery2 += ") ORDER BY id DESC";
                    psquery2 += ";";
                    ps = conn.prepareStatement(psquery2);
                    if (user) {
                        ps.setString(1, username);
                    }
                    if(!user && startDate != null){
                        ps.setString(1, startDate);
                    }
                    else if(user && startDate != null) {
                        ps.setString(2, startDate);
                    }
                    if (!user && startDate == null && endDate != null){
                        ps.setString(1,endDate);
                    } else if (((user && startDate == null) || (!user && startDate != null)) && endDate != null){
                        ps.setString(2,endDate);
                    } else if (user && startDate != null && endDate != null){
                        ps.setString(3,endDate);
                    }
                    //IF YOU SEE ERRORS IT IS OK! prepared statements are not smart enough
                    // to understand that everything is totally ok

                    rs = ps.executeQuery();
                    while (rs.next()) {
                        LogRec log = new LogRec(rs.getInt("id"),
                                rs.getString("date_time"),
                                rs.getString("username"),
                                rs.getString("activity"),
                                rs.getString("result"),
                                rs.getString("additional_info"));
                        toReturn.addLast(log);
                    }
                }
            }
            return toReturn;
        } catch (Exception ex) {
            System.out.println("Exception in getLogsByParameters " + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
        return null;
    }


    @org.jetbrains.annotations.NotNull
    private String generateHash(String input) {
        //taken from https://dzone.com/articles/storing-passwords-java-web for now
        StringBuilder hash = new StringBuilder();
        final String SALT = "bitlabnurent";
        input = SALT + input;
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f'};
            IntStream.range(0, hashedBytes.length).forEach(idx -> {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            });
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception in fenerateHash() " + e.getMessage());
        }
        return hash.toString();
    }

    @NotNull
    private String getTime() {
        return new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss").format(Calendar.getInstance().getTime());
    }
}