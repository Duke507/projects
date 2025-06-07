from tkinter import *
from socket import *
import json
import datetime

def gettime():
    return (datetime.datetime.now() - datetime.datetime.now().replace(hour=0,minute=0,second=0,microsecond=0)).seconds

rr = []
transactionID = 0

serverName = 'localhost'
serverPort = 15000
clientSocket = socket(AF_INET, SOCK_DGRAM)

while True:
    flag = False
    appendBool = True
    message = input('Enter the host name/domain name: ')

    if message.upper() == "QUIT":
        break
    

    message2 = input('Enter the type of DNS query: ')

    print ("{:<20} {:<30} {:<20} {:<20} {:<6} {:<1}" .format("position", "name", "type", "value", "ttl", "static"))
    for x in rr:
        print ("{:<20} {:<30} {:<20} {:<20} {:<6} {:<1}" .format(x["pos"], x["name"], x["type"], x["value"], x["ttl"], x["static"]))
        if x["name"] == message and x["type"] == message2 and gettime() < x["ttl"]:
            print("\nClient ==> " + x["name"] + " met the conditions in client table")
            print("\nClient ==> The request value is " + x["value"])
            flag = True

    if flag == False:
        request = {"transactionID": transactionID, "qr": 0, "type": message2, "valueLength": "", "name": message, "value": ""}
        askServer = json.dumps(request)
        clientSocket.sendto(askServer.encode(),(serverName, serverPort))
        response, serverAddress = clientSocket.recvfrom(2048)

        response = response.decode()
        response = json.loads(response)
        if response["value"] == "NOTFOUND":
            print("\nClient ==> Server could not find requested item")
        else:
            
            for x in rr:
                if response["name"] == x["name"]:
                    x["ttl"] = gettime()+60
                    appendBool = False
            if appendBool:        
                toAppend = {"pos": len(rr), "name": response["name"], "type": response["type"], "value": response["value"], "ttl": gettime() + 60, "static": 0}
                rr.append(toAppend)
            transactionID += 1
            print("\nClient ==> The request value is " + response["value"])

clientSocket.close()
