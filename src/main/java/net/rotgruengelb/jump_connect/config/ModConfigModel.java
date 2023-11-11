package net.rotgruengelb.jump_connect.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.Nest;
import io.wispforest.owo.config.annotation.SectionHeader;

@Modmenu(modId = "jump_connect")
@Config(name = "jump_connect", wrapperName = "ModConfig")
public class ModConfigModel {


    @Nest
    public Nested alt_key = new Nested();

    @Nest
    public Nested control_key = new Nested();

    @Nest
    public Nested shift_key = new Nested();

    public static class Nested {
        public String address = "local_host";
        public ResourcePackPolicy resourcePackPolicy = ModConfigModel.ResourcePackPolicy.PROMPT;
    }

    public enum ResourcePackPolicy {
        ALLOW, DENY, PROMPT
    }
}
