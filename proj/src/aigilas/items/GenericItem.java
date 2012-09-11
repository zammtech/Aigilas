package aigilas.items;

import spx.bridge.ActorType;
import spx.bridge.DrawDepth;
import spx.bridge.EntityType;
import spx.core.Point2;
import spx.entities.Entity;
import spx.entities.EntityManager;
import spx.entities.IActor;
import aigilas.creatures.Player;
import aigilas.creatures.StatType;
import aigilas.creatures.Stats;

public class GenericItem extends Entity {
	public Stats Modifers;
	public String Name;
	private String _suffix;
	private String _prefix;
	private ItemName _type;
	private Slots _targetSlots;

	private static final String __spacingCharacter = " ";

	public ItemClass GetItemClass() {
		return _type.Category;
	}

	private void Initialize(String suffix, String prefix, ItemName type, Slots targetSlots, Stats modifiers, Point2 location) {
		Setup(location, type);
		_suffix = suffix;
		_prefix = prefix;
		_type = type;
		_targetSlots = type.Slots;
		Name = (_prefix == null ? "" : _prefix + __spacingCharacter) + _type.toString() + (_suffix == null ? "" : __spacingCharacter + _suffix);
		Modifers = new Stats(modifiers);
	}

	public GenericItem(GenericItem item, Point2 location) {
		Initialize(item._suffix, item._prefix, item._type, item._targetSlots, item.Modifers, location);
	}

	public GenericItem(Stats modifiers, String suffix, String prefix, ItemName type, Point2 location, boolean onGround) {
		if (type == null) {
			try {
				throw new Exception("Invalid type NULL passed into the GenericItem factory!");
			}
			catch (Exception e) {

				e.printStackTrace();
			}
		}
		Initialize(_suffix, _prefix, type, type.Slots, modifiers, location);
	}

	public GenericItem(Stats modifiers, String suffix, String prefix, ItemName type, Point2 location) {
		this(modifiers, suffix, prefix, type, location, true);
	}

	protected void Setup(Point2 location, ItemName type) {
		Initialize(location, type.Sprite, EntityType.ITEM, DrawDepth.Item);
	}

	private Player _currentTarget;

	@Override
	public void Update() {
		super.Update();
		if (_isOnBoard) {
			IActor collider = EntityManager.GetTouchingCreature(this);
			if (collider != null && collider.GetActorType() == ActorType.PLAYER) {
				_currentTarget = (Player) collider;
				if (_currentTarget != null) {
					if (_currentTarget.IsInteracting()) {
						_currentTarget.PickupItem(this);
					}
				}
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() == GenericItem.class) {
			GenericItem gI = (GenericItem) obj;
			if (Name != gI.Name) {
				return false;
			}
			for (StatType stat : StatType.values()) {
				if (Modifers.Get(stat) != gI.Modifers.Get(stat)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Name.hashCode() + Modifers.hashCode();
	}

	public float GetStatBonus(StatType stat) {
		return Modifers.Get(stat);
	}
}