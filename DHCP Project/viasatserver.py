from socket import *
import json

rr = [
    {
        "pos": 1,
        "name": "www.viasat.com",
        "type": "A",
        "value": "8.37.96.179",
        "ttl": "",
        "static": 1
    }
]

serverPort = 22000
serverSocket = socket(AF_INET, SOCK_DGRAM)
serverSocket.bind(('', serverPort))
print ('The server is ready to receive')
while True:
    flag = False
    message, clientAddress = serverSocket.recvfrom(2048)
    message.decode()
    message = json.loads(message)

    print("Viasat Local ==> received request from " + clientAddress[0])

    responseMessage = message
    print ("{:<20} {:<30} {:<20} {:<20} {:<6} {:<1}" .format("position", "name", "type", "value", "ttl", "static"))
    for x in rr:
        print ("{:<20} {:<30} {:<20} {:<20} {:<6} {:<1}" .format(x["pos"], x["name"], x["type"], x["value"], x["ttl"], x["static"]))
        if message["name"] == x["name"] and message["type"] == x["type"]:
            responseMessage["value"] = x["value"]
            responseMessage["valueLength"] = len(x["value"])
            flag = True
    responseMessage["qr"] = 1
    if flag == False:
        print("Viasat Local ==> the value was not found")
        responseMessage["value"] = "Viasat Local ==> ERROR 404 NOT FOUND"
    
    responseMessage = json.dumps(responseMessage)
    serverSocket.sendto(responseMessage.encode(), clientAddress)
    
