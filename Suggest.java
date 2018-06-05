package nikhi.a5_minutestressreliever;

/**
 * Created by nikhi on 4/4/18.
 */
public class Suggest {
    String globalDesc;
    String globalName;
    int globalImage;
    String globalCategory;

    public Suggest(String name, String description, int img, String category)
    {
        globalDesc = description;
        globalName = name;
        globalImage = img;
        globalCategory = category;
    }
    public String getName()
    {
        return globalName;
    }
    public String getDesc()
    {
        return globalDesc;
    }
    public int getImage()
    {
        return globalImage;
    }
    public String getCategory()
    {
        return globalCategory;
    }


}
