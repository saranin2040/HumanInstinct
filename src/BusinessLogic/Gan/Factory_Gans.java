package BusinessLogic.Gan;
import BusinessLogic.Enemes.Enemy;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class Factory_Gans
{
    public Gan createGan(String name)
    {
        String str="";
        try {
            File file=new File("C:\\Users\\aldan\\IdeaProjects\\game2\\src\\BusinessLogic\\Gan\\GansNames.properties");
            Properties properties=new Properties();
            properties.load(new FileReader(file));

            str = (String) properties.get(name);

            return (Gan)Class.forName(str).newInstance();
        }
        catch (Exception ex)
        {
           return null;
        }
    }
}