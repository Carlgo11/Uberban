package com.carlgo11.uberban;

import com.carlgo11.uberban.player.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    
    
    public void onEnable(){
        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
    }
    

}
