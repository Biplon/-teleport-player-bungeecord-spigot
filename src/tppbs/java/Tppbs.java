package tppbs.java;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class Tppbs extends JavaPlugin implements PluginMessageListener
{
    public static Tppbs instance;

    public static Tppbs getInstance()
    {
        return instance;
    }


    @Override
    public void onEnable()
    {
        instance = this;
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "tppbc:main", this);
    }

    @Override
    public void onDisable()
    {

    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message)
    {

            DataInputStream msgin = new DataInputStream(new ByteArrayInputStream(message));
            try
            {
                String c = msgin.readUTF();
                String somedata = msgin.readUTF(); // Read the data in the same way you wrote it
                String[] werte = somedata.split(",");
                Player p = Bukkit.getPlayer(werte[0]);
                World w = Bukkit.getWorld(werte[4]);
                float x = Integer.parseInt(werte[1]);
                float y =  Integer.parseInt(werte[2]);
                float z =  Integer.parseInt(werte[3]);
                Location loc = new Location(w, x, y, z);
                p.teleport(loc);
                Bukkit.getLogger().info(somedata);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }


    }

}