# Catalog contains all the items in the system

import Item


class Catalog:
    items = None
    
    def __init__(self):
        self._items = list()
        
    @property
    def items(self):
        return self._items
    
    @items.setter
    def items(self, items):
        self._items = items
        
    def add_item(self, item: Item):
        self._items.append(item)
        
    def show_catalog(self):
        for i in self._items:
            print(i)
            
    def size_of_catalog(self):
        return "Catalog has: {} items".format(len(self._items)) 
    
