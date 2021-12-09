# class Customer, interacts with the ShoppingCart, add items to his/her ShoppingCart
from datetime import date
import ShoppingCart
import Order
class Customer:
    #userName = ""
    #password = ""
    #isStocker = False
    #private ShoppingCart shoppingCart;

    # Customer constructor
    def __init__(self, username: str, password: str, is_stocker: bool, shopping_cart: ShoppingCart):
        self._username = username
        self._password = password
        self._is_stocker = is_stocker
        self._shopping_cart = shopping_cart

    def checkout(self, order_number):
        order = Order(order_number, date.today())
        for item in self.shopping_cart:
            order.add_item_to_order(item)
        self._shopping_cart = ShoppingCart()
        return order

    @property
    def username(self):
        return self._userName

    @username.setter
    def username(self, username: str):
        self._username = username

    @property
    def password(self):
        return self._password

    @password.setter
    def password(self, password: str):
        self._password = password

    @property
    def is_stocker(self):
        return self._is_stocker

    @is_stocker.setter
    def is_stocker(self, is_stocker: bool):
        self.is_stocker = is_stocker

    @property
    def shopping_cart(self):
        return self._shoppingCart

    @shopping_cart.setter
    def shopping_cart(self, shopping_cart: ShoppingCart):
        self._shopping_cart = shopping_cart

    #// addItemToCart(int pid) void
