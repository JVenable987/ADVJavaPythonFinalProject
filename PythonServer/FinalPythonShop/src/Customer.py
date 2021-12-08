# class Customer, interacts with the ShoppingCart, add items to his/her ShoppingCart

import ShoppingCart

class Customer:
    #userName = ""
    #password = ""
    #isStocker = False
    #private ShoppingCart shoppingCart;

    # Customer constructor
    def __init__(self, userName:str, password:str, isStocker:bool, shoppingCart:ShoppingCart):
        self.userName = userName
        self.password = password
        self.isStocker = isStocker
        self.shoppingCart = shoppingCart

    def getUserName(self):
        return self.userName

    def setUserName(self, userName:str):
        self.userName = userName

    def getPassword(self):
        return self.password

    def setPassword(self, password:str):
        self.password = password

    def getIsStocker(self):
        return self.isStocker

    def setIsStocker(self, isStocker:bool):
        self.isStocker = isStocker

    def getShoppingCart(self):
        return self.shoppingCart

    def setShoppingCart(self, shoppingCart:ShoppingCart):
        self.shoppingCart = shoppingCart

    #// addItemToCart(int pid) void