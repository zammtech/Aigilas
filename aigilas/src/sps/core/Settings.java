package sps.core;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Settings {
    private static final String __configPath = "assets/settings.cfg";

    private static Settings __instance;

    public static Settings get() {
        if (__instance == null) {
            __instance = new Settings();
        }
        return __instance;
    }

    private final HashMap<String, String> _values = new HashMap<>();
    public int port;
    public String serverIp;
    public boolean clientVerbose;
    public boolean serverVerbose;
    public boolean messageContentsVerbose;
    public boolean messageHandlerVerbose;
    public boolean clientManagerVerbose;
    public boolean networkingEnabled;

    public int enemyCap;
    public int enemyBase;
    public int itemCap;
    public int itemBase;
    public int bossLevelMod;
    public int maxRoomCount;
    public float defaultSpeed;
    public float defaultRegen;
    public float turnTime;

    public int spriteHeight;
    public int spriteWidth;
    public int tileMapHeight;
    public int tileMapWidth;

    public boolean consoleLogging;

    private Settings() {
        try {
            FileInputStream fstream = new FileInputStream(__configPath);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.contains("##") && line.length() > 1) {
                    String key = line.split("=")[0];
                    String value = line.split("=")[1];
                    _values.put(key, value);
                    // Networking
                    if (key.equals("server_ip"))
                        serverIp = value;
                    if (key.equals("socket_port"))
                        port = Integer.parseInt(value);
                    if (key.equals("server_log_verbose"))
                        serverVerbose = isTrue(value);
                    if (key.equals("client_log_verbose"))
                        clientVerbose = isTrue(value);
                    if (key.equals("message_contents_log_verbose"))
                        messageContentsVerbose = isTrue(value);
                    if (key.equals("message_handler_log_verbose"))
                        messageHandlerVerbose = isTrue(value);
                    if (key.equals("client_manager_log_verbose"))
                        clientManagerVerbose = isTrue(value);
                    if (key.equals("networking_enabled"))
                        networkingEnabled = isTrue(value);

                    // Gameplay
                    if (key.equals("enemyCap"))
                        enemyCap = Integer.parseInt(value);
                    if (key.equals("enemyBase"))
                        enemyBase = Integer.parseInt(value);
                    if (key.equals("itemCap"))
                        itemCap = Integer.parseInt(value);
                    if (key.equals("itemBase"))
                        itemBase = Integer.parseInt(value);
                    if (key.equals("bossLevelMod"))
                        bossLevelMod = Integer.parseInt(value);
                    if (key.equals("maxRoomCount"))
                        maxRoomCount = Integer.parseInt(value);
                    if (key.equals("defaultSpeed"))
                        defaultSpeed = Float.parseFloat(value);
                    if (key.equals("defaultRegen"))
                        defaultRegen = Float.parseFloat(value);
                    if (key.equals("turnTime"))
                        turnTime = Float.parseFloat(value);

                    // Display
                    if (key.equals("spriteHeight"))
                        spriteHeight = Integer.parseInt(value);
                    if (key.equals("spriteWidth"))
                        spriteWidth = Integer.parseInt(value);
                    if (key.equals("tileMapHeight"))
                        tileMapHeight = Integer.parseInt(value);
                    if (key.equals("tileMapWidth"))
                        tileMapWidth = Integer.parseInt(value);

                    // Dev
                    if (key.equals("consoleLogging"))
                        consoleLogging = isTrue(value);
                } else {
                    System.out.println("SETTINGS: Parsing section '" + line.replace("##", "") + "'");
                }
            }
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private boolean isTrue(String value) {
        return value.equalsIgnoreCase("true");
    }
}