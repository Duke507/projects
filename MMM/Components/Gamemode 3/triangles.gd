extends Polygon2D


signal body_on_triangle

var move_speed = 100  # Adjust the speed as needed
var direction = Vector2.RIGHT  # Initial movement direction

func _ready():
	set_process(true)  # Enable _process function

func _process(delta):
	# Move the triangle
	var movement = direction * move_speed * delta
	translate(movement)


func _on_area_2d_body_entered(body):
	body_on_triangle.emit(body)
	queue_free()  # Delete the triangle when a body enters it
