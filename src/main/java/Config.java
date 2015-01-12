import java.io.File;

public abstract class Config
{
	public static final int DIR_TMP = 0;
	public static final int DIR_TEST_OUTPUT = 1;
	public static final int DIR_MODELS = 2;
	public static final int DIR_TEXTURES = 3;
	
	
	public Config()
	{}
	public String allDefaultStrings()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("folder.samples=");
		return sb.toString();
	}
	public abstract File[] dirs(int type);
}