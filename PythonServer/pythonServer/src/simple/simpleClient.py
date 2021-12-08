import socket

client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

client_socket.connect( ('localhost', 9999) )

msg = client_socket.recv(1024)

client_socket.close()

print(msg.decode('ascii'))
