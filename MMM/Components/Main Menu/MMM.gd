extends Label

var counter = 0
func _process(delta):
	counter += delta
	
	if counter > 0.5:
		do_something_wacky(randi_range(0,2))
		counter = 0
	

func do_something_wacky(letter):
	var prob = randf()
	if prob <= 0.15:
		cycle_letter(letter)
	elif prob <= 0.17:
		flip()
	elif prob <= 0.18:
		change()
	elif prob <= 0.22:
		math()
		
		
const chars = "&!@#$%/+-&*="
func cycle_letter(letter):
	var timer = Timer.new()
	add_child(timer)
	for i in randi_range(3,12):
		timer.start(0.05)
		await(timer.timeout)
		text[letter] = chars[randi_range(0,chars.length() - 1)]
	text[letter] = "M"
	timer.queue_free()

func flip():
	var timer = Timer.new()
	add_child(timer)
	
	get_parent().rotation = PI
	timer.start(0.2)
	
	
	await(timer.timeout)
	get_parent().rotation = 0
	timer.queue_free()

const strings = ["HI!", "BOO", "CAT", "DOG", "ADD", "LOL", "WOW", "BRB", "POW", "ZAP", "AH!", "BOP", "POP", "HA!", "REE", "OOF"] # MUST BE 3 LETTERS
func change():
	var timer = Timer.new()
	add_child(timer)
	
	text = strings.pick_random()
	timer.start(0.4)
	
	await(timer.timeout)
	text = "MMM"

	timer.queue_free()

const ops = ["+","-","*","/"]
func math():
	var timer = Timer.new()
	add_child(timer)
	
	var lhs = str(randi_range(0,9))
	var rhs = str(randi_range(0,9))

	text = lhs + ops.pick_random() + rhs
	timer.start(0.4)
	
	await(timer.timeout)
	text = "MMM"

	timer.queue_free()

func _change_color():
	modulate = Color(randf(),randf(),randf(),1)
	$"../ColorChangeButton/ColorTimer".start(3)
	


func _on_color_timer_timeout():
	modulate = Color.WHITE
