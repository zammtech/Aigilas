package com.aigilas.entities;
    public class Floor  extends Entity
    {
        public Floor(Point2 location)
        {
            Initialize(location, SpriteType.FLOOR, com.aigilas.EntityType.FLOOR,com.aigilas.Depth.Floor);
        }
    }