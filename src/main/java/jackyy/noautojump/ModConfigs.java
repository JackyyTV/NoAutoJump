package jackyy.noautojump;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ModConfigs {

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
    public static final ClientConfig CONFIG;
    static {
        final Pair<ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        SPEC = specPair.getRight();
        CONFIG = specPair.getLeft();
    }

}
