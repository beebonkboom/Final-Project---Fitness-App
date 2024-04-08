import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;
public class main {
    private static final String url = "jdbc:postgresql://localhost:5432/Fitness_App";
    private static final String user = "";
    private static final String password = "";
    public static int user_id = -1;

    public static void main(String[] args) {

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null){
                System.out.println("Connected to the database");
            }else {
                System.out.println("Failed to connect to the database");
            }

            int c = -1;
            while(c != 0){
                c = mainMenu();
                Scanner sc = new Scanner(System.in);
                if(c == 1){
                    while (c != 0 && c != -1){
                        boolean ret = false;
                        c = memMenu();

                        if (c == 1){
                            ret = signInMember();
                        } else if (c == 2){
                            ret = registerMember();
                        }

                        if (ret){
                            while (c != 0 && c != -1 && user_id != -1) {
                                c = memOpMenu();
                                if (c == 1){
                                    viewMember();
                                } else if (c == 2){
                                    deleteMember();
                                } else if (c == 3){
                                    updateHealthMet();
                                } else if (c == 4){
                                    payMemberBill();
                                } else if (c == 5){
                                    createExercise();
                                }else if (c == 6){
                                    deleteExercise();
                                }else if (c == 7){
                                    viewExercise();
                                }else if (c == 8){
                                    createFitnessGoal();
                                }else if (c == 9){
                                    achieveFitnessGoal();
                                }else if (c == 10){
                                    viewFitnessGoals();
                                }else if (c == 11){
                                    enrollSession();
                                } else if (c == 12){
                                    unenrollSession();
                                }
                            }
                        }

                    }

                } else if (c == 2){
                    while (c != 0 && c != -1){
                        c = adminMenu();
                        boolean log = false;
                        log = signInAdmin();

                        if(log){
                            while(c != 0 && c != -1 && user_id != -1){
                                c = adminOpMenu();
                                if(c == 1){
                                    adminManageProfile();
                                }else if(c == 2){
                                    adminManageSessions();
                                }else if(c == 3){
                                    adminOverseeBilling();
                                }else if(c == 4){
                                    adminProcessPayments();
                                }else if(c == 5){
                                    adminManageMaintenance();
                                }
                            }
                        }
                    }

                } else if (c == 3){
                    while (c != 0 && c != -1){
                        c = trainerMenu();
                        boolean log = false;
                        log = signInTrainer();
                        if(log){
                            while(c != 0 && c != -1 && user_id != -1){
                                c = trainerOpMenu();
                                if(c == 1){
                                    trainerManageProfile();
                                }else if(c == 2){
                                    trainerManageAvailability();
                                }else if(c == 3){
                                    overseeMembers();
                                }
                            }
                        }
                    }

                }
            }
        }
        catch(Exception e){System.out.println("FAILED"); System.out.println(e);}
    }

    public static int mainMenu(){
        // user menu information
        System.out.println("\n\n|----------------------MAIN MENU----------------------");
        System.out.println("0.) Exit Program");
        System.out.println("1.) Member Menu");
        System.out.println("2.) Admin Menu");
        System.out.println("3.) Trainer Menu");
        System.out.println("\n\nEnter Choice: ");
        Scanner scann = new Scanner(System.in);
        int choice = scann.nextInt();

        // clear scanner
        scann.nextLine();

        // send back user's choice
        return choice;
    }


    public static int memMenu(){
        // user menu information
        System.out.println("\n\n|----------------------MEMBER MAIN MENU----------------------");
        System.out.println("-1.) Back to Main Menu");
        System.out.println("0.) Exit Program");
        System.out.println("1.) Sign in as Member");
        System.out.println("2.) Register as a member");
        System.out.println("\n\nEnter Choice: ");
        Scanner scann = new Scanner(System.in);
        int choice = scann.nextInt();

        // clear scanner
        scann.nextLine();

        // send back user's choice
        return choice;
    }

    public static int memOpMenu(){
        // user menu information
        System.out.println("\n\n|----------------------MEMBER DASHBOARD----------------------");
        System.out.println("-1.) Back to Main Menu");
        System.out.println("0.) Exit Program");// remember to display needed info for the user to input!!!
        System.out.println("\n|----------------------Manage Profile----------------------");
        System.out.println("1.) View Profile");
        System.out.println("2.) Delete Profile");
        System.out.println("3.) Update Health metrics");
        System.out.println("4.) Paying Bill");
        System.out.println("\n\n|----------------------Manage Fitness----------------------");
        System.out.println("5.) Create Exercise Routine");
        System.out.println("6.) Delete Exercise Routine");
        System.out.println("7.) View Exercise Routines");
        System.out.println("8.) Create a fitness goal");
        System.out.println("9.) Complete a fitness goal");
        System.out.println("10.) View Fitness Achievements");
        System.out.println("\n\n|----------------------Manage Scheduling----------------------");
        System.out.println("11.) Enroll in a session");
        System.out.println("12.) Un-enroll in a session");
        System.out.println("\n\nEnter Choice: ");
        Scanner scann = new Scanner(System.in);
        int choice = scann.nextInt();

        // clear scanner
        scann.nextLine();

        // send back user's choice
        return choice;
    }


    public static int adminMenu(){
        // user menu information
        System.out.println("\n\n|----------------------ADMIN MAIN MENU----------------------");
        System.out.println("-1.) Back to Main Menu");
        System.out.println("0.) Exit Program");
        System.out.println("1.) Sign in as Admin");
        System.out.println("\n\nEnter Choice: ");
        Scanner scann = new Scanner(System.in);
        int choice = scann.nextInt();

        // clear scanner
        scann.nextLine();

        // send back user's choice
        return choice;
    }


    public static int adminOpMenu(){
        // user menu information
        System.out.println("\n\n|----------------------ADMIN OPTIONS MENU----------------------");
        System.out.println("-1.) Back to Main Menu");
        System.out.println("0.) Exit Program");
        System.out.println("\n|----------------------Manage Profile----------------------");
        System.out.println("1.) Manage Profile"); // allow to update/delete
        System.out.println("\n|----------------------Manage Sessions----------------------");
        System.out.println("2.) Manage room booking and class schedule"); // remember to display needed info for the user to input!!!
        System.out.println("\n|----------------------Manage Member Profiles----------------------");
        System.out.println("3.) Oversee billings"); // display all members + their bills
        System.out.println("4.) Help Pay Bill");
        System.out.println("\n|----------------------Manage Equipment----------------------");
        System.out.println("5.) Manage maintenance appointments");
        System.out.println("\n\nEnter Choice: ");
        Scanner scann = new Scanner(System.in);
        int choice = scann.nextInt();

        // clear scanner
        scann.nextLine();

        // send back user's choice
        return choice;
    }

    public static int trainerMenu(){
        // user menu information
        System.out.println("\n\n|----------------------TRAINER MAIN MENU----------------------");
        System.out.println("-1.) Back to Main Menu");
        System.out.println("0.) Exit Program");
        System.out.println("1.) Sign in as Trainer");
        System.out.println("\n\nEnter Choice: ");
        Scanner scann = new Scanner(System.in);
        int choice = scann.nextInt();

        // clear scanner
        scann.nextLine();

        // send back user's choice
        return choice;
    }


    public static int trainerOpMenu(){
        System.out.println("\n\n|----------------------TRAINER OPTIONS MENU----------------------");
        System.out.println("-1.) Back to Main Menu");
        System.out.println("0.) Exit Program");
        System.out.println("\n|----------------------Manage Profile----------------------");
        System.out.println("1.) Manage Profile");
        System.out.println("2.) Manage Availability");// allow to update availability
        System.out.println("\n|----------------------Manage Members----------------------");
        System.out.println("3.) Oversee Members");
        System.out.println("\n\nEnter Choice: ");
        Scanner scann = new Scanner(System.in);
        int choice = scann.nextInt();

        // clear scanner
        scann.nextLine();

        // send back user's choice
        return choice;
    }


    public static boolean signInMember(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null){
                System.out.println("Connected to the database");
            }else {
                System.out.println("Failed to connect to the database");
            }

            Scanner sc = new Scanner(System.in);

            System.out.print("\nEnter username: ");
            String us = sc.nextLine();
            System.out.print("\nEnter password: ");
            String pass = sc.nextLine();


            String insertSQL =  "select mem_id from fit_member where username = ? AND mem_password = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)){
                pstmt.setString(1, us);
                pstmt.setString(2, pass);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        user_id = rs.getInt("mem_id");
                        System.out.println("\nMember signed in!");
                        return true;
                    } else {
                        System.out.println("\nMember NOT signed in!");
                        return false;
                    }
                }

            }

        }
        catch(Exception e){System.out.println("FAILED"); System.out.println(e); return false;}

    }



    public static boolean registerMember (){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null){
                System.out.println("Connected to the database");
            }else {
                System.out.println("Failed to connect to the database");
            }

            Scanner sc = new Scanner(System.in);

            //register as member
            System.out.print("Enter First Name: ");
            String fn = sc.nextLine();
            System.out.print("\nEnter Last Name: ");
            String ls = sc.nextLine();
            System.out.print("\nEnter Email: ");
            String em = sc.nextLine();
            System.out.print("\nEnter Enrollments Date's Year: ");
            int yr = sc.nextInt();
            System.out.print("\nEnter Enrollments Date's Month: ");
            int mn = sc.nextInt();
            System.out.print("\nEnter Enrollments Date's Day: ");
            int dy = sc.nextInt();
            sc.nextLine();
            System.out.print("\nEnter username: ");
            String us = sc.nextLine();
            System.out.print("\nEnter password: ");
            String pass = sc.nextLine();
            System.out.print("\nEnter age: ");
            int age = sc.nextInt();

            // clear scanner - avoids issues
            sc.nextLine();

            Date d = new Date ((yr-1900), (mn-1), (dy));


            String insertSQL = "insert into fit_member (first_name, last_name, email, signedup_date, username, mem_password, age) values (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)){
                // set variables by their type
                pstmt.setString(1, fn);
                pstmt.setString(2, ls);
                pstmt.setString(3, em);
                pstmt.setDate(4, d);
                pstmt.setString(5, us);
                pstmt.setString(6, pass);
                pstmt.setInt(7, age);

                // confirm if successful
                int rowsinserted = pstmt.executeUpdate();
                if(rowsinserted == 1){
                    System.out.println("\nSuccessfully registered!");
                    String newsql =  "select mem_id from fit_member where username = ? AND mem_password = ?";
                    try (PreparedStatement pstmtt = connection.prepareStatement(newsql)){
                        pstmtt.setString(1, us);
                        pstmtt.setString(2, pass);
                        try (ResultSet rs = pstmtt.executeQuery()) {
                            if (rs.next()) {
                                user_id = rs.getInt("mem_id");
                            }
                        }

                    }

                    boolean ret = false;
                    while(!ret){
                        ret = createHealthMet();
                    }


                    return true;
                } else {
                    System.out.println("\nUnsuccessfully registered!");
                    return false;
                }

            }



        }
        catch(Exception e){System.out.println("FAILED"); System.out.println(e); return false;}

    }

    public static boolean createHealthMet(){

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null){
                System.out.println("Connected to the database");
            }else {
                System.out.println("Failed to connect to the database");
            }

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter weight: ");
            float w =  sc.nextFloat();
            System.out.print("Enter height: ");
            float h =  sc.nextFloat();
            System.out.print("Enter bmi: ");
            float b =  sc.nextFloat();
            System.out.print("Enter body fat percentage: ");
            float p =  sc.nextFloat();
            sc.nextLine();


            String insertSQL = "insert into health_metrics (mem_id, weight, height, bmi, body_fat_perc) values (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)){
                pstmt.setInt(1, user_id);
                pstmt.setFloat(2, w);
                pstmt.setFloat(3, h);
                pstmt.setFloat(4, b);
                pstmt.setFloat(5, p);

                int rowsinserted = pstmt.executeUpdate();
                if(rowsinserted > 0){
                    System.out.println("\nSuccessfully made health metrics!");
                    return true;
                } else {
                    System.out.println("\nUnsuccessfully made health metrics!");
                    return false;
                }

            }



        }
        catch(Exception e){System.out.println("FAILED"); System.out.println(e); return false;}
    }
    public static void viewMember(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null){
                System.out.println("Connected to the database");
            }else {
                System.out.println("Failed to connect to the database");
            }


            String insertSQL =  "select * from fit_member where mem_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)){
                pstmt.setInt(1, user_id);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        // Print member information
                        System.out.println("Member ID: " + rs.getInt("mem_id"));
                        System.out.println("First Name: " + rs.getString("first_name"));
                        System.out.println("Last Name: " + rs.getString("last_name"));
                        System.out.println("Email: " + rs.getString("email"));
                        System.out.println("Username: " + rs.getString("username"));
                        System.out.println("Age: " + rs.getInt("age"));
                        System.out.println("Bill: " + rs.getDouble("bill"));
                        viewMemberHealth();

                    } else {
                        System.out.println("\nERROR!");
                    }
                }

            }

        }
        catch(Exception e){System.out.println("FAILED"); System.out.println(e);}
    }

    public static void viewMemberHealth(){


        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null){
                System.out.println("Connected to the database");
            }else {
                System.out.println("Failed to connect to the database");
            }

            String newSql =  "select * from health_metrics where mem_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(newSql)){
                pstmt.setInt(1, user_id);
                try (ResultSet rss = pstmt.executeQuery()) {
                    if (rss.next()) {
                        System.out.println("Health Metric id: " + rss.getInt("health_met_id"));
                        System.out.println("Weight: " + rss.getFloat("weight"));
                        System.out.println("Height: " + rss.getFloat("height"));
                        System.out.println("Bmi: " + rss.getFloat("bmi"));
                        System.out.println("Body Fat %: " + rss.getFloat("body_fat_perc"));

                    } else {
                        System.out.println("\nERROR!");
                    }
                }

            }

        }
        catch(Exception e){System.out.println("FAILED"); System.out.println(e);}
    }



    public static void deleteMember(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection != null){
                System.out.println("Connected to the database");
            }else {
                System.out.println("Failed to connect to the database");
            }
            PreparedStatement ps = connection.prepareStatement("SELECT bill FROM fit_member WHERE mem_id = ?");
            ps.setInt(1, user_id);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            rs.next();
            double bill = rs.getDouble("bill");
            if(bill > 0){
                System.out.println("Account deletion is currently unavailable as you still have balance on your account.");
                System.out.println("Please pay your remaining bill.");
            }else {
                String insertSQL = "delete from fit_member where mem_id = ?";
                try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
                    pstmt.setInt(1, user_id);
                    int rowsinserted = pstmt.executeUpdate();
                    if (rowsinserted > 0) {
                        System.out.println("\nSuccessfully deleted member!");
                        user_id = -1;
                    } else {
                        System.out.println("\nUnsuccessfully deleted member!");
                    }
                }
            }

        }
        catch(Exception e){System.out.println("FAILED"); System.out.println(e);}

    }



    public static void updateHealthMet(){


        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null){
                System.out.println("Connected to the database");
            }else {
                System.out.println("Failed to connect to the database");
            }
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter weight: ");
            float w =  sc.nextFloat();
            System.out.print("Enter height: ");
            float h =  sc.nextFloat();
            System.out.print("Enter bmi: ");
            float b =  sc.nextFloat();
            System.out.print("Enter body fat percentage: ");
            float p =  sc.nextFloat();
            sc.nextLine();


            String insertSQL = "update health_metrics set weight = ?, height = ?, bmi = ?, body_fat_perc = ? where mem_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)){

                pstmt.setFloat(1, w);
                pstmt.setFloat(2, h);
                pstmt.setFloat(3, b);
                pstmt.setFloat(4, p);
                pstmt.setInt(5, user_id);

                int rowsinserted = pstmt.executeUpdate();
                if(rowsinserted > 0){
                    System.out.println("\nSuccessfully updated!");
                } else {
                    System.out.println("\nUnsuccessfully updated!");
                }

            }

        }
        catch(Exception e){System.out.println("FAILED"); System.out.println(e);}
    }



    public static void payMemberBill(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null){
                System.out.println("Connected to the database");
            }else {
                System.out.println("Failed to connect to the database");
            }
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter payment: ");
            int p =  sc.nextInt();


            String insertSQL = "update fit_member set bill = ? where mem_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)){

                pstmt.setDouble(1, p);
                pstmt.setInt(2, user_id);

                int rowsinserted = pstmt.executeUpdate();
                if(rowsinserted > 0){
                    System.out.println("\nSuccessfully Pay!");
                } else {
                    System.out.println("\nUnsuccessfully Pay!");
                }

            }

        }
        catch(Exception e){System.out.println("FAILED"); System.out.println(e);}
    }



    public static void createExercise(){
        int hstatid = createHealthStat();

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null){
                System.out.println("Connected to the database");
            }else {
                System.out.println("Failed to connect to the database");
            }

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Exercise description: ");
            String e =  sc.nextLine();
            System.out.print("Enter Exercise duration: ");
            int d =  sc.nextInt();
            System.out.print("Enter Exercise target: ");
            String t =  sc.nextLine();


            String insertSQL = "insert into exercise_routine (mem_id, health_stat_id, exercise, duration, target) values (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)){
                pstmt.setInt(1, user_id);
                pstmt.setInt(2, hstatid);
                pstmt.setString(3, e);
                pstmt.setInt(4, d);
                pstmt.setString(5, t);

                int rowsinserted = pstmt.executeUpdate();
                if(rowsinserted > 0){
                    System.out.println("\nSuccessfully made exercise routine!");
                } else {
                    System.out.println("\nUnsuccessfully made exercise routine!");
                }

            }



        }
        catch(Exception e){System.out.println("FAILED"); System.out.println(e);}

    }

    public static int createHealthStat(){
        int hstat_id = -1;

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null){
                System.out.println("Connected to the database");
            }else {
                System.out.println("Failed to connect to the database");
            }

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter steps: ");
            int s =  sc.nextInt();
            System.out.print("Enter oxygen intake: ");
            float o =  sc.nextFloat();
            System.out.print("Enter heart rate: ");
            int h =  sc.nextInt();
            System.out.print("Enter blood pressure: ");
            int b =  sc.nextInt();
            sc.nextLine();


            String insertSQL = "insert into health_stat (steps, oxygen_intake, heart_rate, blood_pressure) values (?, ?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)){
                pstmt.setInt(1, s);
                pstmt.setFloat(2, o);
                pstmt.setInt(3, h);
                pstmt.setInt(4, b);


                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected == 1) {
                    try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            hstat_id = generatedKeys.getInt(1);
                        }
                    }
                    System.out.println("\nSuccessfully made health stats!");
                    return hstat_id;
                } else {
                    System.out.println("\nUnsuccessfully made health stats!");
                }

            }



        }
        catch(Exception e){System.out.println("FAILED"); System.out.println(e);}

        return hstat_id;
    }

    public static void viewExercise(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null){
                System.out.println("Connected to the database");
            }else {
                System.out.println("Failed to connect to the database");
            }

            String newSql =  "select * from exercise_routine where mem_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(newSql)){
                pstmt.setInt(1, user_id);
                try (ResultSet rss = pstmt.executeQuery()) {
                    while (rss.next()) {
                        System.out.println("Exercise: " + rss.getString("exercise"));
                        System.out.println("Duration: " + rss.getInt("duration"));
                        System.out.println("Target: " + rss.getString("target"));

                        int hid = rss.getInt("health_stat_id");
                        viewHealthStat(hid);

                    }
                }

            }

        }
        catch(Exception e){System.out.println("FAILED"); System.out.println(e);}
    }

    public static void viewHealthStat(int hid){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null){
                System.out.println("Connected to the database");
            }else {
                System.out.println("Failed to connect to the database");
            }

            String newSql =  "select * from health_stat where health_stat_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(newSql)){
                pstmt.setInt(1, hid);
                try (ResultSet rss = pstmt.executeQuery()) {
                    if (rss.next()) {
                        System.out.println("Steps: " + rss.getInt("steps"));
                        System.out.println("Oxygen Intake: " + rss.getFloat("oxygen_intake"));
                        System.out.println("Heart Rate: " + rss.getInt("heart_rate"));
                        System.out.println("Blood Pressure: " + rss.getInt("blood_pressure"));
                    } else {
                        System.out.println("\nERROR!");
                    }
                }

            }

        }
        catch(Exception e){System.out.println("FAILED"); System.out.println(e);}

    }



    public static void deleteExercise(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null){
                System.out.println("Connected to the database");
            }else {
                System.out.println("Failed to connect to the database");
            }


            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Exercise ID: ");
            int eid =  sc.nextInt();

            String insertSQL = "delete from exercise where exer_rout_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)){
                pstmt.setInt(1, eid);
                int rowsinserted = pstmt.executeUpdate();
                if(rowsinserted > 0){
                    System.out.println("\nSuccessfully deleted exercise!");
                } else {
                    System.out.println("\nUnsuccessfully deleted exercise!");
                }
            }

        }
        catch(Exception e){System.out.println("FAILED"); System.out.println(e);}

    }



    public static void createFitnessGoal(){

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null){
                System.out.println("Connected to the database");
            }else {
                System.out.println("Failed to connect to the database");
            }

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Goal description: ");
            String g =  sc.nextLine();

            System.out.print("\nEnter start Date's Year: ");
            int syr = sc.nextInt();
            System.out.print("\nEnter start Date's Month: ");
            int smn = sc.nextInt();
            System.out.print("\nEnter start Date's Day: ");
            int sdy = sc.nextInt();

            System.out.print("\nEnter end Date's Year: ");
            int eyr = sc.nextInt();
            System.out.print("\nEnter end Date's Month: ");
            int emn = sc.nextInt();
            System.out.print("\nEnter end Date's Day: ");
            int edy = sc.nextInt();

            sc.nextLine();

            Date sd = new Date ((syr-1900), (smn-1), (sdy));
            Date ed = new Date ((eyr-1900), (emn-1), (edy));


            String insertSQL = "insert into fitness_goal (mem_id, goal, st_date, end_date) values (?, ?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)){
                pstmt.setInt(1, user_id);
                pstmt.setString(2, g);
                pstmt.setDate(3, sd);
                pstmt.setDate(4, ed);

                int rowsinserted = pstmt.executeUpdate();
                if(rowsinserted > 0){
                    System.out.println("\nSuccessfully made fitness goal!");
                } else {
                    System.out.println("\nUnsuccessfully made fitness goal!");
                }

            }



        }
        catch(Exception e){System.out.println("FAILED"); System.out.println(e);}

    }




    public static void viewFitnessGoals(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null){
                System.out.println("Connected to the database");
            }else {
                System.out.println("Failed to connect to the database");
            }

            String newSql =  "select * from fitness_goal where mem_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(newSql)){
                pstmt.setInt(1, user_id);
                try (ResultSet rss = pstmt.executeQuery()) {
                    while (rss.next()) {
                        System.out.println("Goal: " + rss.getString("goal"));
                        System.out.println("Duration: " + rss.getInt("duration"));
                        System.out.println("Start Date: " + rss.getDate("st_date"));
                        System.out.println("End Date: " + rss.getDate("end_date"));

                    }
                }

            }

        }
        catch(Exception e){System.out.println("FAILED"); System.out.println(e);}
    }




    public static void achieveFitnessGoal(){

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null){
                System.out.println("Connected to the database");
            }else {
                System.out.println("Failed to connect to the database");
            }

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Fitness Goal Id: ");
            int gid =  sc.nextInt();
            System.out.print("\nEnter achievement Date's Year: ");
            int yr = sc.nextInt();
            System.out.print("\nEnter achievement Date's Month: ");
            int mn = sc.nextInt();
            System.out.print("\nEnter achievement Date's Day: ");
            int dy = sc.nextInt();

            Date ad = new Date ((yr-1900), (mn-1), (dy));


            String insertSQL = "insert into fitness_ach (mem_id, fit_goal_id, complete, comp_date) values (?, ?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)){
                pstmt.setInt(1, user_id);
                pstmt.setInt(2, gid);
                pstmt.setBoolean(3, true);
                pstmt.setDate(4, ad);

                int rowsinserted = pstmt.executeUpdate();
                if(rowsinserted > 0){
                    System.out.println("\nSuccessfully achieved goal!");
                } else {
                    System.out.println("\nUnsuccessfully achieved goal!");
                }

            }
        }
        catch(Exception e){System.out.println("FAILED"); System.out.println(e);}

    }

    public static void enrollSession (){
        Scanner sc = new Scanner(System.in);
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            viewAvailSessions();
            System.out.println("Enter session ID: ");
            int sesh = sc.nextInt();
            PreparedStatement ps = connection.prepareStatement("SELECT room_book_id, trainer_id, avail_id, price, curr_size FROM room_booking WHERE room_book_id = ?");
            ps.setInt(1, sesh);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            rs.next();
            int trainerId = rs.getInt("trainer_id");
            int availId = rs.getInt("avail_id");
            double price = rs.getDouble("price");
            int size = rs.getInt("curr_size");
            ps = connection.prepareStatement("SELECT target FROM availability WHERE avail_id = ?");
            ps.setInt(1, availId);
            ps.executeQuery();
            rs = ps.getResultSet();
            rs.next();
            String target = rs.getString("target");
            ps = connection.prepareStatement("INSERT INTO fit_session (mem_id, trainer_id, room_book_id, target) VALUES (?, ?, ?, ?)");
            ps.setInt(1, user_id);
            ps.setInt(2, trainerId);
            ps.setInt(3, sesh);
            ps.setString(4, target);
            ps.executeUpdate();
            ps = connection.prepareStatement("UPDATE room_booking SET curr_size = ? WHERE room_book_id = ?");
            ps.setInt(1, size+1);
            ps.setInt(2, sesh);
            ps.executeUpdate();
            ps = connection.prepareStatement("SELECT bill FROM fit_member WHERE mem_id = ?");
            ps.setInt(1, user_id);
            ps.executeQuery();
            rs = ps.getResultSet();
            rs.next();
            double total = rs.getDouble("bill") + price;
            ps = connection.prepareStatement("UPDATE fit_member SET bill = ? WHERE mem_id = ?");
            ps.setDouble(1, total);
            ps.setInt(2, user_id);
            ps.executeUpdate();
            System.out.println("Enrolled successfully");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void unenrollSession (){
        Scanner sc = new Scanner(System.in);
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            viewMySessions();
            System.out.println("Enter session ID: ");
            int sesh = sc.nextInt();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM fit_session WHERE sess_id = ?");
            ps.setInt(1, sesh);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            rs.next();
            int bookingId = rs.getInt("room_book_id");
            ps = connection.prepareStatement("SELECT curr_size FROM room_booking WHERE room_book_id = ?");
            ps.setInt(1, bookingId);
            ps.executeQuery();
            rs = ps.getResultSet();
            rs.next();
            int size = rs.getInt("curr_size");
            ps = connection.prepareStatement("DELETE FROM fit_session WHERE mem_id = ? AND room_book_id = ?");
            ps.setInt(1, user_id);
            ps.setInt(2, bookingId);
            ps.executeUpdate();
            ps = connection.prepareStatement("UPDATE room_booking SET curr_size = ? WHERE room_book_id = ?");
            ps.setInt(1, size-1);
            ps.setInt(2, bookingId);
            ps.executeUpdate();
            System.out.println("Un-enrolled successfully");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void viewAvailSessions (){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement st = connection.createStatement();
            st.executeQuery("SELECT room_book_id, trainer.first_name, trainer.last_name, room_name, room_booking.st_time, room_booking.end_time, booking_date, capacity, curr_size, price, target FROM room_booking, trainer, room, availability WHERE room_booking.trainer_id = trainer.trainer_id AND room_booking.room_num = room.room_id AND room_booking.avail_id = availability.avail_id");
            ResultSet rs = st.getResultSet();
            System.out.println("-------------Booked sessions-------------");
            System.out.println(String.format("%-4s%-15s%-20s%-15s%-15s%-20s%-15s%-15s%-15s", "ID", "Session Name", "Trainer Name", "Location", "Date", "Time", "Capacity", "Attending", "Price"));
            while(rs.next()){
                System.out.println(String.format("%-4s%-15s%-20s%-15s%-15s%-20s%-15s%-15s%-4.2f", rs.getInt("room_book_id") + ".)", rs.getString("target"), rs.getString("first_name") + " " + rs.getString("last_name"), rs.getString("room_name"), rs.getDate("booking_date"), rs.getString("st_time") + "-" + rs.getString("end_time"), rs.getInt("capacity"), rs.getInt("curr_size"), rs.getDouble("price")));
            }
            System.out.println("---------------------------------------");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void viewMySessions (){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = connection.prepareStatement("SELECT sess_id, target, price, room_name, st_time, end_time, booking_date, trainer.first_name, trainer.last_name FROM fit_session, room_booking, room, trainer WHERE fit_session.room_book_id = room_booking.room_book_id AND room_booking.room_num = room.room_id AND fit_session.trainer_id = trainer.trainer_id AND mem_id = ?");
            ps.setInt(1, user_id);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            System.out.println("-------------My sessions-------------");
            System.out.println(String.format("%-4s%-15s%-15s%-20s%-15s%-20s%-10s", "ID", "Session Name", "Location", "Time", "Date", "Trainer Name", "Price"));
            while(rs.next()){
                System.out.println(String.format("%-4s%-15s%-15s%-20s%-15s%-20s%-4.2f", rs.getInt("sess_id") + ".) ", rs.getString("target"), rs.getString("room_name"), rs.getString("st_time") + "-" + rs.getString("end_time"), rs.getString("booking_date"), rs.getString("first_name") + " " + rs.getString("last_name"), rs.getDouble("price")));
            }
            System.out.println("---------------------------------------");
        }
        catch(Exception e){
            System.out.println(e);
        }

    }
    public static boolean signInTrainer(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Scanner sc = new Scanner(System.in);

            System.out.print("\nEnter username: ");
            String us = sc.nextLine();
            System.out.print("\nEnter password: ");
            String pass = sc.nextLine();

            PreparedStatement ps = connection.prepareStatement("SELECT trainer_id FROM trainer WHERE username = ? AND trainer_password = ?");
            ps.setString(1, us);
            ps.setString(2, pass);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            if(rs.next()){
                user_id = rs.getInt("trainer_id");
                System.out.println("\nTrainer signed in!");
                return true;
            }else{
                System.out.println("\nTrainer NOT signed in!");
                return false;
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    public static void trainerManageProfile(){
        Scanner sc = new Scanner(System.in);
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password); //connect to the database
            PreparedStatement st = connection.prepareStatement("SELECT * FROM trainer WHERE trainer_id = ?");
            st.setInt(1, user_id);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            rs.next();
            System.out.println("-------------" + rs.getString("username") + "'s profile-------------");
            System.out.println("First Name: " + rs.getString("first_name"));
            System.out.println("Last Name: " + rs.getString("last_name"));
            System.out.println("Email: " + rs.getString("email"));
            System.out.println("Username: " + rs.getString("username"));
            System.out.println("Password: " + rs.getString("trainer_password"));
            System.out.println("---------------------------------------");
            System.out.println("1.) Update email");
            System.out.println("2.) Update username");
            System.out.println("3.) Update password");
            System.out.println("Enter Choice: ");
            int c = sc.nextInt();
            if(c == 1){
                System.out.println("Enter New Email: ");
                String newEmail = sc.next();
                st = connection.prepareStatement("UPDATE trainer SET email = ? WHERE trainer_id = ?");
                st.setString(1, newEmail);
                st.setInt(2, user_id);
                st.executeUpdate();
                System.out.println("Email successfully updated");
            }else if(c == 2){
                System.out.println("Enter New Username: ");
                String newUsername = sc.next();
                st = connection.prepareStatement("UPDATE trainer SET username = ? WHERE trainer_id = ?");
                st.setString(1, newUsername);
                st.setInt(2, user_id);
                st.executeUpdate();
                System.out.println("Username successfully updated");
            }else if(c == 3) {
                System.out.println("Enter New Password: ");
                String newPassword = sc.next();
                st = connection.prepareStatement("UPDATE trainer SET trainer_password = ? WHERE trainer_id = ?");
                st.setString(1, newPassword);
                st.setInt(2, user_id);
                st.executeUpdate();
                System.out.println("Password successfully updated");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void trainerManageAvailability(){
        Scanner sc = new Scanner(System.in);
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement st = connection.createStatement();
            st.executeQuery("SELECT avail_id, trainer.first_name, trainer.last_name, avail_date, target, st_time, end_time FROM availability FULL JOIN trainer ON availability.trainer_id = trainer.trainer_id");
            ResultSet rs = st.getResultSet();
            System.out.println("-------------Trainer Availability-------------");
            System.out.println(String.format("%-4s%-20s%-15s%-15s%-20s", "ID", "Trainer Name", "Date", "Session Name", "Time"));
            while(rs.next()){
                System.out.println(String.format("%-4s%-20s%-15s%-15s%-20s", rs.getInt("avail_id") + ".) ", rs.getString("first_name") + " " + rs.getString("last_name"), rs.getDate("avail_date"), rs.getString("target"), rs.getString("st_time") + "-" + rs.getString("end_time")));
            }
            System.out.println("---------------------------------------");
            System.out.println("1.) Create a session");
            System.out.println("2.) Update a session");
            System.out.println("3.) Delete a session");
            System.out.println("Enter Choice: ");
            int c = sc.nextInt();
            PreparedStatement ps;
            if(c == 1){
                System.out.println("Enter session date: ");
                String d = sc.next();
                System.out.println("Enter session name: ");
                String n = sc.next();
                System.out.println("Enter start time: ");
                String stt = sc.next();
                System.out.println("Enter end time: ");
                String ent = sc.next();
                ps = connection.prepareStatement("INSERT INTO availability (trainer_id, avail_date, target, st_time, end_time) VALUES (?, ?, ?, ?, ?)");
                ps.setInt(1, user_id);
                ps.setDate(2, Date.valueOf(d));
                ps.setString(3, n);
                ps.setTime(4, Time.valueOf(stt));
                ps.setTime(5, Time.valueOf(ent));
                ps.executeUpdate();
                System.out.println("Availability successfully added");
            }else if(c == 2){
                System.out.println("Enter Session ID: ");
                int sesh = sc.nextInt();
                System.out.println("1.) Change session type");
                System.out.println("2.) Change session date");
                System.out.println("3.) Change session time");
                System.out.println("Enter choice: ");
                int cs = sc.nextInt();
                if(cs == 1){
                    System.out.println("Enter new session type: ");
                    String n = sc.next();
                    ps = connection.prepareStatement("UPDATE availability SET target = ? WHERE avail_id = ?");
                    ps.setString(1, n);
                    ps.setInt(2, sesh);
                    ps.executeUpdate();
                    System.out.println("Availability successfully updated");
                }else if(cs == 2){
                    System.out.println("Enter new session date");
                    String d = sc.next();
                    ps = connection.prepareStatement("UPDATE availability SET avail_date = ? WHERE avail_id = ?");
                    ps.setDate(1, Date.valueOf(d));
                    ps.setInt(2, sesh);
                    ps.executeUpdate();
                    System.out.println("Availability successfully updated");
                }else if(cs == 3){
                    System.out.println("Enter new session start time");
                    String stt = sc.next();
                    System.out.println("Enter new session end time");
                    String ent = sc.next();
                    ps = connection.prepareStatement("UPDATE availability SET st_time = ? WHERE avail_id = ?");
                    ps.setTime(1, Time.valueOf(stt));
                    ps.setInt(2, sesh);
                    ps.executeUpdate();
                    ps = connection.prepareStatement("UPDATE availability SET end_time = ? WHERE avail_id = ?");
                    ps.setTime(1, Time.valueOf(ent));
                    ps.setInt(2, sesh);
                    ps.executeUpdate();
                    System.out.println("Availability successfully updated");
                }

            }else if(c == 3){
                st.executeQuery("SELECT n");
                rs = st.getResultSet();
                System.out.println("-------------Booked sessions-------------");
                System.out.println(String.format("%-4s%-20s%-15s%-15s%-20s%-10s%-10s%-10s", "ID", "Trainer Name", "Location", "Date", "Time", "Capacity", "Attending", "Price"));
                while(rs.next()){
                    System.out.println(String.format("%-4s%-20s%-15s%-15s%-20s%-10s%-10s%4.2f", rs.getInt("room_book_id") + ".)", rs.getString("first_name") + " " + rs.getString("last_name"), rs.getString("room_name"), rs.getDate("booking_date"), rs.getString("st_time") + "-" + rs.getString("end_time"), rs.getInt("capacity"), rs.getInt("curr_size"), rs.getDouble("price")));
                }
                System.out.println("Enter Session ID: ");
                int sesh = sc.nextInt();
                ps = connection.prepareStatement("DELETE FROM room_booking WHERE room_book_id = ?");
                ps.setInt(1, sesh);
                ps.executeUpdate();
                ps = connection.prepareStatement("DELETE FROM fit_session WHERE room_book_id = ?");
                ps.setInt(1, sesh);
                ps.executeUpdate();
                System.out.println("Availability successfully deleted");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void overseeMembers(){
        Scanner sc = new Scanner(System.in);
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement st = connection.createStatement();
            st.executeQuery("SELECT * FROM fit_member");
            ResultSet rs = st.getResultSet();
            System.out.println("-------------Quick View Member Profiles-------------");
            System.out.println(String.format("%-4s%-20s%-20s", "ID", "Username", "Email"));
            while (rs.next()) {
                System.out.println(String.format("%-4s%-20s%-20s", rs.getInt("mem_id") + ".) ", rs.getString("username"), rs.getString("email")));
            }
            System.out.println("---------------------------------------");
            System.out.println("Enter Member username: ");
            String memUser = sc.next();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM fit_member WHERE username = ?");
            ps.setString(1, memUser);
            ps.executeQuery();
            rs = ps.getResultSet();
            rs.next();
            System.out.println("-------------" + memUser + "'s profile-------------");
            System.out.println("First Name: " + rs.getString("first_name"));
            System.out.println("Last Name: " + rs.getString("last_name"));
            System.out.println("Email: " + rs.getString("email"));
            System.out.println("Username: " + rs.getString("username"));
            System.out.println("Date Joined: " + rs.getDate("signedup_date"));
            System.out.println("Age: " + rs.getInt("age"));
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static boolean signInAdmin(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Scanner sc = new Scanner(System.in);

            System.out.print("\nEnter username: ");
            String us = sc.nextLine();
            System.out.print("\nEnter password: ");
            String pass = sc.nextLine();

            PreparedStatement ps = connection.prepareStatement("SELECT admin_id FROM fit_admin WHERE username = ? AND admin_password = ?");
            ps.setString(1, us);
            ps.setString(2, pass);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            if(rs.next()){
                user_id = rs.getInt("admin_id");
                System.out.println("\nAdministrator signed in!");
                return true;
            }else{
                System.out.println("\nAdministrator NOT signed in!");
                return false;
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    public static void adminManageProfile(){
        Scanner sc = new Scanner(System.in);
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password); //connect to the database
            PreparedStatement st = connection.prepareStatement("SELECT * FROM fit_admin WHERE admin_id = ?");
            st.setInt(1, user_id);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            rs.next();
            System.out.println("-------------" + rs.getString("username") + "'s profile-------------");
            System.out.println("First Name: " + rs.getString("first_name"));
            System.out.println("Last Name: " + rs.getString("last_name"));
            System.out.println("Email: " + rs.getString("email"));
            System.out.println("Username: " + rs.getString("username"));
            System.out.println("Password: " + rs.getString("admin_password"));
            System.out.println("---------------------------------------");
            System.out.println("1.) Update email");
            System.out.println("2.) Update username");
            System.out.println("3.) Update password");
            System.out.println("Enter Choice: ");
            int c = sc.nextInt();
            if(c == 1){
                System.out.println("Enter New Email: ");
                String newEmail = sc.next();
                st = connection.prepareStatement("UPDATE fit_admin SET email = ? WHERE admin_id = ?");
                st.setString(1, newEmail);
                st.setInt(2, user_id);
                st.executeUpdate();
                System.out.println("Email successfully updated");
            }else if(c == 2){
                System.out.println("Enter New Username: ");
                String newUsername = sc.next();
                st = connection.prepareStatement("UPDATE fit_admin SET username = ? WHERE admin_id = ?");
                st.setString(1, newUsername);
                st.setInt(2, user_id);
                st.executeUpdate();
                System.out.println("Username successfully updated");
            }else if(c == 3) {
                System.out.println("Enter New Password: ");
                String newPassword = sc.next();
                st = connection.prepareStatement("UPDATE fit_admin SET admin_password = ? WHERE admin_id = ?");
                st.setString(1, newPassword);
                st.setInt(2, user_id);
                st.executeUpdate();
                System.out.println("Password successfully updated");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void adminManageSessions(){
        Scanner sc = new Scanner(System.in);
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            viewAvailSessions();
            System.out.println("1.) Create a session");
            System.out.println("2.) Update a session");
            System.out.println("3.) Delete a session");
            System.out.println("Enter Choice: ");
            int c = sc.nextInt();
            Statement st = connection.createStatement();
            ResultSet rs;
            if(c == 1){
                st.executeQuery("SELECT avail_id, trainer.first_name, trainer.last_name, avail_date, target, st_time, end_time FROM availability FULL JOIN trainer ON availability.trainer_id = trainer.trainer_id");
                rs = st.getResultSet();
                System.out.println("-------------Trainer Availability-------------");
                System.out.println(String.format("%-4s%-20s%-15s%-15s%-20s", "ID", "Trainer Name", "Date", "Session Name", "Time"));
                while(rs.next()){
                    System.out.println(String.format("%-4s%-20s%-15s%-15s%-20s", rs.getInt("avail_id") + ".) ", rs.getString("first_name") + " " + rs.getString("last_name"), rs.getDate("avail_date"), rs.getString("target"), rs.getString("st_time") + "-" + rs.getString("end_time")));
                }
                System.out.println("---------------------------------------");
                System.out.println("Enter the ID of the session you would like to create: ");
                int sesh = sc.nextInt();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM availability WHERE avail_id = ?");
                ps.setInt(1, sesh);
                ps.executeQuery();
                rs = ps.getResultSet();
                rs.next();
                int id = rs.getInt("trainer_id");
                Date d = rs.getDate("avail_date");
                Time t = rs.getTime("st_time");
                Time et = rs.getTime("end_time");
                String n = rs.getString("target");
                st.executeQuery("SELECT * FROM room_booking");
                rs = st.getResultSet();
                while(rs.next()){
                    if((rs.getInt("trainer_id") == id )&& (rs.getDate("booking_date") == d) && (rs.getTime("st_time") == t)){
                        System.out.println("This session already exists");
                        return;
                    }
                }
                st.executeQuery("SELECT * FROM room");
                rs = st.getResultSet();
                System.out.println("-------------Rooms-------------");
                System.out.println(String.format("%-4s%-10s", "ID", "Room Name"));
                while(rs.next()){
                    System.out.println(String.format("%-4s%-10s", rs.getInt("room_id") + ".)", rs.getString("room_name")));
                }
                System.out.println("---------------------------------------");
                System.out.println("Enter the ID of the room you would like to use: ");
                int room = sc.nextInt();
                st.executeQuery("SELECT * FROM maintenance_booking");
                rs = st.getResultSet();
                while(rs.next()){
                    if(room == rs.getInt("room_id") && d == rs.getDate("main_date")){
                        System.out.println("The selected room will undergo maintenance on this date.");
                        return;
                    }
                }
                System.out.println("Enter the class capacity: ");
                int cap = sc.nextInt();
                System.out.println("Enter the class price: ");
                double pr = sc.nextDouble();
                ps = connection.prepareStatement("INSERT INTO room_booking(trainer_id, avail_id, room_num, st_time, end_time, booking_date, capacity, curr_size, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setInt(1, id);
                ps.setInt(2, sesh);
                ps.setInt(3, room);
                ps.setTime(4, t);
                ps.setTime(5, et);
                ps.setDate(6, d);
                ps.setInt(7, cap);
                ps.setInt(8, 0);
                ps.setDouble(9, pr);
                ps.executeUpdate();
                System.out.println("Session successfully created");
                //delete availability after a session has been created??
            }else if(c == 2){
                viewAvailSessions();
                System.out.println("Enter Session ID: ");
                int sesh = sc.nextInt();
                System.out.println("1.) Change session capacity");
                System.out.println("2.) Change session price");
                System.out.println("Enter choice: ");
                int cs = sc.nextInt();
                PreparedStatement ps;
                if(cs == 1){
                    System.out.println("Enter new session capacity: ");
                    int cap = sc.nextInt();
                    ps = connection.prepareStatement("UPDATE room_booking SET capacity = ? WHERE room_book_id = ?");
                    ps.setInt(1, cap);
                    ps.setInt(2, sesh);
                    ps.executeUpdate();
                    System.out.println("Session capacity successfully updated");
                }else if(cs == 2){
                    System.out.println("Enter new session price: ");
                    double price = sc.nextDouble();
                    ps = connection.prepareStatement("UPDATE room_booking SET price = ? WHERE room_book_id = ?");
                    ps.setDouble(1, price);
                    ps.setInt(2, sesh);
                    ps.executeUpdate();
                    System.out.println("Session price successfully updated");
                }
            }else if(c == 3){
                viewAvailSessions();
                System.out.println("Enter Session ID: ");
                int sesh = sc.nextInt();
                //DOES ORDER MATTER???
                PreparedStatement ps = connection.prepareStatement("DELETE FROM room_booking WHERE room_book_id = ?");
                ps.setInt(1, sesh);
                ps.executeUpdate();
                ps = connection.prepareStatement("DELETE FROM fit_session WHERE room_book_id = ?");
                ps.setInt(1, sesh);
                ps.executeUpdate();
                System.out.println("Session successfully deleted");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static void adminManageMaintenance(){
        Scanner sc = new Scanner(System.in);
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement st = connection.createStatement();
            st.executeQuery("SELECT fit_equip_id, fit_admin.first_name, fit_admin.last_name, room.room_name, main_date FROM maintenance_booking, fit_admin, room WHERE maintenance_booking.admin_id = fit_admin.admin_id AND maintenance_booking.room_id = room.room_id");
            ResultSet rs = st.getResultSet();
            System.out.println("-------------Maintenance Appointments-------------");
            System.out.println(String.format("%-4s%-20s%-15s%-15s", "ID", "Administrator", "Location", "Date"));
            while(rs.next()){
                System.out.println(String.format("%-4s%-20s%-15s%-15s", rs.getInt("fit_equip_id") + ".) ", rs.getString("first_name") + " " + rs.getString("last_name"), rs.getString("room_name"), rs.getDate("main_date")));
            }
            System.out.println("---------------------------------------");
            st.executeQuery("SELECT * FROM room");
            rs = st.getResultSet();
            System.out.println("-------------Rooms-------------");
            System.out.println(String.format("%-4s%-10s", "ID", "Room Name"));
            while(rs.next()){
                System.out.println(String.format("%-4s%-10s", rs.getInt("room_id") + ".)", rs.getString("room_name")));
            }
            System.out.println("---------------------------------------");
            System.out.println("1.) Create a maintenance appointment");
            System.out.println("2.) Update a maintenance appointment");
            System.out.println("3.) Delete a maintenance appointment");
            System.out.println("Enter Choice: ");
            int c = sc.nextInt();
            PreparedStatement ps;
            if(c == 1){
                System.out.println("Enter the maintenance date: ");
                String d = sc.next();
                System.out.println("Enter the room ID: ");
                int room = sc.nextInt();
                ps = connection.prepareStatement("INSERT INTO maintenance_booking (admin_id, room_id, main_date) VALUES (?, ?, ?)");
                ps.setInt(1, user_id);
                ps.setInt(2, room);
                ps.setDate(3, Date.valueOf(d));
                ps.executeQuery();
                System.out.println("Maintenance appointment successfully booked");
            }else if(c == 2){
                System.out.println("Enter the appointment ID: ");
                int app = sc.nextInt();
                System.out.println("1.) Change appointment date");
                System.out.println("2.) Change appointment location");
                System.out.println("Enter choice: ");
                int cs = sc.nextInt();
                if(cs == 1){
                    System.out.println("Enter the new maintenance date: ");
                    String date = sc.next();
                    ps = connection.prepareStatement("UPDATE maintenance_booking SET main_date = ? WHERE fit_equip_id = ?");
                    ps.setDate(1, Date.valueOf(date));
                    ps.setInt(2, app);
                    ps.executeUpdate();
                    System.out.println("Maintenance appointment date successfully updated");
                }else if(cs == 2){
                    System.out.println("Enter the new maintenance room ID: ");
                    int room = sc.nextInt();
                    ps = connection.prepareStatement("UPDATE maintenance_booking SET room_id = ? WHERE fit_equip_id = ?");
                    ps.setInt(1, room);
                    ps.setInt(2, app);
                    ps.executeUpdate();
                    System.out.println("Maintenance appointment location successfully updated");
                }
            }else if(c == 3){
                System.out.println("Enter the appointment ID: ");
                int app = sc.nextInt();
                ps = connection.prepareStatement("DELETE FROM maintenance_booking WHERE fit_equip_id = ?");
                ps.setInt(1, app);
                ps.executeUpdate();
                System.out.println("Maintenance appointment successfully deleted");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void adminOverseeBilling(){
        Scanner sc = new Scanner(System.in);
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement st = connection.createStatement();
            st.executeQuery("SELECT * FROM fit_member");
            ResultSet rs = st.getResultSet();
            System.out.println("-------------Member Profiles-------------");
            System.out.println( String.format("%-15s%-25s%-15s%-20s", "Member ID", "Member Name", "Username", "Current Balance"));
            while(rs.next()){
                System.out.println(String.format("%-15s%-20s%-15s%-10s" , rs.getInt("mem_id"), rs.getString("first_name") + " " + rs.getString("last_name"), rs.getString("username"), rs.getDouble("bill") + "$"));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void adminProcessPayments(){
        Scanner sc = new Scanner(System.in);
        adminOverseeBilling();
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password); //connect to the database
            System.out.println("Enter the member's username: ");
            String memUsername = sc.next();
            System.out.println("Enter the amount paid: ");
            int amount = sc.nextInt();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM fit_member WHERE username = ?");
            st.setString(1, memUsername);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            rs.next();
            double total = rs.getDouble("bill");
            double newTotal = total - amount;
            st = connection.prepareStatement("UPDATE fit_member SET bill = ? WHERE username = ?");
            st.setDouble(1, newTotal);
            st.setString(2, memUsername);
            st.executeUpdate();
            System.out.println("Payment processed successfully");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

}
