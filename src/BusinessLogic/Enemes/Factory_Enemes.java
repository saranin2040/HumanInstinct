package BusinessLogic.Enemes;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.HashMap;
import java.util.Properties;
public class Factory_Enemes
{
    public Enemy createEnemy(char number)
    {
        String str="";
        try {
            File file=new File("C:\\Users\\aldan\\IdeaProjects\\game2\\src\\BusinessLogic\\Enemes\\EnemesNames.properties");
            Properties properties=new Properties();
            properties.load(new FileReader(file));

            str = (String) properties.get(number+"");

            return (Enemy)Class.forName(str).newInstance();
        }
        catch (Exception ex)
        {
            System.out.println(ex);
           return null;
        }
    }
}