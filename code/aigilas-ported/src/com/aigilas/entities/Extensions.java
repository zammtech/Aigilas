package com.aigilas.entities;import com.aigilas.creatures.ICreature;import com.spx.entities.EntityType;import com.spx.entities.IEntity;public class Extensions {	public static ICreature IsCreature(IEntity entity) {		if (entity.GetEntityType() == EntityType.ACTOR) {			return (ICreature) entity;		}		return null;	}}