import Item

class OrderLine:
    #private Item item;
    #private int quantity;

    def __init__(self, item: Item, quantity: int):
        self._item = item
        self._quantity = quantity

    def sub_total(self):
        return self.item.price * self._quantity

    def __str__(self):
        return "Line: {} x {} = ${} ".format(self._quantity, self._item, self.sub_total())

    @property
    def item(self):
        """Return the item"""
        return self._item

    @item.setter
    def item(self, item: Item):
        """Set the item"""
        self._item = item
        
    @property
    def quantity(self):
        return self._quantity

    @quantity.setter
    def quantity(self, quantity: int):
        self._quantity = quantity
