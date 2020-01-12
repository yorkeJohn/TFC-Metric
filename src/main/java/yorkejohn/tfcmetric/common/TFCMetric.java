package yorkejohn.tfcmetric.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import yorkejohn.tfcmetric.common.handler.MetricHandler;
import yorkejohn.tfcmetric.common.lib.LibInfo;

@Mod (modid = LibInfo.MODID, name = LibInfo.NAME, version = LibInfo.VERSION, dependencies = LibInfo.DEPENDENCIES)
public class TFCMetric
{
	public static final Logger log = LogManager.getLogger("TFC-Metric");

	@EventHandler
	public static void init (FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new MetricHandler());
		log.info("Converting units to metric...");
	}
}
