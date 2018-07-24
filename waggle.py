import os
import RPi.GPIO as GPIO
import DHT22
import pymysql as p
import time
import datetime
import pigpio

os.chdir("/home/pi/Desktop/pigpio_dht22")



# set fan and coil
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
FAN_PIN = 4
COIL_PIN = 14
GPIO.setup(FAN_PIN, GPIO.OUT)
GPIO.setup(COIL_PIN, GPIO.OUT)

# use temp/hum sensor
pi = pigpio.pi()
s = DHT22.sensor(pi, 27)

# initialize variables
waggle_id = "\"waggle1\""
remaining_battery = 0
voltage = 0
charging = "\"Y\""
temperature = 0
humidity = 0
heater = "\"N\""
fan = "\"N\""
#updated_time = "now()"
notification = "\"dont know\""
number = 0


while True :
        # use MySQL to save datapoints
    conn = p.connect(host = "localhost",
                   port = 3306,
                   user = "root",
                   passwd = "abc1234",
                   db = "waggle" )

    cur = conn.cursor()

    s.trigger()
    time.sleep(.1)
    temperature = (s.temperature() / 1.)
    temp_f = temperature * 1.8 + 32
    humidity = (s.humidity() / 1.)
    print('humidity : {:3.2f}'.format(humidity))
    print('temperature in celsius : {:3.2f}'.format(temperature))
    print('temperature in fahrenheit : {:3.2f}\n'.format(temp_f))
    

    if temperature >= 35 :
        print("The temperature is too high")
        print("Turning on fan") # = fan
        GPIO.output(FAN_PIN, False)
        fan = "\"Y\""
        print("Turning off coil")
        GPIO.output(COIL_PIN, True)
        heater = "\"N\""
        
    elif temperature >= 0 and temperature < 35 :
        print("The temperature is okay")
        print("Turning off fan and coil")
        GPIO.output(COIL_PIN, True)
        fan = "\"N\""
        GPIO.output(FAN_PIN, True)
        heater = "\"Y\""
        
    else : # temp_c < 0
        print("The temperature is too low")
        print("Turning on coil")
        GPIO.output(COIL_PIN, False)
        heater = "\"Y\""
        print("Turning off fan")
        GPIO.output(FAN_PIN, True)
        fan = "\"N\""
    
    state = "INSERT INTO battery_specs VALUES (" + waggle_id + "," + str(remaining_battery) +\
    "," + str(voltage) + "," + charging + "," + str(temperature) + "," + str(humidity) +\
    "," + heater + "," + fan + "," + notification + "," + str(++number) + ", localtime());"
    cur.execute(state)
    conn.commit()
    time.sleep(60)
    cur.close()
    conn.close()

