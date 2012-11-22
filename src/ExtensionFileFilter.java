import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class ExtensionFileFilter extends FileFilter
{

    private String description = "";
    private final String regex[];

    public ExtensionFileFilter(String description, String regex)
    {
        this(description, new String[] { regex });
    }

    public ExtensionFileFilter(String description, String regex[])
    {
        if ( description == null )
        {
            for ( String reg : regex )
            {
                description += reg + "; ";
            }
        }
        else
        {
            this.description = description;
        }

        this.regex = regex.clone();
    }

    public void lowerCase()
    {
        for ( String reg : this.regex )
        {
            reg = reg.toLowerCase();
        }
    }

    public String getDescription()
    {
        return this.description;
    }

    public boolean accept(File file)
    {
        if ( file.isDirectory() )
        {
            return true;
        }
        else
        {
            String path = file.getAbsolutePath().toLowerCase();
            for ( String reg : regex )
            {
                if ( path.matches(reg) )
                {
                    return true;
                }
            }
        }
        return false;
    }
}

