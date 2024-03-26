package ManageSql;
public class ControlSql extends ManageURSQL {
    public void PerformSql() throws Exception {
        while (true) {
            System.out.println(" Enter ADD to add into database \n Enter DELETE to delete elements from database \n Enter SHOW to print the entire database \n Enter QUERY to run your own query in database ");
            System.out.print("Enter your command : ");
            String func = sc.next().toUpperCase();
            switch (func) {
                case "ADD": {
                    InsertData();
                    break;
                }
                case "DELETE": {
                    DeleteData();
                    break;
                }
                case "SHOW": {
                    FetchAndRead();
                    break;
                }
                case "QUERY": {
                    ThroughQuery();
                    break;
                }
                default:
                    throw new Exception("Invalid command !!");
            }
            System.out.println("DO YOU WANT TO PERFORM MORE OPERATIONS ? Y?N ");
            String res = sc.next().toUpperCase();
            if (res.equals("N")) break;
        }
    }
}