extends Node3D

@onready var death_label = $DeathLabel/DLabel  # Label inside the CanvasLayer
@onready var player = get_node("Player")  # Player node
@onready var settings_menu = $SettingsMenu  # Settings menu UI
@onready var resume_button = $SettingsMenu/Panel/Resume
@onready var exit_button = $SettingsMenu/Panel/ExitGame

func _ready() -> void:
	death_label.visible = false
	settings_menu.visible = false

	# Connect settings buttons
	resume_button.pressed.connect(_on_resume_pressed)
	exit_button.pressed.connect(_on_exit_pressed)

func _process(delta: float) -> void:
	# Toggle settings menu with Esc key
	if Input.is_action_just_pressed("ui_cancel"):
		toggle_settings_menu()

	# Boundary check (X and Z)
	if abs(player.global_transform.origin.x) > 50 or abs(player.global_transform.origin.z) > 50:
		print("Player has crossed the boundary.")
		show_death_label()
		reset_player_position()

	# Fall check (Y-axis)
	if player.global_transform.origin.y < -10:
		print("Player has fallen off.")
		show_death_label()
		reset_player_position()

func show_death_label():
	death_label.text = "You Died"
	death_label.visible = true

func reset_player_position():
	player.global_transform.origin = Vector3(0, 1, 0)
	player.linear_velocity = Vector3.ZERO

func toggle_settings_menu():
	settings_menu.visible = not settings_menu.visible
	Input.set_mouse_mode(Input.MOUSE_MODE_VISIBLE if settings_menu.visible else Input.MOUSE_MODE_CAPTURED)

func _on_resume_pressed():
	settings_menu.visible = false
	Input.set_mouse_mode(Input.MOUSE_MODE_CAPTURED)

func _on_exit_pressed():
	get_tree().quit()
	
