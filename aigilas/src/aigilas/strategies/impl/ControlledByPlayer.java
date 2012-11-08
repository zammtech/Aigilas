package aigilas.strategies.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.Stats;
import aigilas.creatures.impl.Player;
import aigilas.management.Common;
import aigilas.strategies.BaseStrategy;
import aigilas.strategies.Strategy;
import sps.bridge.ActorTypes;
import sps.bridge.Command;
import sps.bridge.Commands;
import sps.bridge.Contexts;
import sps.core.Core;
import sps.core.Point2;
import sps.core.Settings;
import sps.entities.Entity;
import sps.entities.EntityManager;
import sps.io.Input;

import java.util.Arrays;
import java.util.List;

public class ControlledByPlayer extends BaseStrategy {
    private boolean _isCasting = false;
    private final Point2 _keyVelocity = new Point2(0, 0);

    private static final List<Command> __hotkeys = Arrays.asList(Commands.get(Common.Hot_Skill_1), Commands.get(Common.Hot_Skill_2), Commands.get(Common.Hot_Skill_3));

    public ControlledByPlayer(BaseCreature parent) {
        super(parent, Strategy.ControlledByPlayer);

        _targets.addTargetTypes(new sps.bridge.ActorType[]{ActorTypes.get(Core.Non_Player)});
    }

    @Override
    public void act() {
        if (Input.isActive(Commands.get(Common.Start), _parent.getPlayerIndex())) {
            _parent.setPlaying(true);
        }
        if (Input.isActive(Commands.get(Common.Back), _parent.getPlayerIndex())) {
            _parent.setPlaying(false);
        }
        if (_parent.isPlaying()) {
            if (!Input.isContext(Contexts.get(Common.Inventory), _parent.getPlayerIndex())) {
                float leftVelocity = (Input.isActive(Commands.get(Common.MoveLeft), _parent.getPlayerIndex()) ? -Stats.DefaultMoveDistance : 0);
                float rightVelocity = ((Input.isActive(Commands.get(Common.MoveRight), _parent.getPlayerIndex())) ? Stats.DefaultMoveDistance : 0);
                _keyVelocity.setX(rightVelocity + leftVelocity);

                float downVelocity = ((Input.isActive(Commands.get(Common.MoveDown), _parent.getPlayerIndex())) ? -Stats.DefaultMoveDistance : 0);
                float upVelocity = ((Input.isActive(Commands.get(Common.MoveUp), _parent.getPlayerIndex())) ? Stats.DefaultMoveDistance : 0);
                _keyVelocity.setY(upVelocity + downVelocity);

                if (Input.isContext(Contexts.get(Common.Free), _parent.getPlayerIndex())) {
                    boolean isPress = Input.isActive(Commands.get(Common.Confirm), _parent.getPlayerIndex());
                    if (!isPress) {
                        _parent.setInteraction(false);
                    }
                    if (isPress && !_parent.isInteracting()) {
                        _parent.setInteraction(true);
                    }
                    int skillCycleVelocity = ((Input.isActive(Commands.get(Common.CycleLeft), _parent.getPlayerIndex())) ? -1 : 0) + ((Input.isActive(Commands.get(Common.CycleRight), _parent.getPlayerIndex())) ? 1 : 0);
                    _parent.cycleActiveSkill(skillCycleVelocity);

                    if (!_isCasting) {
                        if (!Input.isActive(Commands.get(Common.Confirm), _parent.getPlayerIndex(), false)) {
                            _parent.moveIfPossible(_keyVelocity.X, _keyVelocity.Y);
                        }
                        if (!_keyVelocity.isZero()) {
                            _parent.setSkillVector(_keyVelocity);
                        }
                    }
                }
                if (Input.isActive(Commands.get(Common.Skill), _parent.getPlayerIndex())) {
                    _isCasting = true;
                }

                for (Command hotkey : __hotkeys) {
                    if (Input.isActive(hotkey, _parent.getPlayerIndex())) {
                        if (!Input.isActive(Commands.get(Common.LockSkill), _parent.getPlayerIndex(), false)) {
                            if (_parent.setHotSkillActive(hotkey)) {
                                _isCasting = true;
                            }
                        }
                        else {
                            _parent.markHotSkill(hotkey);
                        }
                    }
                }

                if (_isCasting) {
                    if (_parent.getSkillVector() == null) {
                        _parent.setSkillVector(new Point2(1, 0));
                    }
                    if (!_parent.getSkillVector().isZero()) {
                        _parent.useActiveSkill();
                    }
                    _isCasting = false;
                }
            }

            if (Input.isActive(Commands.get(Common.Inventory), _parent.getPlayerIndex())) {
                if (Settings.get().debugInventory) {
                    for (Entity player : EntityManager.get().getPlayers()) {
                        Player p = (Player) player;
                        if (p.getPlayerIndex() != _parent.getPlayerIndex()) {
                            p.toggleInventoryVisibility();
                        }
                    }
                }
                Input.setContext(_parent.toggleInventoryVisibility() ? Contexts.get(Common.Inventory) : Contexts.get(Common.Free), _parent.getPlayerIndex());
            }
        }
    }
}
