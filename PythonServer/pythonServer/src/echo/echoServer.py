import socket

class Server:

    def __init__(self, ip:str, port:int, backlog:int):
        self.__ip = ip
        self.__port = port
        self.__backlog = backlog
        self.__keep_running = True

    def run(self):
        server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server_socket.bind( (self.__ip, self.__port) )
        server_socket.listen(self.__backlog)

        while self.__keep_running:
            print(f"""[SRV] Waiting for Client""")
            client_socket, client_address = server_socket.accept()
            print(f"""Got a connection from {client_address}""")

            client_socket.send("Connected to Python Echo Server".encode("UTF-16"))

            client_runs = True
            while client_runs:
                client_message = client_socket.recv(1024).decode("UTF-16")
                if client_message == "QUIT":
                    client_runs = False
                    client_socket.send("OK".encode("UTF-16"))
                elif client_message == "TERMINATE":
                    client_runs = False
                    self.__keep_running = False
                    client_socket.send("OK".encode("UTF-16"))
                else:
                    client_socket.send(client_message.upper().encode("UTF-16"))

            client_socket.close()

        server_socket.close()
