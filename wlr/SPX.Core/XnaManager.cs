﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework;

namespace SPX.Core
{
    public static class XnaManager
    {
        public static readonly int WindowHeight = GameManager.SpriteHeight*20;
        public static readonly int WindowWidth = GameManager.SpriteWidth*30;
        private static ContentManager s_assetHandler;
        private static Camera s_camera = new Camera();
        private static GraphicsDeviceManager s_graphicsDevice;
        public static SpriteBatch Renderer;

        private const string s_menuBaseSprite = "MenuBase";
        private const string s_gameplaySheetSprite = "GameplaySheet";
        private const string s_gameOverSprite = "GameOver";
        private const string s_fontName = "Action";

        public static void SetContentManager(ContentManager assetHandler)
        {
            s_assetHandler = assetHandler;
        }

        private static Texture2D GetAsset(string resourceName)
        {
            return s_assetHandler.Load<Texture2D>(resourceName);
        }

        public static Texture2D GetMenuBaseAsset()
        {
            return GetAsset(s_menuBaseSprite);
        }

        public static Texture2D GetSpriteAsset()
        {
            return GetAsset(s_gameplaySheetSprite);
        }

        public static Texture2D GetGameOverAsset()
        {
            return GetAsset(s_gameOverSprite);
        }

        private static SpriteFont GetFont(string resourceName)
        {
            return s_assetHandler.Load<SpriteFont>(resourceName);
        }

        public static SpriteFont GetActionFont()
        {
            return GetFont(s_fontName);
        }

        public static void SetupCamera(GraphicsDeviceManager graphicsDevice)
        {
            s_camera.Pos = new Vector2(GameManager.SpriteWidth*15, GameManager.SpriteHeight*10);
            s_graphicsDevice = graphicsDevice;
            s_camera.Zoom = 1f;
            //s_graphicsDevice.IsFullScreen = true;
        }

        public static Camera GetCamera()
        {
            return s_camera;
        }

        public static GraphicsDeviceManager GetGraphicsDevice()
        {
            return s_graphicsDevice;
        }

        public static Vector2 GetCenter()
        {
            return new Vector2(WindowWidth/2, WindowHeight/2);
        }

        public static Vector2 GetDimensions()
        {
            return new Vector2(WindowWidth,WindowHeight);
        }
    }
}