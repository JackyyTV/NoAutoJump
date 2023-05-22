package jackyy.noautojump;

import net.minecraft.client.AbstractOption;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ControlsScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.OptionButton;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(NoAutoJump.MODID)
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class NoAutoJump {

    public static final String MODID = "noautojump";

    public NoAutoJump() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ModConfigs.SPEC);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent @OnlyIn(Dist.CLIENT)
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (ModConfigs.CONFIG.enableMod.get()) {
            if (event.phase == TickEvent.Phase.END) {
                Minecraft.getInstance().gameSettings.autoJump = false;
            }
        }
    }

    @SubscribeEvent @OnlyIn(Dist.CLIENT)
    public void onInitGui(GuiScreenEvent.InitGuiEvent.Post event) {
        if (ModConfigs.CONFIG.enableMod.get()) {
            if (event.getGui() instanceof ControlsScreen) {
                for (Widget button : event.getWidgetList()) {
                    if (button instanceof OptionButton && ((OptionButton) button).getOptions() == AbstractOption.AUTO_JUMP) {
                        button.active = false;
                    }
                }
            }
        }
    }

}
