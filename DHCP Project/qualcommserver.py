from socket import *
import json
rr = [
    {
        "pos": 0,
        "name": "www.qualcomm.com",
        "type": "A",
        "value": "104.86.224.205",
        "ttl": "",
        "static": 1
    },
    {
        "pos": 1,
        "name": "qtiack12.qti.qualcomm.com",
        "type": "A",
        "value": "129.46.100.21",
        "ttl": "",
        "static": 1
    }
]

serverPort = 21000
serverSocket = socket(AF_INET, SOCK_DGRAM)
serverSocket.bind(('', serverPort))
print ('The server is ready to receive')
while True:
    flag = False
    message, clientAddress = serverSocket.recvfrom(2048)
    message.decode()
    message = json.loads(message)

    print("Qualcomm Local ==> received request from " + clientAddress[0])
    print ("{:<20} {:<30} {:<20} {:<20} {:<6} {:<1}" .format("position", "name", "type", "value", "ttl", "static"))
    responseMessage = message
    for x in rr:
        print ("{:<20} {:<30} {:<20} {:<20} {:<6} {:<1}" .format(x["pos"], x["name"], x["type"], x["value"], x["ttl"], x["static"]))
        if message["name"] == x["name"] and message["type"] == x["type"]:
            responseMessage["value"] = x["value"]
            responseMessage["valueLength"] = len(x["value"])
            flag = True
    responseMessage["qr"] = 1
    if flag == False:
        print("Qualcomm Local ==> the value was not found")
    
    responseMessage = json.dumps(responseMessage)
    serverSocket.sendto(responseMessage.encode(), clientAddress)
