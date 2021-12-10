import socket
from Catalog import Catalog
from Customer import Customer
from Item import Item
from Order import Order
import copy
from Server import Server
from threading import Thread, Lock


class ClientWorker(Thread):

    def __init__(self, server:Server, socket, address, lock:Lock):
        super.__init__()
        self.__server = server
        self.__client_socket = socket
        self.__address = address
        self.__keep_running = False
        self.__lock = lock
        #self.__catalog = server.catalog
        #self.order_list = []
        #self.order_number = 0

    #def add_customer_to_customer_list(self, customer):
    #    self.customer_list.append(customer);

    #def add_order_to_order_list(self, order):
    #    self.order_list.append(order)
    #    for item in order.lines:
    #        quantity_to_remove_from_inventory = item.quantity()
    #        item_name_to_remove = item.product_id()
    #        for catalog_item in self.catalog.items():
    #            if item_name_to_remove == catalog_item.product_id():
    #                catalog_item.quantity(catalog_item.quantity - quantity_to_remove_from_inventory)

    #def get_customer_from_name(self, name):
    #    for customer in self.customer_list:
    #        if customer.username == name:
    #            return customer

    #def get_item_from_id(self, id):
    #    for item in self.catalog.items:
    #        if item.product_id == id:
    #            return item


    #def order_number_ticker(self):
    #    this_order_number = self.order_number
    #    self.order_number = self.order_number + 1
    #    return this_order_number

    def terminate(self):
        self.__keep_running = False

    def run(self):
        self.__keep_running = True
        while self.__keep_running:
            client_message = self.__client_socket.recv(1024).decode("UTF-16")
            print(client_message)
            args = client_message.split("|")
            print(args[0])
            self.__lock.acquire()
            if int(args[0]) == 0:
                new_customer = Customer(args[1], args[2], args[3])
                self.__server.add_customer_to_customer_list(new_customer)
                self.__client_socket.send("customer added".encode("UTF-16"))
            elif int(args[0]) == 1:
                check_out_customer = self.__server.get_customer_from_name(args[1])
                order = check_out_customer.checkout(self.__server.order_number_ticker)
                self.__client_socket.send(order.order_number)
                self.__server.add_order_to_order_list(order)
            elif int(args[0]) == 2:
                self.__server.customer_to_add_to_cart = self.__server.get_customer_from_name(args[1])
                #item_to_add = Item()
                item_to_add = copy.copy(self.__server.get_item_from_id(int(args[2])))
                print(str(item_to_add))
                item_to_add._quantity = (int(args[3]))
                self.__server.customer_to_add_to_cart.shopping_cart.add_item_to_cart(item_to_add)
                self.client_socket.send("item added to cart".encode("UTF-16"))
            elif int(args[0]) == 3:
                item_to_add_to_catalog = Item(args[1], int(args[2]), args[3], int(args[4]), float(args[5]), args[6], args[7])
                self.__server.catalog.add_item(item_to_add_to_catalog)
                self.client_socket.send("item added".encode("UTF-16"))
            elif int(args[0]) == 4:
                for item in self.__server.catalog.items:
                    self.client_socket.send(str(item).encode("UTF-16"))
            elif int(args[0]) == 5:
                self.__server.get_customer_from_name(args[1]).empty_shopping_cart()
                self.client_socket.send("cart emptied".encode("UTF-16"))
            elif int(args[0]) == 6:
                self.client_socket.send(str(self.get_customer_from_name(args[1]).shopping_cart.get_total())
                                        .encode("UTF-16"))
            elif int(args[0]) ==7:
                cart_to_remove_items = self.__server.get_customer_from_name(args[1]).shopping_cart.get_cart_list();
                for item_to_remove in cart_to_remove_items:
                    if item_to_remove.product_id == int(args[2]):
                        new_quantity = item_to_remove.quantity - int(args[3])
                        item_to_remove._quantity = new_quantity
                        self.__client_socket.send("items removed".encode("UTF-16"))

            if client_message == "QUIT":
                self.__keep_running = False
                self.__client_socket.send("OK".encode("UTF-16"))
            elif client_message == "TERMINATE":
                self.__keep_running = False
                self.__client_socket.send("OK".encode("UTF-16"))
                self.__server.terminate()
            self.__lock.release()
            #else:
                #client_socket.send(client_message.upper().encode("UTF-16"))
        self.__client_socket.close()

