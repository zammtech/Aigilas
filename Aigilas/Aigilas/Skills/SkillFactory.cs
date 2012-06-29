﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Aigilas.Creatures;
using SPX.Sprites;
using Aigilas.Entities;
using SPX.Core;

namespace Aigilas.Skills
{
    public class SkillId
    {
        public static readonly string[] Values = 
        {
            SkillId.ABSORB,
            SkillId.ACID_DRIP,
            SkillId.ACID_NOZZLE,
            SkillId.BRIMSTONE,
            SkillId.BOIL,
            SkillId.BREAKING_WHEEL,
            SkillId.CAVALRY,
            SkillId.COLD_SHOULDER,
            SkillId.COMBUST,
            SkillId.CONFUSION,
            SkillId.DART,
            SkillId.DART_TRAP,
            SkillId.ELECTRIFY,
            SkillId.ENVENOM,
            SkillId.EXPLODE,
            SkillId.FIREBALL,
            SkillId.FLAME_HAMMER,
            SkillId.FLOOR_SPIKES,
            SkillId.FORGET_SKILL,
            SkillId.GUSH,
            SkillId.HORDER,
            SkillId.HORRIFY,
            SkillId.ICE_SHARD,
            SkillId.MAGIC_MAP,
            SkillId.MANA_UP,
            SkillId.MIMIC,
            SkillId.MUTINY,
            SkillId.NO_SKILL,
            SkillId.POISON_CLOUD,
            SkillId.PLAGUE,
            SkillId.REGEN_ALL,
            SkillId.REMOTE_MINE,
            SkillId.SERPENT_SUPPER,
            SkillId.SOUL_CRUSH,
            SkillId.SOUL_REINFORCEMENT,
            SkillId.SPAWN_ITEM,
            SkillId.SPEED_UP,
            SkillId.STEAL_ITEM,
            SkillId.STRENGTH_UP,
            SkillId.THROW_ITEM,
            SkillId.VALEDICTORIAN,
            SkillId.VAPOR_IMPLANT,
            SkillId.VENOM_FIST,
            SkillId.WALL_PUNCH,
            SkillId.WEAK_KNEEES,
            SkillId.VAPOR_CLOUD,
            SkillId.DISMEMBERMENT,
            SkillId.HYPOTHERMIA
        };

        public const string FIREBALL = "Fireball";
        public const string NO_SKILL = "No Skill";
        //Sloth Acolyte
        public const string FLOOR_SPIKES = "Floor Spikes";
        public const string DART = "Dart";
        public const string DART_TRAP = "Dart Trap";
        public const string ACID_DRIP = "Acid Drip";
        public const string ACID_NOZZLE = "Acid Nozzle";
        public const string REMOTE_MINE = "Remote Mine";
        public const string VAPOR_IMPLANT = "Vapor Implant";
        //Envy Acolyte
        public const string CONFUSION = "Confusion";
        public const string WEAK_KNEEES = "Weak Knees";
        public const string VENOM_FIST = "Venom Fist";
        public const string ABSORB = "Absorb";
        public const string MUTINY = "Mutiny";
        //Greed Acolyte
        public const string SOUL_REINFORCEMENT = "Soul Reinforcement";
        public const string HORDER = "Horder";
        public const string SPAWN_ITEM = "Drop Rate (+)";
        public const string THROW_ITEM = "Throw Item";
        public const string STEAL_ITEM = "Steal Item";
        //Wrath Acolyte
        public const string FLAME_HAMMER = "Flame Hammer";
        public const string GUSH = "Gush";
        public const string SOUL_CRUSH = "Soul Crush";
        public const string COMBUST = "Combust";
        public const string HORRIFY = "Horrify";
        //Gluttony Acolyte
        public const string FORGET_SKILL = "Forget Skill";
        //Lust Acolyte
        public const string REGEN_ALL = "Regen All";
        public const string SPEED_UP = "Speed (+) All";
        public const string ENVENOM = "Envenom";
        public const string MAGIC_MAP = "Magic Map";
        public const string COLD_SHOULDER = "Cold Shoulder";
        public const string CAVALRY = "Cavalry";
        //Pride Acolyte
        public const string STRENGTH_UP = "Strength (+)";
        public const string MANA_UP = "Mana (+)";
        public const string ELECTRIFY = "Electrify";
        public const string WALL_PUNCH = "Wall Punch";
        public const string MIMIC = "Mimic";
        public const string VALEDICTORIAN = "Valedictorian";

        public const string EXPLODE = "Explode";
        public const string VAPOR_CLOUD = "Vapor Cloud";

        //Wrath
        public const string DISMEMBERMENT = "Dismemberment";
        //Envy
        public const string HYPOTHERMIA = "Hypothermia";
        public const string ICE_SHARD = "Ice Shard";
        //Gluttony
        public const string PLAGUE = "Plague";
        public const string POISON_CLOUD = "Poison Cloud";
        //Sloth
        public const string SERPENT_SUPPER = "Serpent Supper";
        //Pride
        public const string BREAKING_WHEEL = "Breaking Wheel";
        //Greed
        public const string BOIL = "Boil";
        //Lust
        public const string BRIMSTONE = "Brimstone";
    }
    class SkillFactory
    {
        public static ISkill Create(string idSkill)
        {
            switch(idSkill)
            {
                case SkillId.ABSORB:return new AbsorbSkill();
                case SkillId.ACID_DRIP:return new AcidDripSkill();
                case SkillId.ACID_NOZZLE:return new AcidNozzleSkill();
                case SkillId.BRIMSTONE: return new BrimstoneSkill();
                case SkillId.BOIL: return new BoilSkill();
                case SkillId.BREAKING_WHEEL: return new BreakingWheelSkill();
                case SkillId.CAVALRY:return new CavalrySkill();
                case SkillId.COLD_SHOULDER: return new ColdShoulderSkill();
                case SkillId.COMBUST: return new CombustSkill();
                case SkillId.CONFUSION:return new ConfusionSkill();
                case SkillId.DART:return new DartSkill();
                case SkillId.DART_TRAP: return new DartTrapSkill();
                case SkillId.DISMEMBERMENT: return new DismembermentSkill();
                case SkillId.ELECTRIFY: return new ElectrifySkill();
                case SkillId.ENVENOM: return new EnvenomSkill();
                case SkillId.EXPLODE: return new ExplodeSkill();
                case SkillId.FIREBALL:return new FireballSkill();
                case SkillId.FLAME_HAMMER: return new FlameHammerSkill();
                case SkillId.FLOOR_SPIKES:return new FloorSpikesSkill();
                case SkillId.FORGET_SKILL: return new ForgetSkill();
                case SkillId.GUSH: return new GushSkill();
                case SkillId.HORDER: return new HorderSkill();
                case SkillId.HORRIFY: return new HorrifySkill();
                case SkillId.HYPOTHERMIA: return new HypothermiaSkill();
                case SkillId.ICE_SHARD: return new IceShardSkill();
                case SkillId.MAGIC_MAP: return new MagicMapSkill();
                case SkillId.MANA_UP: return new ManaUpSkill();
                case SkillId.MIMIC: return new MimicSkill();
                case SkillId.MUTINY: return new MutinySkill();
                case SkillId.NO_SKILL:return new NoSkill();
                case SkillId.PLAGUE: return new PlagueSkill();
                case SkillId.POISON_CLOUD: return new PoisonCloudSkill();
                case SkillId.REGEN_ALL: return new RegenAllSkill();
                case SkillId.REMOTE_MINE:return new RemoteMineSkill();
                case SkillId.SERPENT_SUPPER: return new SerpentSupperSkill();
                case SkillId.SOUL_CRUSH: return new SoulCrushSkill();
                case SkillId.SOUL_REINFORCEMENT: return new SoulReinforcementSkill();
                case SkillId.SPAWN_ITEM: return new SpawnItemSkill();
                case SkillId.SPEED_UP: return new SpeedUpSkill();
                case SkillId.STEAL_ITEM: return new StealItemSkill();
                case SkillId.STRENGTH_UP: return new StrengthUpSkill();
                case SkillId.THROW_ITEM: return new ThrowItemSkill();
                case SkillId.VALEDICTORIAN: return new ValedictorianSkill();
                case SkillId.VAPOR_IMPLANT:return new VaporImplantSkill();
                case SkillId.VENOM_FIST: return new VenomFistSkill();
                case SkillId.WALL_PUNCH:return new WallPunchSkill();
                case SkillId.WEAK_KNEEES:return new WeakKneesSkill();
                case SkillId.VAPOR_CLOUD: return new VaporCloudSkill();
                default:
                    throw new Exception("A SkillId to Skill mapping was not defined in the SkillFactory for ("+idSkill+"). This is 100% the fault of whoever wrote the code."); 
            }
        }

        private static Dictionary<int, List<string>> __elementMap = new Dictionary<int, List<string>>();
        private static Dictionary<string, int> __skillAnimationMap = new Dictionary<string,int>();

        private static void GenerateStaticSkillMaps()
        {
            if (__elementMap.Count() == 0)
            {
                ISkill skill;
                foreach (var skillId in SkillId.Values)
                {
                    if (skillId != SkillId.NO_SKILL)
                    {
                        skill = Create(skillId);
                        foreach (var elem in skill.GetElements())
                        {
                            if (!__elementMap.ContainsKey(elem))
                            {
                                __elementMap.Add(elem, new List<string>());
                            }
                            __elementMap[elem].Add(skillId);
                        }
                        __skillAnimationMap[skillId] = skill.GetAnimationType();
                    }
                }
                __skillAnimationMap.Add(SkillId.NO_SKILL, -1);
            }
        }

        private static int skillPick = 0;
        private static List<string> InvalidRandomSkills = new List<string>() 
        { 
            SkillId.BRIMSTONE,
            SkillId.BOIL,
            SkillId.SERPENT_SUPPER,
            SkillId.BREAKING_WHEEL,
            SkillId.DISMEMBERMENT,
            SkillId.HYPOTHERMIA,
            SkillId.PLAGUE
        };
        public static string GetElementalSkill(int elementId)
        {
            GenerateStaticSkillMaps();
            while (InvalidRandomSkills.Contains(SkillId.Values[skillPick]))
            {
                skillPick = RNG.Rand.Next(0, __elementMap[elementId].Count());
            }
            return __elementMap[elementId][skillPick];
        }

        public static bool IsSkill(string skillId,int animationType)
        {
            GenerateStaticSkillMaps();
            return __skillAnimationMap[skillId] == animationType;
        }

        public static SkillBehavior Create(int animation,int skillGraphic,ISkill parentSkill)
        {
            switch (animation)
            {
                case AnimationType.CLOUD:
                    return new CloudBehavior(skillGraphic, parentSkill);
                case AnimationType.RANGED:
                    return new RangedBehavior(skillGraphic, parentSkill);
                case AnimationType.SELF:
                    return new SelfBehavior(skillGraphic, parentSkill);
                case AnimationType.STATIONARY:
                    return new StationaryBehavior(skillGraphic, parentSkill);
                case AnimationType.ROTATE:
                    return new RotateBehavior(skillGraphic, parentSkill);
                default:
                    throw new Exception("How dare you create a new Anim type for skills without defining a proper behavior for them!");
            }
        }

        public static SkillAnimation Create(int animation)
        {
            switch (animation)
            {
                case AnimationType.RANGED:
                    return new RangedAnimation();
                case AnimationType.SELF:
                    return new SelfAnimation();
                case AnimationType.ROTATE:
                    return new RotateAnimation();
                default:
                    return new NoAnimation();
            }
        }

        public static Dictionary<int, List<string>> __actorToSkillMapping = new Dictionary<int, List<string>>();
        public static ICollection<string> GetElementalSkills(int actorType, List<int> _elementalComposition)
        {
            if (!__actorToSkillMapping.ContainsKey(actorType))
            {
                __actorToSkillMapping.Add(actorType,new List<string>());
                foreach (var elem in _elementalComposition)
                {
                    __actorToSkillMapping[actorType].Add(SkillFactory.GetElementalSkill(elem));
                }
            }
            return __actorToSkillMapping[actorType];
        }

        internal static float GetCost(string skillId)
        {
            return Create(skillId).GetCost();
        }
    }
}