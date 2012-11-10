package aigilas.net;

import aigilas.Config;
import sps.core.Logger;
import sps.core.Settings;

public class Client {
    private static IClient __instance;

    public static IClient get() {
        if (__instance == null) {
            if (Config.get().networkingEnabled) {
                __instance = new LanClient();
                while (!__instance.isConnected()) {
                    // Wait
                }
            }
            else {
                __instance = new LocalClient();
            }
        }
        return __instance;
    }
}