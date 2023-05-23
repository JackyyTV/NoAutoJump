package jackyy.noautojump;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = NoAutoJump.MODID, version = NoAutoJump.VERSION, name = NoAutoJump.MODNAME, certificateFingerprint = "@FINGERPRINT@", acceptedMinecraftVersions = NoAutoJump.MCVERSION, clientSideOnly = true, acceptableRemoteVersions = "*", useMetadata = true)
public class NoAutoJump {

    public static final String VERSION = "1.2";
    public static final String MCVERSION = "[1.12,1.13)";
    public static final String MODID = "noautojump";
    public static final String MODNAME = "No Auto Jump";
    public static final Logger LOGGER = LogManager.getLogger(MODNAME);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (ModConfig.enableMod) {
            if (event.phase == TickEvent.Phase.END) {
                Minecraft.getMinecraft().gameSettings.autoJump = false;
            }
        }
    }

    @SubscribeEvent
    public void onInitGui(GuiScreenEvent.InitGuiEvent.Post event) {
        if (ModConfig.enableMod) {
            if (event.getGui() instanceof GuiControls) {
                for (GuiButton button : event.getButtonList()) {
                    if (button instanceof GuiOptionButton && ((GuiOptionButton) button).getOption() == GameSettings.Options.AUTO_JUMP) {
                        button.enabled = false;
                    }
                }
            }
        }
    }

    @Mod.EventHandler
    public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
        LOGGER.warn("Invalid fingerprint detected! The file " + event.getSource().getName() + " may have been modified or running in dev environment.");
    }

    @Config(modid = MODID, name = "NoAutoJump")
    public static class ModConfig {
        @Config.Comment({
                "If true, enables this mod, which forces the Auto Jump option to be disabled.",
                "Set this to false if you want to restore the Auto Jump option temporarily without removing the mod.",
                "Game restart is not needed for this to take effect if this option was changed through the in-game config GUI."
        })
        public static boolean enableMod = true;
        @Mod.EventBusSubscriber
        public static class ConfigHolder {
            @SubscribeEvent
            public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
                if (event.getModID().equals(MODID)) {
                    ConfigManager.sync(MODID, Config.Type.INSTANCE);
                }
            }
        }
    }

}
