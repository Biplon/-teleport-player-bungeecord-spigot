package tppbs.java;

import net.minecraft.server.v1_15_R1.CommandData;
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
                if (c.equals("tppbc:main"))
                {
                    String somedata = msgin.readUTF();
                    String[] values = somedata.split(",");
                    Player p = Bukkit.getPlayer(values[0]);
                    World w = Bukkit.getWorld(values[4]);
                    float x = Integer.parseInt(values[1]);
                    float y =  Integer.parseInt(values[2]);
                    float z =  Integer.parseInt(values[3]);
                    Location loc = new Location(w, x, y, z);
                    assert p != null;
                    p.teleport(loc);
                    Bukkit.getLogger().info(somedata);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }


    }

}