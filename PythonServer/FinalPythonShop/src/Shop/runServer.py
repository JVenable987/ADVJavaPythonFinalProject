from Server import Server

if __name__ == "__main__":
    print("test")
    server = Server("localhost", 10000, 5)
    print("afterTest")
    server.run()
