from socket import socket, AF_INET, SOCK_STREAM

serversocket = socket(AF_INET, SOCK_STREAM)
serversocket.bind(('localhost', 9999))
serversocket.listen(5)

while True:
    clientsocket, addr = serversocket.accept()
    print("Got a connection from {}".format(str(addr)))

    msg = "Thank you for connecting\r\n"

    clientsocket.send(msg.encode('ascii'))
    clientsocket.close()

serversocket.close()