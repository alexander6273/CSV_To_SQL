import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileHandler
{
    private Scanner sc = new Scanner(System.in);
    private StringBuilder sb = new StringBuilder();

    public Scanner reader(String fileName){
        File file = new File(fileName);
        try{
            return new Scanner(file);
        }
        catch (FileNotFoundException e){
            System.out.println("Could not find the file");
            e.printStackTrace();
        }
        return sc;
    }

    public void writeTo_ddl(String SQL){
        try{
            PrintStream ps = new PrintStream(new FileOutputStream(SQL));
            ps.print(loadcolumns());
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void writeTo_dml(String SQL){
        try{
            PrintStream ps = new PrintStream(new FileOutputStream(SQL));
            ps.print(insertinto());
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public String loadcolumns(){
        sc = reader("data/imdbData.csv");

        String firstLine = sc.nextLine();
        String[] columnNames = firstLine.split(";");

        sb = new StringBuilder();
        sb.append("CREATE TABLE imdb (\n");
        for (int i = 0; i < columnNames.length; i++)
        {
            String coulmnHeader = columnNames[i];
            if (i == columnNames.length - 1){
                sb.append(coulmnHeader + " varchar(255) \n);");
            }else{
                sb.append(coulmnHeader + " varchar(255), \n");
            }
        }
        return sb.toString();
    }

    public String insertinto(){
        sc = reader("data/imdbData.csv");
        sc.nextLine();

        sb = new StringBuilder();
        sb.append("INSERT INTO imdb");

        //while (sc.hasNextLine()){
            sb.append("\nVALUES (");

            String line = sc.nextLine();
            String[] data = line.split(";");

            for (int i = 0; i < data.length; i++)
            {
                if (i == data.length - 1){
                    sb.append("'" + data[i] + "'");
                }else {
                    sb.append("'" + data[i] + "', ");
                }
            }
            sb.append(");");
        //}

        return sb.toString();
    }
}
