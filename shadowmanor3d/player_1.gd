extends CharacterBody3D

signal player_interact

var SPEED = 5.0
const JUMP_VELOCITY = 4.5
var mouse_sensitivity := 1
var twist_input := 0
var pitch_input := 0
var in_air := false
var jumping := false

@onready var model = $Rogue
@onready var twist_pivot = $TwistPivot
@onready var pitch_pivot = $TwistPivot/PitchPivot
@onready var camera = $TwistPivot/PitchPivot/Camera3D

func _ready() -> void:
	Input.set_mouse_mode(Input.MOUSE_MODE_CAPTURED)

	
func _physics_process(delta: float) -> void:
	# Add the gravity.
	if not is_on_floor():
		velocity += get_gravity() * delta
		in_air = true
	else:
		in_air = false
	
	if Input.is_action_just_pressed("interact"):
		player_interact.emit()
	
	#Camera movement
	rotate_y(twist_input * delta)
	pitch_pivot.rotate_x(pitch_input * delta)
	pitch_pivot.rotation.x = clamp(pitch_pivot.rotation.x, deg_to_rad(-50), deg_to_rad(40))
	
	#reset relative mouse movement
	twist_input = 0.0
	pitch_input = 0.0
	
	# Handle jump.
	if Input.is_action_just_pressed("jump") and is_on_floor():
		velocity.y = JUMP_VELOCITY
		jumping = true
	else:
		jumping = false
	if Input.is_action_pressed("sprint"):
		SPEED = 8.0
	else: SPEED = 5.0

	# Get the input direction and handle the movement/deceleration.
	# As good practice, you should replace UI actions with custom gameplay actions.
	var input_dir := Input.get_vector("move_left", "move_right", "move_forward", "move_back")
	var direction := (transform.basis * Vector3(input_dir.x, 0, input_dir.y)).normalized()
	if direction:
		#if direction is backwards, set to walking speed
		if input_dir.y > 0:
			SPEED = 5.0
		velocity.x = direction.x * SPEED
		velocity.z = direction.z * SPEED
	else:
		velocity.x = move_toward(velocity.x, 0, SPEED)
		velocity.z = move_toward(velocity.z, 0, SPEED)
	
	model.play_animation(SPEED, input_dir, jumping, in_air)
	
	move_and_slide()

	
func _unhandled_input(event: InputEvent) -> void:
	if event is InputEventMouseMotion and Input.get_mouse_mode() == Input.MOUSE_MODE_CAPTURED:
		twist_input = -event.relative.x * mouse_sensitivity
		pitch_input = -event.relative.y * mouse_sensitivity
		
