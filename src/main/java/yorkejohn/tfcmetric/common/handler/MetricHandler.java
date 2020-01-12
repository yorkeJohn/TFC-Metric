package yorkejohn.tfcmetric.common.handler;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.Food.ItemMeal;
import com.bioxx.tfc.Items.Tools.ItemCustomBucketMilk;
import com.bioxx.tfc.api.Food;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class MetricHandler
{
	@SubscribeEvent
	public void tooltipEvent (ItemTooltipEvent e)
	{
		ItemStack s = e.itemStack;
		List<String> l = e.toolTip;

		if (s != null && (s.getItem() instanceof ItemFoodTFC || s.getItem() instanceof ItemMeal || s.getItem() instanceof ItemCustomBucketMilk))
		{
			for (int idx = 0; idx < l.size(); idx++)
			{
				String str = l.get(idx);
				if (str.contains("oz"))
				{
					l.remove(idx);
					float oz = Food.getWeight(s);
					float grams = (oz / 160.0F) * 1000.0F;
					BigDecimal bd = new BigDecimal(grams);
					bd = bd.round(new MathContext(5));
					grams = bd.floatValue();
					if (!(s.getItem() instanceof ItemCustomBucketMilk) && grams > 0.0F && grams <= 1000.0F)
						l.add(idx, TFC_Core.translate("gui.food.amount") + " " + grams + " g / " + 1000.0F + " g");
					else if(grams > 0.0F)
						l.add(idx, grams + " g");
				}
			}
		}
	}
}
