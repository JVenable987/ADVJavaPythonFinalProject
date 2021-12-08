from Admin import Administrator

if __name__ == "__main__":
    admin = Administrator("localhost", 10000)

    admin.connect()

    server_message = admin.receive_message()
    print(f"""[CLI] SRV -> {server_message}""")

    client_runs = True
    while client_runs:
        # print("Do you want to: ")
        # print("1. Create new item")
        # print("2. Add quantity to item")
        # print("3. Change item's price")

        msg = input("Message to send: ")
        admin.send_message(msg)

        server_message = admin.receive_message()
        print(f"""[CLI] SRV -> {server_message}""")

        if msg == "QUIT" or msg == "TERMINATE":
            client_runs = False

