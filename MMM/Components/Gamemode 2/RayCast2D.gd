extends RayCast2D

var target
var timePassed
var tween
# Called when the node enters the scene tree for the first time.
func _ready():
	set_physics_process(false)
	add_exception($"../../StaticBody2D")
	timePassed = 0
	tween = create_tween()

func _function_shoot():
	_appear()
	set_physics_process(true)
	
func _fucntion_reload():
	_disappear()
	set_physics_process(false)
	
# Called every frame. 'delta' is the elapsed time since the previous frame.
func _physics_process(delta):
	target = $"..".laserTarget
	target_position = target
	$Line2D.points[1] = target_position
	force_raycast_update()
	
	if is_colliding():
		target_position = get_collision_point()

func _appear() -> void:
	tween.kill()
	tween.tween_property($Line2D, "width", 10.0, 0.1)
	tween.play()
func _disappear() -> void:
	tween.kill()
	tween.tween_property($Line2D, "width", 0, 0.1)
	tween.play()
