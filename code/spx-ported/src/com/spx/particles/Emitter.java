package com.spx.particles;import com.spx.core.Point2;import com.spx.entities.IEntity;import com.xna.wrapper.Color;class Emitter extends PEComponent {	private Particle2[] _particles = new Particle2[100];	private int _index = 0;	ParticleBehavior _behavior;	public Emitter() {	}	public void Update() {		if (IsActive) {			IsActive = false;			for (int ii = 0; ii < _index; ii++) {				if (_particles[ii].IsActive) {					IsActive = true;					_particles[ii].Update();				}			}		}	}	public void Draw() {		if (IsActive) {			for (int ii = 0; ii < _index; ii++) {				_particles[ii].Draw();			}		}	}	public void Reset(ParticleBehavior behavior, Point2 position,			Color baseColor) {		IsActive = true;		_behavior = behavior;		_index = 0;		while (_index < behavior.GetParticleCount()) {			_particles[_index] = ParticleEngine.CreateParticle(behavior,					position, baseColor);			_index++;		}	}	public void Reset(ParticleBehavior behavior, IEntity entity, Color baseColor) {		IsActive = true;		_behavior = behavior;		_index = 0;		while (_index < behavior.GetParticleCount()) {			_particles[_index] = ParticleEngine.CreateParticle(behavior,					entity, baseColor);			_index++;		}	}}