extends Area2D

#onready var animation = $AnimationPlayer


var speed: int = 1000
var direction = Vector2.RIGHT

#func _process(delta):
	#position += direction * delta * speed
