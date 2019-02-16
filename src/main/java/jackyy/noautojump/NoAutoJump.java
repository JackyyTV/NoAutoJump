package jackyy.noautojump;

import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(NoAutoJump.MODID)
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class NoAutoJump {

    public static final String MODID = "noautojump";
    public static final String MODNAME = "No Auto Jump";
    public static final Logger LOGGER = LogManager.getLogger(MODNAME);

    public NoAutoJump() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent @OnlyIn(Dist.CLIENT)
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Minecraft.getInstance().gameSettings.autoJump = false;
        }
    }

    @SubscribeEvent @OnlyIn(Dist.CLIENT)
    public void onInitGui(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.getGui() instanceof GuiControls) {
            for (GuiButton button : event.getButtonList()) {
                if (button instanceof GuiOptionButton && ((GuiOptionButton) button).getOption() == GameSettings.Options.AUTO_JUMP) {
                    button.enabled = false;
                }
            }
        }
    }

}
