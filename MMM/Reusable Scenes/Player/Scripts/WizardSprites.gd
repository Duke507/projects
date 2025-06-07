extends Node2D

# Made by victor, script for character customization, don't change.
# 
# Texture variables given in drawing order.
var skin_texture: Texture2D
var hat_texture: Texture2D
var facialhair_texture: Texture2D
var shirt_texture: Texture2D

# Automatic call to load sprites on ready.
func _ready():
	load_sprites()
	
# Only called outside of main menu. Will read the cfg file PlayerData, which will always exist. 
func load_sprites():
	var cfg = ConfigFile.new()
	var err = cfg.load("user://PlayerData.cfg")
	if err != OK:
		printerr(err) # Shouldn't happen.
		return 
	
	skin_texture = load(cfg.get_value("CustomSprites", "skin"))
	hat_texture = load(cfg.get_value("CustomSprites", "hat"))
	facialhair_texture = load(cfg.get_value("CustomSprites", "facialhair"))
	shirt_texture = load(cfg.get_value("CustomSprites", "shirt"))
	update_sprites()
		
# Just a call to update the sprites. Should be called by the customizer or the main menu.
func update_sprites():
	$Skin.texture = skin_texture
	$Hat.texture = hat_texture
	$"Facial Hair".texture = facialhair_texture
	$Shirt.texture = shirt_texture
