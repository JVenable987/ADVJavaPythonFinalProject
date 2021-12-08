import Item
class OrderLine:
    #private Item item;
    #private int quantity;

    def __init__(self, item:Item, quantity:int):
        self.item = item
        self.quantity = quantity

    def subTotal(self):
        return self.item.getPrice() * self.quantity

    def toString(self):
        return str("Line:{%5d x %s = $%10.2f}",self.getQuantity(),self.getItem(), self.subTotal())

    def getItem(self):
        return self.item

    def setItem(self, item:Item):
        self.item = item

    def getQuantity(self):
        return self.quantity

    def setQuantity(self, quantity:int):
        self.quantity = quantity