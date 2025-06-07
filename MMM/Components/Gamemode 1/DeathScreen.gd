extends Control

func _ready():
	hide()

# Connected to loss condition signal 
func _on_death():
	show()

func _on_pregame_start():
	hide()
