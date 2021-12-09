#// class ShoppingCart
#// this object is composed of Item objects

#import edu.cudenver.university.Student;

#import java.util.ArrayList;

class ShoppingCart:
    #// thinking if we should have a cartID? for that unique cart?
    cartList = list()       #// list of Items

    #//Constructor for ShoppingCart
    def __init__(self):
        self.cartList = list   #// assumed to have a capacity of 100 items

    def add_item_to_cart(self, item):
        self.cartList.add(item)

    def get_cart_list(self):
        return self.cartList

    #// No methods for appending/adding to the cartList added as of yet or toString to check if the items accurately added