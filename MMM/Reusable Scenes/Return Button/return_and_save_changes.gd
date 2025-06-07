extends Button
signal save_changes

# Return and save changes is used only by the character customizer. 
# Only difference is that it calls to update user choices before switching back to main menu.
func _on_toggled(button_pressed):
	emit_signal("save_changes")
	get_tree().change_scene_to_file("res://Components/Main Menu/main_menu.tscn")
