#// Catalog contains all the Items in the system

#import java.util.ArrayList;
import Item

class Catalog:
    items = None

    def __init__(self):
        self.items = list();

    def getItems(self):
        return self.items

    def setItems(self, items:list):
        self.items = items

    def addItem(self , item:Item):
        self.items.append(item)

    def showCatalog(self):
        # to show everything that is in the catalog (should be all items)
        for i in self.items:
            print(i)

    def sizeOfCatalog(self):
        print("Catalog has: " + len(self.items) + " items")