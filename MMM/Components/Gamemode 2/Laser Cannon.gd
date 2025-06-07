extends Node2D

var timer
var timePassed
var reloadTimer
var countDown
var reload
var rng
var xMulti
var xOffset
var laserTarget
var string
var stringActual

# Called when the node enters the scene tree for the first time.
func _ready():
	timer = $Timer
	reloadTimer = $Timer2
	timePassed = 0
	_cannon_timer()
	timer.start(1)
	rng = RandomNumberGenerator.new()


# Called every frame. 'delta' is the elapsed time since the previous frame.
func _physics_process(delta):
	timePassed += delta
	$"../Label".set_text(str(int(timePassed)))
	
	
func _cannon_timer():
	if timePassed < 20:
		countDown = 5
	elif timePassed < 40:
		countDown = 2.5
	else:
		countDown = 1



func _on_timer_timeout():
	$RayCast2D._fucntion_reload()
	xMulti = rng.randi_range(-3,3)
	xOffset = rng.randi_range(-5,5) * 54
	laserTarget = Vector2(648, (xMulti*(-648-xOffset)))
	if -1*xOffset < 0:
		string = "Y = %dX - %d"
		stringActual = string % [xMulti, xOffset/54]
	elif -1*xOffset > 0:
		string = "Y = %dX + %d"
		stringActual = string % [xMulti, -1*xOffset/54]
	else:
		string = "Y = %dX"
		stringActual = string % xMulti
	$"../Equation".set_text(stringActual)
	reloadTimer.start(2)


func _on_timer_2_timeout():
	_cannon_timer()
	position.x = 252
	position.y = 324 + (324 - xOffset) * xMulti
	$RayCast2D._function_shoot()
	timer.start(countDown)
