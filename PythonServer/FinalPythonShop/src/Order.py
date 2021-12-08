#import java.time.LocalDate;
#import java.util.ArrayList;
import OrderLine

class Order:
    #private LocalDate date;
    #private String orderNumber;
    #private ArrayList<OrderLine> lines;

    def __init__(self, orderNumber:str, date=None):
        self.orderNumber = orderNumber
        self.date = date
        self.lines = list()

    def total(self):
        #//TODO
        total= 0;
        for ol in self.lines:
            total += ol.subTotal()
        return total

    def toString(self):
        #todo: make sure this works
        sb = ""
        sb += "------------------------------------------------------------------------------------------------------------\n"
        sb += str("Order:[%-20s|%-10s]\n",self.getOrderNumber(),self.getDate())
        for line in self.lines:
            sb+= str("--->%-50s\n",line.toString())
        sb += str("---> Order Total: $%10.2f\n", self.total())
        sb += "============================================================================================================"
        return sb

    def getDate(self):
        return self.date

    #todo:update LocalDate
    def setDate(self, date:LocalDate):
        self.date = date

    def getOrderNumber(self):
        return self.orderNumber

    def setOrderNumber(self, orderNumber:str):
        self.orderNumber = orderNumber

    def getLines(self):
        return self.lines

    def setLines(self, lines:list):
        self.lines = lines