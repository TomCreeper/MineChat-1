/*
*** UPDATE CLASS MADE BY LULOAK2 ***
*/


//!!!!!!!!You have to change your package adress!!!!!!!!!
package tk.mhutti1.minechat;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import org.bukkit.plugin.PluginDescriptionFile;

//!!!!!!!!!You have to change the "extends" to your Plugin!!!!!!!!!
public class Updater extends MineChat
{
	boolean updateAvailable = false;
	String version;
	String plugin_name;

	public Updater() { }

	public boolean update(String new_version_adress, String new_plugin_adress)
	{
		PluginDescriptionFile pdFile = getDescription();
		plugin_name = pdFile.getName();
		plugin_name = "MineChat";
		System.out.println("["+ plugin_name +"] Searching for update...");

		try
		{
			 URL url1 = new URL(new_version_adress);
			 URLConnection connection = url1.openConnection();

			 ByteArrayOutputStream result1 = new ByteArrayOutputStream();
			 java.io.InputStream input1 = connection.getInputStream();
			 byte[] buffer = new byte[1000];
			 int amount = 0;

			 while(amount != -1)
			 {

				 	result1.write(buffer, 0, amount);
			   		amount = input1.read(buffer);

			   		version = result1.toString();
			 }

			String Version_String_Temp = version.toString();
			Double version_double = Double.parseDouble(Version_String_Temp);

			String version_now = "1.42";
			Double version_now_double = Double.parseDouble(version_now);

			if(version_now_double < version_double && version_double > version_now_double)
			{
				System.out.println("["+ plugin_name +"] Update found!");
				System.out.println("["+ plugin_name +"] Updating...");

				URL url2 = new URL(new_plugin_adress);
				ReadableByteChannel rbc1 = Channels.newChannel(url2.openStream());
				FileOutputStream fos1 = new FileOutputStream("plugins/" + plugin_name + ".jar");
				fos1.getChannel().transferFrom(rbc1, 0, 1 << 24);

				System.out.println("["+ plugin_name +"] Updated sucessfully!");
			}
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			System.out.println("["+ plugin_name +"] Unable to find Version-File!");
			System.out.println("["+ plugin_name +"] Unable to load update!");
			e.printStackTrace();
		}
		return true;
	}
}
