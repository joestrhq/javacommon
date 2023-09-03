//
// Copyright (c) 2022 Joel Strasser <strasser999@gmail.com>
//
// Licensed under the EUPL-1.2 license.
//
// For the full license text consult the 'LICENSE' file from the repository.
//
package at.joestr.javacommon.foliautils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author joestr
 */
public class FoliaUtils {

  public static void scheduleAsync(Plugin plugin, Runnable runnable) {
    if (isFolia()) {
      Bukkit.getAsyncScheduler().runNow(plugin, (t) -> {
        runnable.run();
      });
    } else {
      Bukkit.getScheduler().runTaskAsynchronously(plugin, runnable);
    }
  }

  public static void scheduleSyncForEntity(Plugin plugin, Entity entity, Runnable runnable) {
    if (isFolia()) {
      entity.getScheduler().run(plugin, (t) -> {
        runnable.run();
      }, null);
    } else {
      Bukkit.getScheduler().runTask(plugin, runnable);
    }
  }

  public static boolean isFolia() {
    try {
      Class.forName("io.papermc.paper.threadedregions.scheduler.ScheduledTask");
      return true;
    } catch (ClassNotFoundException ex) {
      return false;
    }
  }
}
