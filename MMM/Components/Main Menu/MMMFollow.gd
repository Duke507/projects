extends PathFollow2D

var counter = 0
var backwards: bool = false
func _process(delta):
	counter += delta
	if counter > 0.01:
		progress += 1
		counter = 0
