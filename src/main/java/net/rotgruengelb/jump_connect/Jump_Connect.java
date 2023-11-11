package net.rotgruengelb.jump_connect;

import net.fabricmc.api.ClientModInitializer;
import net.rotgruengelb.jump_connect.config.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Jump_Connect implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("jump_connect");

    public static final ModConfig CONFIG = ModConfig.createAndLoad();

    @Override
    public void onInitializeClient() {

        LOGGER.info("Hello Fabric world!");

    }
}