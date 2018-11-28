package DB;

        import java.sql.*;
        import java.util.Scanner;

public class Aplication {
    private static final String url = "jdbc:mysql://localhost:3306/Shop?serverTimezone=UTC&useSSL=false";
    private static final String user = "root";
    private static final String password = "legoro05";

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet rs = null;

    public static void main(String args[]) {
        try {
//region    0. This will load the MySQL driver, each DB has its own driver //
            Class.forName("com.mysql.cj.jdbc.Driver");
            //endregion


//region    1. Get a connection to database //
            connection = DriverManager.getConnection(url, user, password);
            //endregion
            if(!connection.isClosed()){
                System.out.println("DB підключено");
            }

//region  2. Create a statement
            // Statements allow to issue SQL queries to the database
            statement = connection.createStatement();
            boolean menu = true;
            while(menu) {
                System.out.println("Please select an option: ");
                System.out.println("0 - Print all entries");
                System.out.println("1 - Update a Shopper");
                System.out.println("2 - Add a Passwords");
                System.out.println("3 - Delete a Passwords");
                System.out.println("4 - Exit");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();

                switch (choice) {
                    case 0:
                        readData();
                        break;
                    case 1:
                        updateData();
                        break;
                    case 2:
                        insertData();
                        break;
                    case 3:
                        DeleteData();
                        break;
                    case 4:
                    default:
                        menu = false;
                        break;
                }
            }


        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver is not loaded");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        } finally {
            //close connection ,statement and resultset
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
            } // ignore
            if (statement != null) try {
                statement.close();
            } catch (SQLException e) {
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
            }
        }
    }


    private static void readData() throws SQLException {

        rs=statement.executeQuery("SELECT * FROM Shopper");

        // 4. Process the result set
        System.out.format("\nTable Shopper --------------------\n");
        System.out.format("%3s %-12s %-12s %-10s \n", "id", "name", "surname", "сard_number");
        while (rs.next())
        {
            int id=rs.getInt("id_shopper");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String сard_number=rs.getString("сard_number");
            // Simply Print the results
            System.out.format("%3d %-12s %-12s %-12s \n", id, name,surname, сard_number);
        }

        rs=statement.executeQuery("SELECT * FROM Goods");

        // 4. Process the result set
        System.out.format("\nTable Goods --------------------\n");
        System.out.format("%3s %-12s %-12s  \n", "id", "name", "price");
        while (rs.next())
        {
            int id=rs.getInt("id_goods");
            String name = rs.getString("name");
            String price = rs.getString("price");
            // Simply Print the results
            System.out.format("%3d %-12s %-12s  \n", id, name,price);
        }

        rs=statement.executeQuery("SELECT * FROM Passwords");

        // 4. Process the result set
        System.out.format("\nTable Passwords --------------------\n");
        System.out.format("%3s %-12s   \n", "id", "password");
        while (rs.next())
        {
            int id=rs.getInt("id_password");
            int password = rs.getInt("password");

            // Simply Print the results
            System.out.format("%3d %-12s   \n", id, password);
        }

        rs=statement.executeQuery("SELECT * FROM Shopper_Goods");

        // 4. Process the result set
        System.out.format("\nTable Shopper_Goods --------------------\n");
        System.out.format("%3s %-12s   \n", "id_s", "id_g");
        while (rs.next())
        {
            int id_s=rs.getInt("id_s");
            int id_g = rs.getInt("id_g");

            // Simply Print the results
            System.out.format("%3d %-12s   \n", id_s, id_g);
        }
    }

    private static void updateData() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Input name user what you want to update: ");
        String name = input.next();
        System.out.println("Input new name user for %s: "+ name);
        String namenew = input.next();
        statement.execute("UPDATE Shopper SET name='"+namenew+"' WHERE name='"+name+"';");


    }

    private static void insertData() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Input a new id: ");
        String id = input.next();
        System.out.println("Input a new password: ");
        String password = input.next();
        statement.execute("INSERT Passwords VALUES ("+id+","+password+")");
    }

    private static void DeleteData() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Input a id Passwords for delete: ");
        String id = input.next();
        statement.execute("DELETE FROM Passwords WHERE id="+id+"");
    }
}