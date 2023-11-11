package net.rotgruengelb.jump_connect.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;
import net.rotgruengelb.jump_connect.config.ModConfigModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.rotgruengelb.jump_connect.Jump_Connect.CONFIG;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
	protected TitleScreenMixin(Text title) {
		super(title);
	}


	@Unique
	private void connect(String address, ModConfigModel.ResourcePackPolicy resourcePackPolicy) {
		ServerInfo info = new ServerInfo(I18n.translate("selectServer.defaultName"), address, ServerInfo.ServerType.OTHER);

		switch (resourcePackPolicy) {
			case ALLOW -> info.setResourcePackPolicy(ServerInfo.ResourcePackPolicy.ENABLED);
			case DENY -> info.setResourcePackPolicy(ServerInfo.ResourcePackPolicy.DISABLED);
			case PROMPT -> info.setResourcePackPolicy(ServerInfo.ResourcePackPolicy.PROMPT);
		}

		ConnectScreen.connect(this, MinecraftClient.getInstance(), ServerAddress.parse(info.address), info, false);
	}

	@Inject(at = @At("TAIL"), method = "onMultiplayerButtonPressed")
	private void onMultiplayerButtonPressed(CallbackInfo callbackInfo)
	{
		if (Screen.hasAltDown()) {
			connect(CONFIG.alt_key.address(), CONFIG.alt_key.resourcePackPolicy());
		}
		else if (Screen.hasControlDown()) {
			connect(CONFIG.alt_key.address(), CONFIG.control_key.resourcePackPolicy());
		}
		else if (Screen.hasShiftDown()) {
			connect(CONFIG.shift_key.address(), CONFIG.shift_key.resourcePackPolicy());
		}
	}
}