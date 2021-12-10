import socket
from Catalog import Catalog
from Customer import Customer
from Item import Item
from Order import Order
from ClientWorker import ClientWorker
import copy
from threading import Lock



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
        self.connectionList = []

    def add_customer_to_customer_list(self, customer):
        self.customer_list.append(customer)

    def add_order_to_order_list(self, order):
        self.order_list.append(order)
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


    def order_number_ticker(self):
        this_order_number = self.order_number
        self.order_number = self.order_number + 1
        return this_order_number

    def terminate(self):
        self.__keep_running = False

    def run(self):
        server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server_socket.bind( (self.__ip, self.__port) )
        server_socket.listen(self.__backlog)

        lock = Lock()
        while self.__keep_running:
            print(f"""[SRV] Waiting for Client""")
            client_socket, client_address = server_socket.accept()
            print(f"""Got a connection from {client_address}""")
            cw = ClientWorker(self, client_socket, client_address, lock)
            self.connectionList.append(cw)
            cw.start()

        print("Removing threads(if slowly)")
        for connection in self.connectionList:
            connection.terminate()
            connection.join()
        print("Shutting down Server")
        server_socket.close()
