import socket
from Catalog import Catalog
from Customer import Customer
from Item import Item
from Order import Order
import copy
import Server
from threading import Thread, Lock


class ClientWorker(Thread):

    def __init__(self, server: Server, socket, address, lock: Lock):
        super().__init__()
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

    # def get_customer_from_name(self, name):
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

    def send_message(self, str_to_send):
        self.__client_socket.send(len(str_to_send.encode("UTF-8")).to_bytes(2, byteorder='big'))
        self.__client_socket.send(str_to_send.encode("UTF-8"))

    def run(self):
        self.__keep_running = True
        welcome = "Connected to Python Echo Server".encode("UTF-8")
        self.__client_socket.send(len(welcome).to_bytes(2, byteorder='big'))
        self.__client_socket.send(welcome)
        while self.__keep_running:
            client_message = self.__client_socket.recv(1024)[2:].decode("UTF-8")
            print(client_message)
            client_message = client_message.strip()
            args = client_message.split("|")
            print(args[0])
            self.__lock.acquire()
            if "QUIT" == client_message:
                print("QUIT\n")
                self.__keep_running = False
                #self.__client_socket.send("OK".encode("UTF-8"))
            elif "TERMINATE" == client_message:
                print("TERMINATE\n")
                self.__keep_running = False
                #self.__client_socket.send("OK".encode("UTF-8"))
                self.__server.terminate()
            # elif not args[0].isnumeric():
            #     print("NON-NUMERIC")
            #     self.send_message("Errored Input")
            elif int(args[0]) == 0:
                new_customer = Customer(args[1], args[2], args[3])
                self.__server.add_customer_to_customer_list(new_customer)
                self.send_message("OK")
            elif int(args[0]) == 1:
                check_out_customer = self.__server.get_customer_from_name(args[1])
                order = check_out_customer.checkout(self.__server.order_number_ticker)
                # self.__client_socket.send(order.order_number)
                self.send_message(str(order.order_number))
                self.__server.add_order_to_order_list(order)
            elif int(args[0]) == 2:
                ''' Adds a product to customer name, product/item id, quantity to customers shopping cart '''
                self.__server.customer_to_add_to_cart = self.__server.get_customer_from_name(args[1])
                # item_to_add = Item()
                item_to_add = copy.copy(self.__server.get_item_from_id(int(args[2])))
                print(str(item_to_add))
                item_to_add._quantity = (int(args[3]))
                self.__server.customer_to_add_to_cart.shopping_cart.add_item_to_cart(item_to_add)
                self.send_message("OK")
            elif int(args[0]) == 3:
                ''' Item name, item id, type, quantity, price, manufacturer, model; adding item to the catalog '''
                item_to_add_to_catalog = Item(args[1], int(args[2]), args[3], int(args[4]), float(args[5]), args[6], args[7])
                self.__server.catalog.add_item(item_to_add_to_catalog)
                self.send_message("OK")
            elif int(args[0]) == 4:
                ''' Prints the whole item catalog '''
                self.send_message(str(self.__server.catalog.get_catalog_size()))
                for item in self.__server.catalog.items:
                    self.send_message((str(item)))
            elif int(args[0]) == 5:
                ''' sends customer name and empties that customers cart '''
                self.__server.get_customer_from_name(args[1]).empty_shopping_cart()
                self.send_message("OK")
            elif int(args[0]) == 6:
                ''' sends customers total '''
                self.send_message(str(self.__server.get_customer_from_name(args[1]).shopping_cart.get_total()))
            elif int(args[0]) == 7:
                ''' takes customer name, product id, quantity, removes quantity of that item from their cart '''
                cart_to_remove_items = self.__server.get_customer_from_name(args[1]).shopping_cart.get_cart_list()
                for item_to_remove in cart_to_remove_items:
                    if item_to_remove.product_id == int(args[2]):
                        new_quantity = item_to_remove.quantity - int(args[3])
                        item_to_remove._quantity = new_quantity
                        self.send_message("OK")
            elif int(args[0]) == 8:
                ''' for tab 4 '''
                customer_to_get_cart = self.__server.get_customer_from_name(args[1])
                cart_size = len(customer_to_get_cart.shopping_cart.get_cart_list())
                self.send_message(str(cart_size))
                for item in customer_to_get_cart.shopping_cart.get_cart_list():
                    self.send_message(str(item))
            else:
                self.send_message("Unknown Command")
            self.__lock.release()
            #else:
                #client_socket.send(client_message.upper().encode("UTF-8"))
        self.__client_socket.close()

