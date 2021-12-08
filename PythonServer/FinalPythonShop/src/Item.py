# class  Item

class Item:
    # add stockerAdded: Stocker after, productID if we decide to put that in
    def __init__(self, product_id: int, type: str, quantity: int,
                 price: float, manufacturer: str, model: str):
        self._product_id = product_id
        #self._stockerAdded = stockerAdded
        self._type = type
        self._quantity = quantity
        self._price = price
        self._manufacturer = manufacturer
        self._model = model

        @property
        def product_id(self):
            """Return the product_id"""
            return self._product_id

        @property
        def type(self):
            """Return the type"""
            return self._type

        @property                      
        def quantity(self):
            """Return the quantity"""
            return self._quantity

        @quantity.setter              
        def quantity(self, the_quantity):
            """Setter for quantity"""
            if the_quantity >= 0:
                self._quantity = quantity
            else:
                raise ValueError('quantity must be a non-negative quantity')

        @property                     
        def price(self):
            """Return the price"""
            return self._price

        @price.setter                 
        def price(self, the_price):
            """Setter for price"""
            if the_price >= 0:
                self._price = price
            else:
                raise ValueError('price must be a non-negative price')

        @property
        def manufacturer(self):
            """Return the manufacturer"""
            return  self._manufacturer

        @property
        def model(self):
            """Return the model"""
            return  self._model

        def __str__(self):
            return "".format(
                f"ITEM: [ID: {self._product_id} | Type: {self._type} | Quantity: {self._quantity} | Price: {self._price} | Manufacturer: {self._manufacturer} | Model: {self._model}]")
