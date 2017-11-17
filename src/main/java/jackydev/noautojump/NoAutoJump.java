package jackydev.noautojump;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(modid = NoAutoJump.MODID, version = NoAutoJump.VERSION, name = NoAutoJump.MODNAME, acceptedMinecraftVersions = NoAutoJump.MCVERSION, clientSideOnly = true, acceptableRemoteVersions = "*", useMetadata = true)
public class NoAutoJump {

    public static final String VERSION = "1.0";
    public static final String MCVERSION = "[1.12,)";
    public static final String MODID = "noautojump";
    public static final String MODNAME = "No Auto Jump";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Minecraft.getMinecraft().gameSettings.autoJump = false;
        }
    }

    @SubscribeEvent
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
