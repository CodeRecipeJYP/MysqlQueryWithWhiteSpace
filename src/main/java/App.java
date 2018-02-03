import java.sql.*;

public class App {

    public static void main(String[] args) {

        Db db = new Db();

        // First time Crate Table
//        db.create();

        System.out.println("Insert samples");
        db.insertSample(1, "Title1", "Content1");
        db.insertSample(2, "Title2", "Content2");
        System.out.println("");

        System.out.println("Before update");
        db.retrieve();
        System.out.println("");


        db.updateWithWhiteSpace(1, "withwhiteTitle", "content1");
        db.updateWithoutWhiteSpace(2, "withoutWhiteTitle", "content2");
        System.out.println("");
        System.out.println("After update");
        db.retrieve();


        db.clearSamples();
        System.out.println("After clear");
        db.retrieve();

        db.close();
    }
}
