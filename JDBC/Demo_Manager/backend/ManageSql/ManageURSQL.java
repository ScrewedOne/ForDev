package ManageSql;

import java.sql.*;
import java.util.Scanner;

public class ManageURSQL {
    static Connection connect;
    static PreparedStatement statement;
    static ResultSet set;
    private static String url;
    private static String username;
    private static String password;
    static Scanner sc = new Scanner(System.in);

    private static void myData() // Encapsulation for data security
    {
        url = "jdbc:postgresql://localhost:5432/postgres";
        username = "postgres";
        password = "9674";
    }

    private static void getConnected() {
        try {
            myData();
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established :D !!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed To Connect :( ");
        }
    }

    private static void ReadyQuery(String Query) {
        try {
            getConnected();
            statement = connect.prepareStatement(Query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String GetQuery() {
        System.out.println("Enter Query : ");
        return sc.nextLine();
    }

    public static void FetchAndRead() {
        try {
            String Query = "select * from movie";
            ReadyQuery(Query);
            set = statement.executeQuery();
            System.out.println("---------------------------------");
            System.out.println("|  movie_id    |   movie_name    |");
            System.out.println("---------------------------------");
            System.out.println("---------------------------------");
            while (set.next()) {
                int movie_id = set.getInt("movie_id");
                String movie_name = set.getString("movie_name");
                System.out.println(STR."|  \{movie_id}         |   \{movie_name}    ");
            }
            System.out.println("---------------------------------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void DeleteData() {

        try {
            String Query = "delete from movie where movie_id = ? ";
            ReadyQuery(Query);
            while (true) {
                System.out.println("Enter Movie Id : ");
                int movie_id = sc.nextInt();
                statement.setInt(1, movie_id);
                statement.addBatch();
                System.out.println("DO YOU WANT TO DELETE MORE DATA ? Y/N ");
                String val = sc.next().toUpperCase().trim();
                if (val.equals("N"))
                    break;;
            }
            int  []arr = statement.executeBatch();
            int ZeroCounter = 0;
            for (int i = 0 ; i < arr.length ; i++)
            {
                if (arr[i]==0) {
                    ZeroCounter++;
                    System.out.printf("%d Query Was Not Successfully Loaded :( ( Failed To Delete ) ", i + 1);
                }
            }
            if (ZeroCounter == 0) System.out.println("Batch Was Successfully Loaded ( Data Deleted ) ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void InsertData() {

        try {
            String Query = "insert into movie (movie_id,movie_name) values ( ? , ? )";
            ReadyQuery(Query);
            while (true)
            {
                System.out.print("Enter movie_id : ");
                int movie_id = sc.nextInt();
                System.out.print("Enter movie_name :");
                String movie_name = sc.next();
                statement.setInt(1,movie_id);
                statement.setString(2,movie_name);
                statement.addBatch();
                System.out.println("DO YOU WANT TO ADD MORE DATA ? Y/N ");
                String val = sc.next().toUpperCase().trim();
                if (val.equals("N"))
                    break;;
            }
            int  []arr = statement.executeBatch();
            int ZeroCounter = 0;
           for (int i = 0 ; i < arr.length ; i++)
           {
               if (arr[i]==0) {
                   ZeroCounter++;
                   System.out.printf("%d Query Was Not Successfully Loaded :( ", i + 1);
               }
           }
           if (ZeroCounter == 0) System.out.println("Batch Was Successfully Loaded : ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void ThroughQuery() {
        try {
            ReadyQuery(GetQuery());
            int RowsInfluenced = statement.executeUpdate();
            if (RowsInfluenced > 0) System.out.println("Query Done Successfully !!");
            else System.out.println("Failed Query");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
