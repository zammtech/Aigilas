package com.spx.core;import com.xna.wrapper.*;import java.util.*;
    public class XnaManager
    {
        public static int WindowHeight = GameManager.SpriteHeight * GameManager.TileMapHeight; //720 //1050
        public static int WindowWidth = GameManager.SpriteWidth * GameManager.TileMapWidth; //1280 //1680
        public static int RenderHeight = WindowHeight;
        public static int RenderWidth = WindowWidth;
        private static ContentManager __assetHandler;
        private static GraphicsDeviceManager __graphics;
        public static SpriteBatch Renderer;

        private static final String __menuBaseSprite = "MenuBase";
        private static final String __gameplaySheetSprite = "GameplaySheet";
        private static final String __gameOverSprite = "GameOver";
        private static final String __fontName = "Action";
        private static final String __particleSprite = "Particle";

        public static void SetContentManager(ContentManager assetHandler)
        {
            __assetHandler = assetHandler;
        }

        private static Texture2D GetAsset(String resourceName)
        {
        	return __assetHandler.LoadTexture(resourceName);
        }
        public static Texture2D GetParticleAsset()
        {
            return GetAsset(__particleSprite);
        }

        public static Texture2D GetMenuBaseAsset()
        {
            return GetAsset(__menuBaseSprite);
        }

        public static Texture2D GetSpriteAsset()
        {
            return GetAsset(__gameplaySheetSprite);
        }

        public static Texture2D GetGameOverAsset()
        {
            return GetAsset(__gameOverSprite);
        }

        private static SpriteFont GetFont(String resourceName)
        {
            return __assetHandler.LoadSpriteFont(resourceName);
        }

        public static SpriteFont GetActionFont()
        {
            return GetFont(__fontName);
        }

        public static void SetupCamera(GraphicsDeviceManager graphics,boolean isFullScreen)
        {
            __graphics = graphics;
            __graphics.PreferredBackBufferHeight = XnaManager.WindowHeight;
            __graphics.PreferredBackBufferWidth = XnaManager.WindowWidth;
            __graphics.IsFullScreen = isFullScreen;
            __graphics.ApplyChanges();
            Resolution.Init(__graphics);
            Resolution.SetVirtualResolution(WindowWidth, WindowHeight);
            Resolution.SetResolution(RenderWidth, RenderHeight, isFullScreen);
        }

        public static Vector2 GetCenter()
        {
            return new Vector2(WindowWidth/2, WindowHeight/2);
        }

        public static Vector2 GetDimensions()
        {
            return new Vector2(WindowWidth,WindowHeight);
        }

        public static Matrix GetScalingMatrix()
        {
            return Resolution.getTransformationMatrix();
        }
    }
