#// class ShoppingCart
#// this object is composed of Item objects

#import edu.cudenver.university.Student;

#import java.util.ArrayList;

class ShoppingCart:
    #// thinking if we should have a cartID? for that unique cart?
         #// list of Items

    #//Constructor for ShoppingCart
    def __init__(self):
        self.cartList = []   #// assumed to have a capacity of 100 items

    def add_item_to_cart(self, item):
        self.cartList.append(item)

    def get_total(self):
        total = 0
        for item in self.cartList:
            total += item.price * item.quantity
        return total



    def get_cart_list(self):
        return self.cartList

    #// No methods for appending/adding to the cartList added as of yet or toString to check if the items accurately added