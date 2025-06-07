extends Node2D

var speed: int = 1000
var direction = Vector2.RIGHT
var original_scale: Vector2
var current_scale: Vector2
var target_scale: Vector2
var scale_increment: float = 0.1
var sprite_bottom_y: float

func _ready():
	original_scale = $Sprite2D.scale
	current_scale = original_scale
	target_scale = original_scale * 3
	sprite_bottom_y = $Sprite2D.global_position.y + ($Sprite2D.texture.get_height() / 2)

func _process(_delta):
	current_scale.y += scale_increment
	$Sprite2D.scale = current_scale

	# Calculate the new position for the bottom of the sprite
	var new_sprite_bottom_y = sprite_bottom_y - ($Sprite2D.texture.get_height() / 2) * (current_scale.y - original_scale.y)

	# Calculate the difference in position and move the sprite by that difference
	var position_diff = new_sprite_bottom_y - $Sprite2D.global_position.y
	#$Sprite2D.global_position.y += position_diff

	if current_scale.y >= target_scale.y:
		queue_free()
