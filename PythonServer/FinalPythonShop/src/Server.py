import socket
import Catalog
import Customer
import Item
import Order
import OrderLine


class Server:

    def __init__(self, ip:str, port:int, backlog:int):
        self.__ip = ip
        self.__port = port
        self.__backlog = backlog
        self.__keep_running = True
        self.catalog = Catalog()
        self.customer_list = []
        self.order_list = []
        self.order_number = 0

    def add_customer_to_customer_list(self, customer):
        self.customer_list.add(customer);

    def add_order_to_order_list(self, order):
        self.customer_list.add(order)
        for item in order.lines:
            quantity_to_remove_from_inventory = item.quantity()
            item_name_to_remove = item.product_id()
            for catalog_item in self.catalog.items():
                if item_name_to_remove == catalog_item.product_id():
                    catalog_item.quantity(catalog_item.quantity - quantity_to_remove_from_inventory)

    def get_customer_from_name(self, name):
        for customer in self.customer_list:
            if customer.username == name:
                return customer

    def get_item_from_id(self, id):
        for item in self.catalog.items:
            if item.product_id == id:
                return item


    def order_number_ticker(self, ):
        this_order_number = self.order_number
        self.order_number = self.order_number + 1
        return this_order_number

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
                args = client_message.split("|")
                if args[0] == 0:
                    new_customer = Customer(args[1], args[2], args[3])
                    self.add_customer_to_customer_list(new_customer)
                elif args[0] == 1:
                    check_out_customer = self.get_customer_from_name(args[1])
                    order = check_out_customer.checkout(self.order_number_ticker)
                    client_socket.send(order.order_number)
                    self.add_order_to_order_list(order)
                elif args[0] == 2:
                    customer_to_add_to_cart = self.get_customer_from_name(args[1])
                    item_to_add = Item()
                    item_to_add = self.get_item_from_id(args[2])
                    item_to_add.quantity(args[3])
                    customer_to_add_to_cart.shopping_cart.add_item_to_cart(item_to_add)



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
