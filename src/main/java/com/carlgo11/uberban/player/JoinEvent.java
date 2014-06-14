package com.carlgo11.uberban.player;

import com.carlgo11.uberban.Main;
import com.carlgo11.uberban.Mysql;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class JoinEvent implements Listener {
    
    private Main plugin;

    public JoinEvent(Main plug)
    {
        this.plugin = plug;
    }
    
    @EventHandler
    public void onJoin(PlayerLoginEvent  e){
        Player p = e.getPlayer();
        if(Mysql.ifPlayerBanned(p.getUniqueId().toString())){
            e.disallow(PlayerLoginEvent.Result.KICK_BANNED, "lel no :P");
        }else{
            e.allow();
        }
    }

}
