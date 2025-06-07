extends RigidBody2D

var waves_traversed: int = 0
var calculated_period_length: float = 0
signal lost
signal shift_waves

func _ready():
	gravity_scale = 0.4 # Default
	
func _physics_process(_delta):
	if calculated_period_length != 0: # Has been set by wave node.
		if global_position.x / calculated_period_length > waves_traversed:
			waves_traversed += 1
			if waves_traversed > 4:
				emit_signal("shift_waves")
	
	#print(linear_velocity)
	if linear_velocity.x < 0:
		emit_signal("lost")
		set_physics_process(false)
		return
		
	if Input.is_key_label_pressed(KEY_S): # TODO work w/ touchscreen
		var new_scale = gravity_scale
		new_scale += 0.2
		new_scale = clamp(new_scale, 0.4, 2)
		gravity_scale = new_scale
	else:
		gravity_scale = 0.4

const MAXSPEED = 1500
func _integrate_forces(state):
	
	var x_vel = clamp(linear_velocity.x, -MAXSPEED, MAXSPEED)
	var y_vel = clamp(linear_velocity.y, -MAXSPEED, MAXSPEED)
	
	
	linear_velocity = Vector2(x_vel, y_vel)
	
