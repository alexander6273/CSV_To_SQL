import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args) throws FileNotFoundException
    {
        FileHandler fh = new FileHandler();
        fh.writeTo_ddl("src/ddl.sql");
        fh.writeTo_dml("src/dml.sql");
    }
}
