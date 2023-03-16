
import java.sql.*;
import java.util.Random;  

public class App {
    public static void main(String[] args) throws Exception {
        int n = 10;
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/AQMS","root","hiabc(69)");  
            //here sonoo is database name, root is username and password  
            while(n > 0 ) {
                Statement stmt = con.createStatement();
                //Query to get the number of rows in a table
                String query = "select count(*) from air_quality_check";
                ResultSet rs = stmt.executeQuery(query);
                rs.next();
                int count = rs.getInt(1);
                int id = count + 1;
                String insert_command = "insert into air_quality_check (id, floor, o2_level,co2_level,so2_level, co_level , c_level , air_quality_level)" + "values (?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStmt = con.prepareStatement(insert_command);
                preparedStmt.setInt(1,id);
                preparedStmt.setInt(2,getRandomNumber(1, 4));
                preparedStmt.setInt(3,getRandomNumber(100, 1000));
                preparedStmt.setInt(4,getRandomNumber(75, 200));
                preparedStmt.setInt(5,getRandomNumber(75, 200));
                preparedStmt.setInt(6,getRandomNumber(75, 200));
                preparedStmt.setInt(7,getRandomNumber(75, 200));
                preparedStmt.setString(8, "Need to check the air quality");
                
                preparedStmt.execute(); 
                n -= 1;
            }  
            con.close();  
        } catch(Exception e){ System.out.println(e);}
    }

    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}

