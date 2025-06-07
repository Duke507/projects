from socket import *
import json
import datetime

def gettime():
    return (datetime.datetime.now() - datetime.datetime.now().replace(hour=0,minute=0,second=0,microsecond=0)).seconds

#rrTable is an array of dictionaries
rrTable = [
    {
        "pos": 1,
        "name": "www.csusm.edu",
        "type": "A",
        "value": "144.37.5.45",
        "ttl": None,
        "static": 1
    },
    {
        "pos": 2,
        "name": "cc.csusm.edu",
        "type": "A",
        "value": "144.37.5.117",
        "ttl": None,
        "static": 1
    },{
        "pos": 3,
        "name": "cc1.csusm.edu",
        "type": "CNAME",
        "value": "cc.csusm.edu",
        "ttl": None,
        "static": 1
    },{
        "pos": 4,
        "name": "cc1.csusm.edu",
        "type": "A",
        "value": "155.37.5.118",
        "ttl": None,
        "static": 1
    },{
        "pos": 5,
        "name": "my.csusm.edu",
        "type": "A",
        "value": "144.37.5.150",
        "ttl": None,
        "static": 1
    },{
        "pos": 6,
        "name": "qualcomm.com",
        "type": "NS",
        "value": "dns.qualcomm.com",
        "ttl": None,
        "static": 1
    },{
        "pos": 7,
        "name": "viasat.com",
        "type": "NS",
        "value": "dns.viasat.com",
        "ttl": None,
        "static": 1
    }
]
serverTransactionID = 0
serverPort = 15000
serverSocket = socket(AF_INET, SOCK_DGRAM)
serverSocket.bind(('', serverPort))
print ('The server is ready to receive')
while True:
    appendBool = True
    found = False
    messageFromClient, clientAddress = serverSocket.recvfrom(2048)#recieves message from client
    messageFromClient.decode()#decodes client message
    messageFromClient = json.loads(messageFromClient)#changes message to dictionary
    
    responseMessage = messageFromClient

    print("Local ==> The client with IP address " + clientAddress[0] + " sent an " + messageFromClient["type"] + " request for hostname " + messageFromClient["name"])
    #Local ==> The client with IP address
    print ("{:<20} {:<30} {:<20} {:<20} {:<6} {:<1}" .format("position", "name", "type", "value", "ttl", "static"))
    for x in rrTable:
        if x["ttl"] == None:
            ttl = 86401
            print ("{:<20} {:<30} {:<20} {:<20} {:<6} {:<1}" .format(x["pos"], x["name"], x["type"], x["value"], "", x["static"]))
        else:
            ttl = x["ttl"]
            print ("{:<20} {:<30} {:<20} {:<20} {:<6} {:<1}" .format(x["pos"], x["name"], x["type"], x["value"], ttl, x["static"]))

        if x["name"] == messageFromClient["name"] and messageFromClient["type"] == x["type"] and ttl > gettime():
            responseMessage["value"] = x["value"]
            responseMessage["valuelength"] = len(x["value"])
            found = True
        
    #if messageFromClient["type"] == "CNAME":
    #    for x in rrTable:
    #        if responseMessage["value"] == x["name"]:
    #            responseMessage["value"] = x["value"]
    #            responseMessage["value"] = len(x["value"])
#qualcommm will send back info and make it into a rr format
    if found == False:
        notFound = messageFromClient["name"].split(".")
        domainName = notFound[1] + "." + notFound[2]
        VSPort = 0

        if domainName == "qualcomm.com":
            VSName = "localhost"
            VSPort = 21000
            print("\nLocal ==> unable to find " + messageFromClient["name"] + ", now asking qualcomm.com for request")

        if domainName == "viasat.com":
            VSName = "localhost"
            VSPort = 22000
            print("\nLocal ==> unable to find " + messageFromClient["name"] + ", now asking viasat.com for request")

        if VSPort == 21000 or VSPort == 22000:

            VSRequest = {"transactionID": serverTransactionID, "qr": 0, "type": messageFromClient["type"],
                         "valueLength": "",
                         "name": messageFromClient["name"], "value": ""}
            askVSServer = json.dumps(VSRequest)
            serverSocket.sendto(askVSServer.encode(), (VSName, VSPort))
            VSResponse, VSAddress = serverSocket.recvfrom(2048)
            VSResponse = json.loads(VSResponse.decode())
            responseMessage = VSResponse

        if VSPort == 0:
            print("\nLocal ==> Server is unable to find request")
            responseMessage["value"] = "NOTFOUND"

        for x in rrTable:
            if x["name"] == responseMessage["name"]:
                    appendBool = False

        if appendBool:
            toAppend = {"pos": len(rrTable) + 1, "name": responseMessage["name"], "type": responseMessage["type"], "value": responseMessage["value"], "ttl": gettime()+60, "static": 0}
            rrTable.append(toAppend)



    #if found is true the local server will send a response back to client
    if responseMessage["value"] != "NOTFOUND":
        print("\nLocal ==> responding to client with transaction id " + str(messageFromClient["transactionID"]))
        responseMessage["transactionID"] = messageFromClient["transactionID"]
    responseMessage["qr"] = 1
    responseMessage = json.dumps(responseMessage)
    serverSocket.sendto(responseMessage.encode(), clientAddress)
    serverTransactionID += 1
        #Local ==> a CNAME record for hosname cc1.csusm.edu was not found


    #serverSocket.sendto(modifiedMessage.encode(), clientAddress)