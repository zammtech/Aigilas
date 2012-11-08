package aigilas.entities;

import aigilas.creatures.impl.Player;
import aigilas.gods.God;
import aigilas.gods.GodId;
import aigilas.items.GenericItem;
import aigilas.management.Common;
import sps.bridge.DrawDepths;
import sps.bridge.EntityTypes;
import sps.bridge.SpriteTypes;
import sps.core.Point2;
import sps.entities.Entity;
import sps.entities.EntityManager;
import sps.text.Text;
import sps.text.TextPool;

import java.util.List;

public class Altar extends Entity {
    private final God _god;
    private Player _currentTarget;
    private List<Entity> _offerings;
    private Text text;

    public Altar(Point2 location, GodId godName) {
        _god = godName.getInstance();
        _graphic.setColor(_god.getColor());
        initialize(location, SpriteTypes.get(Common.Altar), EntityTypes.get(Common.Altar), DrawDepths.get(Common.Altar));
    }

    @Override
    public void update() {
        _currentTarget = (Player) EntityManager.get().getTouchingCreature(this);
        if (_currentTarget != null) {
            if (_currentTarget.isInteracting()) {
                _currentTarget.pray(_god);
            }
            _offerings = EntityManager.get().getEntities(EntityTypes.get(Common.Item), _location);
            for (Entity offering : _offerings) {
                _currentTarget.sacrifice(_god, (GenericItem) offering);
            }
            if (text == null || !text.isVisible()) {
                text = TextPool.get().write(_god.NameText, getLocation());
            }
        }
        else {
            if (text != null) {
                text.hide();
                text = null;
            }
        }
    }

    public God getGod() {
        return _god;
    }
}
