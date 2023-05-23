package jackyy.noautojump;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.screens.controls.ControlsScreen;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

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
                Minecraft.getInstance().options.autoJump = false;
            }
        }
    }

    @SubscribeEvent @OnlyIn(Dist.CLIENT)
    public void onInitGui(ScreenEvent.InitScreenEvent.Post event) {
        if (ModConfigs.CONFIG.enableMod.get()) {
            if (event.getScreen() instanceof ControlsScreen) {
                for (Widget button : event.getScreen().renderables) {
                    if (button instanceof CycleButton<?>) {
                        TranslatableComponent autoJumpOn = new TranslatableComponent("options.generic_value", new TranslatableComponent("options.autoJump"), new TranslatableComponent("options.on"));
                        TranslatableComponent autoJumpOff = new TranslatableComponent("options.generic_value", new TranslatableComponent("options.autoJump"), new TranslatableComponent("options.off"));
                        if (((CycleButton<?>) button).getMessage().equals(autoJumpOn) || ((CycleButton<?>) button).getMessage().equals(autoJumpOff)) {
                            ((CycleButton<?>) button).active = false;
                        }
                    }
                }
            }
        }
    }

    public static class ModConfigs {
        public static class ClientConfig {
            public final ForgeConfigSpec.BooleanValue enableMod;
            ClientConfig(ForgeConfigSpec.Builder builder) {
                builder.comment("No Auto Jump Config")
                        .push("general");
                enableMod = builder
                        .comment(
                                "If true, enables this mod, which forces the Auto Jump option to be disabled.",
                                "Set this to false if you want to restore the Auto Jump option temporarily without removing the mod.",
                                "Game restart is not needed for this to take effect."
                        )
                        .define("enableMod", true);
                builder.pop();
            }
        }
        public static final ForgeConfigSpec SPEC;
        public static final ModConfigs.ClientConfig CONFIG;
        static {
            final Pair<ModConfigs.ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ModConfigs.ClientConfig::new);
            SPEC = specPair.getRight();
            CONFIG = specPair.getLeft();
        }
    }

}
